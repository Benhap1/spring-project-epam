package com.example.book.service.service.impl;

import com.example.book.service.dto.ClientDTO;
import com.example.book.service.model.Client;
import com.example.book.service.repo.ClientRepository;
import com.example.book.service.service.ClientService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<ClientDTO> getAllClients() {
        return clientRepository.findAll().stream()
                .map(client -> modelMapper.map(client, ClientDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ClientDTO getClientByEmail(String email) {
        Client client = clientRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Client not found with email: " + email));
        return modelMapper.map(client, ClientDTO.class);
    }

    @Override
    public ClientDTO updateClientByEmail(String email, ClientDTO clientDTO) {
        Client client = clientRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Client not found with email: " + email));
        modelMapper.map(clientDTO, client);
        clientRepository.save(client);
        return modelMapper.map(client, ClientDTO.class);
    }

    @Override
    public void deleteClientByEmail(String email) {
        Client client = clientRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Client not found with email: " + email));
        clientRepository.delete(client);
    }

    @Override
    public ClientDTO addClient(ClientDTO clientDTO) {
        Client client = modelMapper.map(clientDTO, Client.class);
        clientRepository.save(client);
        return modelMapper.map(client, ClientDTO.class);
    }
}