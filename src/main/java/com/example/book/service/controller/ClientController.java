package com.example.book.service.controller;

import com.example.book.service.dto.ClientCreateRequestDTO;
import com.example.book.service.dto.ClientDTO;
import com.example.book.service.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public List<ClientDTO> getAllClients(){
        return clientService.getAllClients();
    }
    @GetMapping("/{email}")
    public ResponseEntity<ClientDTO> getClientByEmail(@PathVariable String email){
        return ResponseEntity.ok(clientService.getClientByEmail(email));
    }

    @PostMapping
    public ResponseEntity<ClientDTO> addClient(@RequestBody @Valid ClientCreateRequestDTO  ClientDTO){
        return ResponseEntity.ok(clientService.addClient(ClientDTO));
    }

    @PutMapping("/{email}")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable String email, @RequestBody @Valid ClientDTO clientDTO){
        return ResponseEntity.ok(clientService.updateClientByEmail(email, clientDTO));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteClient(@PathVariable String email){
        clientService.deleteClientByEmail(email);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{email}/block")
    public ResponseEntity<Void> blockClient(@PathVariable String email) {
        clientService.blockClient(email);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{email}/unblock")
    public ResponseEntity<Void> unblockClient(@PathVariable String email) {
        clientService.unblockClient(email);
        return ResponseEntity.ok().build();
    }
}
