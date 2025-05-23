package com.example.book.service.service.impl;

import com.example.book.service.dto.ClientDTO;
import com.example.book.service.service.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    @Override
    public List<ClientDTO> getAllClients() {
        return List.of();
    }

    @Override
    public ClientDTO getClientByEmail(String email) {
        return null;
    }

    @Override
    public ClientDTO updateClientByEmail(String email, ClientDTO client) {
        return null;
    }

    @Override
    public void deleteClientByEmail(String email) {

    }

    @Override
    public ClientDTO addClient(ClientDTO client) {
        return null;
    }
}
