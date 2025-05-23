package com.example.book.service.repo;

import com.example.book.service.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // TODO Place your code here
}
