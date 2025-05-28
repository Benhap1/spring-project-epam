package com.example.book.service.service.impl;

import com.example.book.service.dto.BookItemDTO;
import com.example.book.service.dto.OrderDTO;
import com.example.book.service.exception.NotFoundException;
import com.example.book.service.model.*;
import com.example.book.service.model.enums.OrderStatus;
import com.example.book.service.repo.BookRepository;
import com.example.book.service.repo.ClientRepository;
import com.example.book.service.repo.EmployeeRepository;
import com.example.book.service.repo.OrderRepository;
import com.example.book.service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

//@Service
//@RequiredArgsConstructor
//public class OrderServiceImpl implements OrderService {
//
//    private final OrderRepository orderRepository;
//    private final ClientRepository clientRepository;
//    private final EmployeeRepository employeeRepository;
//    private final ModelMapper modelMapper;
//
//    @Override
//    public List<OrderDTO> getOrdersByClient(String clientEmail) {
//        Client client = clientRepository.findByEmail(clientEmail)
//                .orElseThrow(() -> new NotFoundException("Client not found with email: " + clientEmail));
//
//        return orderRepository.findAllByClient(client)
//                .stream()
//                .map(order -> modelMapper.map(order, OrderDTO.class))
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public List<OrderDTO> getOrdersByEmployee(String employeeEmail) {
//        Employee employee = employeeRepository.findByEmail(employeeEmail)
//                .orElseThrow(() -> new NotFoundException("Employee not found with email: " + employeeEmail));
//
//        return orderRepository.findAllByEmployee(employee)
//                .stream()
//                .map(order -> modelMapper.map(order, OrderDTO.class))
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public OrderDTO addOrder(OrderDTO orderDTO) {
//        Order order = modelMapper.map(orderDTO, Order.class);
//        Order saved = orderRepository.save(order);
//        return modelMapper.map(saved, OrderDTO.class);
//    }
//}

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

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

        Order order = Order.builder()
                .client(client)
                .employee(employee)
                .orderDate(orderDTO.getOrderDate() != null ? orderDTO.getOrderDate() : LocalDateTime.now())
                .price(orderDTO.getPrice())
                .status(OrderStatus.DRAFT)
                .build();

        List<BookItem> bookItems = orderDTO.getBookItems().stream().map(itemDTO -> {
            Long bookId = itemDTO.getBook().getId();

            Book book = bookRepository.findById(bookId)
                    .orElseThrow(() -> new NotFoundException("Book not found with id: " + bookId));

            return BookItem.builder()
                    .book(book)
                    .quantity(itemDTO.getQuantity())
                    .order(order)
                    .build();
        }).collect(Collectors.toList());

        order.setBookItems(bookItems);

        Order saved = orderRepository.save(order);
        return convertToDTO(saved);
    }

    private OrderDTO convertToDTO(Order order) {
        return OrderDTO.builder()
                .clientEmail(order.getClient().getEmail())
                .employeeEmail(order.getEmployee() != null ? order.getEmployee().getEmail() : null)
                .orderDate(order.getOrderDate())
                .price(order.getPrice())
                .bookItems(order.getBookItems().stream().map(bookItem ->
                        BookItemDTO.builder()
                                .book(bookItem.getBook())
                                .quantity(bookItem.getQuantity())
                                .build()
                ).collect(Collectors.toList()))
                .build();
    }
}
