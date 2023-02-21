package com.example.flowershop.controller;

import com.example.flowershop.model.dto.ClientDTO;
import com.example.flowershop.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
    @RestController
    @RequiredArgsConstructor
    @RequestMapping("/client")

    public class ClientController {

        private final ClientService clientService;

        @PostMapping
        public ClientDTO createClient(@RequestBody ClientDTO clientDTO) {
            return clientService.createClient(clientDTO);
        }

        @GetMapping("/{id}")
        public ResponseEntity<String> read(@PathVariable("id") Long id) {
            return clientService.readClient(id);
        }

        @PutMapping
        public ResponseEntity<ClientDTO> update(@RequestBody ClientDTO clientDTO) {
            return ResponseEntity.ok(clientService.update(clientDTO));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<ClientDTO> delete(@PathVariable("id") Long id) {
            clientService.delete(id);
            return ResponseEntity.ok().build();
        }

        @GetMapping("/all")
        public ResponseEntity<List<ClientDTO>> getAllClients() {
            return clientService.getAllClients();
        }
    }

