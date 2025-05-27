package com.example.book.service.service.impl;

import com.example.book.service.dto.BookDTO;
import com.example.book.service.exception.ResourceNotFoundException;
import com.example.book.service.model.Book;
import com.example.book.service.repo.BookRepository;
import com.example.book.service.service.BookService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(book -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public BookDTO getBookByName(String name) {
        Book book = bookRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with name: " + name));
        return modelMapper.map(book, BookDTO.class);
    }

    @Override
    public BookDTO updateBookByName(String name, BookDTO bookDTO) {
        Book book = bookRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with name: " + name));

        modelMapper.map(bookDTO, book); // обновляет поля существующего объекта
        Book saved = bookRepository.save(book);
        return modelMapper.map(saved, BookDTO.class);
    }

    @Override
    public void deleteBookByName(String name) {
        Book book = bookRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with name: " + name));
        bookRepository.delete(book);
    }

    @Override
    public BookDTO addBook(BookDTO bookDTO) {
        Book book = modelMapper.map(bookDTO, Book.class);
        Book saved = bookRepository.save(book);
        return modelMapper.map(saved, BookDTO.class);
    }
}