package com.example.book.service.service.impl;

import com.example.book.service.dto.OrderDTO;
import com.example.book.service.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public List<OrderDTO> getOrdersByClient(String clientEmail) {
        return List.of();
    }

    @Override
    public List<OrderDTO> getOrdersByEmployee(String employeeEmail) {
        return List.of();
    }

    @Override
    public OrderDTO addOrder(OrderDTO order) {
        return null;
    }
}
