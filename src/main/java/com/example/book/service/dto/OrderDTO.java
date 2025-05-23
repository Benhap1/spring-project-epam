package com.example.book.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private String clientEmail;
    private String employeeEmail;
    private LocalDateTime orderDate;
    private BigDecimal price;
    private List<BookItemDTO> bookItems;
}
