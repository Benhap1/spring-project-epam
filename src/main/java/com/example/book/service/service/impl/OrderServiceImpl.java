package com.example.book.service.service.impl;

import com.example.book.service.dto.OrderDTO;
import com.example.book.service.exception.NotFoundException;
import com.example.book.service.model.Client;
import com.example.book.service.model.Employee;
import com.example.book.service.model.Order;
import com.example.book.service.repo.ClientRepository;
import com.example.book.service.repo.EmployeeRepository;
import com.example.book.service.repo.OrderRepository;
import com.example.book.service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<OrderDTO> getOrdersByClient(String clientEmail) {
        Client client = clientRepository.findByEmail(clientEmail)
                .orElseThrow(() -> new NotFoundException("Client not found with email: " + clientEmail));

        return orderRepository.findAllByClient(client)
                .stream()
                .map(order -> modelMapper.map(order, OrderDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> getOrdersByEmployee(String employeeEmail) {
        Employee employee = employeeRepository.findByEmail(employeeEmail)
                .orElseThrow(() -> new NotFoundException("Employee not found with email: " + employeeEmail));

        return orderRepository.findAllByEmployee(employee)
                .stream()
                .map(order -> modelMapper.map(order, OrderDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTO addOrder(OrderDTO orderDTO) {
        Order order = modelMapper.map(orderDTO, Order.class);
        Order saved = orderRepository.save(order);
        return modelMapper.map(saved, OrderDTO.class);
    }
}