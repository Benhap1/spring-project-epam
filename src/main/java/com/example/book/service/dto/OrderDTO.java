package com.example.book.service.dto;

import com.example.book.service.model.enums.OrderStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO {
    private String clientEmail;
    private String employeeEmail;
    private LocalDateTime orderDate;
    private BigDecimal price;
    private List<BookItemDTO> bookItems;
    private OrderStatus status;
}
