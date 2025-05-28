package com.example.book.service.controller;

import com.example.book.service.dto.OrderDTO;
import com.example.book.service.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // Получение заказов по email клиента
    @GetMapping("/client/{email}")
    public ResponseEntity<List<OrderDTO>> getOrdersByClient(@PathVariable String email) {
        List<OrderDTO> orders = orderService.getOrdersByClient(email);
        return ResponseEntity.ok(orders);
    }

    // Получение заказов по email сотрудника
    @GetMapping("/employee/{email}")
    public ResponseEntity<List<OrderDTO>> getOrdersByEmployee(@PathVariable String email) {
        List<OrderDTO> orders = orderService.getOrdersByEmployee(email);
        return ResponseEntity.ok(orders);
    }

    // Создание нового заказа
    @PostMapping
    public ResponseEntity<OrderDTO> addOrder(@RequestBody @Valid OrderDTO orderDTO) {
        OrderDTO createdOrder = orderService.addOrder(orderDTO);
        return ResponseEntity.ok(createdOrder);
    }
}
