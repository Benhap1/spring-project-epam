package com.example.book.service.dto;

import com.example.book.service.model.Book;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookItemDTO {
    private Book book;
    private Integer quantity;
}
