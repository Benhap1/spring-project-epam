package com.example.book.service.service;

import com.example.book.service.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    List<OrderDTO> getOrdersByClient(String clientEmail);

    List<OrderDTO> getOrdersByEmployee(String employeeEmail);

    OrderDTO addOrder(OrderDTO order);
}
