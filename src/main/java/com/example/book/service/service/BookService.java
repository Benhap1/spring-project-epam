package com.example.book.service.service;

import com.example.book.service.dto.BookDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BookService {
    List<BookDTO> getAllBooks();

    BookDTO getBookByName(String name);

    BookDTO updateBookByName(String name, BookDTO book);

    void deleteBookByName(String name);

    BookDTO addBook(BookDTO book);
}
