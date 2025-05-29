package com.example.book.service.service.impl;

import com.example.book.service.dto.BookItemDTO;
import com.example.book.service.dto.OrderDTO;
import com.example.book.service.exception.CustomBadRequestException;
import com.example.book.service.exception.ForbiddenException;
import com.example.book.service.exception.NotFoundException;
import com.example.book.service.model.*;
import com.example.book.service.model.enums.OrderStatus;
import com.example.book.service.repo.BookRepository;
import com.example.book.service.repo.ClientRepository;
import com.example.book.service.repo.EmployeeRepository;
import com.example.book.service.repo.OrderRepository;
import com.example.book.service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;
    private final BookRepository bookRepository;

    @Override
    public List<OrderDTO> getOrdersByClient(String clientEmail) {
        Client client = clientRepository.findByEmail(clientEmail)
                .orElseThrow(() -> new NotFoundException("Client not found with email: " + clientEmail));

        return orderRepository.findAllByClient(client)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> getOrdersByEmployee(String employeeEmail) {
        Employee employee = employeeRepository.findByEmail(employeeEmail)
                .orElseThrow(() -> new NotFoundException("Employee not found with email: " + employeeEmail));

        return orderRepository.findAllByEmployee(employee)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    @Override
    public OrderDTO addOrder(OrderDTO orderDTO) {
        Client client = clientRepository.findByEmail(orderDTO.getClientEmail())
                .orElseThrow(() -> new NotFoundException("Client not found: " + orderDTO.getClientEmail()));

        Employee employee = null;
        if (orderDTO.getEmployeeEmail() != null) {
            employee = employeeRepository.findByEmail(orderDTO.getEmployeeEmail())
                    .orElseThrow(() -> new NotFoundException("Employee not found: " + orderDTO.getEmployeeEmail()));
        }

        if (orderDTO.getBookItems() == null || orderDTO.getBookItems().isEmpty()) {
            throw new CustomBadRequestException("Order must contain at least one book item");
        }

        Order order = Order.builder()
                .client(client)
                .employee(employee)
                .orderDate(orderDTO.getOrderDate() != null ? orderDTO.getOrderDate() : LocalDateTime.now())
                .price(orderDTO.getPrice())
                .status(OrderStatus.DRAFT)
                .build();

        List<BookItem> bookItems = buildBookItems(orderDTO.getBookItems(), order);
        order.setBookItems(bookItems);

        Order saved = orderRepository.save(order);
        return convertToDTO(saved);
    }

    private List<BookItem> buildBookItems(List<BookItemDTO> itemDTOs, Order order) {
        return itemDTOs.stream().map(itemDTO -> {
            Long bookId = itemDTO.getBook().getId();

            Book book = bookRepository.findById(bookId)
                    .orElseThrow(() -> new NotFoundException("Book not found with id: " + bookId));

            return BookItem.builder()
                    .book(book)
                    .quantity(itemDTO.getQuantity())
                    .order(order)
                    .build();
        }).collect(Collectors.toList());
    }


    private OrderDTO convertToDTO(Order order) {
        return OrderDTO.builder()
                .id(order.getId())
                .clientEmail(order.getClient().getEmail())
                .employeeEmail(order.getEmployee() != null ? order.getEmployee().getEmail() : null)
                .orderDate(order.getOrderDate())
                .price(order.getPrice())
                .status(order.getStatus())
                .bookItems(order.getBookItems().stream().map(bookItem ->
                        BookItemDTO.builder()
                                .book(bookItem.getBook())
                                .quantity(bookItem.getQuantity())
                                .build()
                ).collect(Collectors.toList()))
                .build();
    }

    @Override
    public OrderDTO submitOrder(Long orderId, String clientEmail) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Order not found with id: " + orderId));

        if (!order.getClient().getEmail().equals(clientEmail)) {
            throw new ForbiddenException("Client does not have access to this order");
        }

        if (order.getStatus() != OrderStatus.DRAFT) {
            throw new CustomBadRequestException("Only orders in DRAFT status can be submitted");
        }

        order.setStatus(OrderStatus.SUBMITTED);
        orderRepository.save(order);
        return convertToDTO(order);
    }

    @Override
    public OrderDTO confirmOrder(Long orderId, String employeeEmail) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Order not found with id: " + orderId));

        if (order.getStatus() != OrderStatus.SUBMITTED) {
            throw new CustomBadRequestException("Only orders in SUBMITTED status can be confirmed");
        }

        Employee employee = employeeRepository.findByEmail(employeeEmail)
                .orElseThrow(() -> new NotFoundException("Employee not found with email: " + employeeEmail));

        order.setEmployee(employee);
        order.setStatus(OrderStatus.CONFIRMED);
        orderRepository.save(order);
        return convertToDTO(order);
    }

    @Override
    public OrderDTO cancelOrder(Long orderId, String employeeEmail) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Order not found with id: " + orderId));

        if (order.getStatus() != OrderStatus.SUBMITTED) {
            throw new CustomBadRequestException("Only orders in SUBMITTED status can be cancelled");
        }

        Employee employee = employeeRepository.findByEmail(employeeEmail)
                .orElseThrow(() -> new NotFoundException("Employee not found with email: " + employeeEmail));

        order.setEmployee(employee);
        order.setStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);
        return convertToDTO(order);
    }
}
