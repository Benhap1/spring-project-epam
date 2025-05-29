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

    @GetMapping("/client/{email}")
    public ResponseEntity<List<OrderDTO>> getOrdersByClient(@PathVariable String email) {
        List<OrderDTO> orders = orderService.getOrdersByClient(email);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/employee/{email}")
    public ResponseEntity<List<OrderDTO>> getOrdersByEmployee(@PathVariable String email) {
        List<OrderDTO> orders = orderService.getOrdersByEmployee(email);
        return ResponseEntity.ok(orders);
    }


    @PostMapping
    public ResponseEntity<OrderDTO> addOrder(@RequestBody @Valid OrderDTO orderDTO) {
        OrderDTO createdOrder = orderService.addOrder(orderDTO);
        return ResponseEntity.ok(createdOrder);
    }

    @PatchMapping("/{orderId}/submit")
    public ResponseEntity<OrderDTO> submitOrder(@PathVariable Long orderId, @RequestParam String clientEmail) {
        OrderDTO submittedOrder = orderService.submitOrder(orderId, clientEmail);
        return ResponseEntity.ok(submittedOrder);
    }

    @PatchMapping("/{orderId}/confirm")
    public ResponseEntity<OrderDTO> confirmOrder(@PathVariable Long orderId, @RequestParam String employeeEmail) {
        OrderDTO confirmedOrder = orderService.confirmOrder(orderId, employeeEmail);
        return ResponseEntity.ok(confirmedOrder);
    }

    @PatchMapping("/{orderId}/cancel")
    public ResponseEntity<OrderDTO> cancelOrder(@PathVariable Long orderId, @RequestParam String employeeEmail) {
        OrderDTO cancelledOrder = orderService.cancelOrder(orderId, employeeEmail);
        return ResponseEntity.ok(cancelledOrder);
    }
}
