package com.example.book.service.service.impl;

import com.example.book.service.dto.BookDTO;
import com.example.book.service.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Override
    public List<BookDTO> getAllBooks() {
        return List.of();
    }

    @Override
    public BookDTO getBookByName(String name) {
        return null;
    }

    @Override
    public BookDTO updateBookByName(String name, BookDTO book) {
        return null;
    }

    @Override
    public void deleteBookByName(String name) {

    }

    @Override
    public BookDTO addBook(BookDTO book) {
        return null;
    }
}
