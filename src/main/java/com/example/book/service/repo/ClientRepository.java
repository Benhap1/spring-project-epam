package com.example.book.service.repo;

import com.example.book.service.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    // TODO Place your code here
}
