package com.example.book.service.service;

import com.example.book.service.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    List<OrderDTO> getOrdersByClient(String clientEmail);

    List<OrderDTO> getOrdersByEmployee(String employeeEmail);

    OrderDTO addOrder(OrderDTO order);

    OrderDTO submitOrder(Long orderId, String clientEmail);

    OrderDTO confirmOrder(Long orderId, String employeeEmail);

    OrderDTO cancelOrder(Long orderId, String employeeEmail);
}
