package com.example.flowershop.service;

import com.example.flowershop.model.dto.ClientDTO;
import com.example.flowershop.model.entity.Client;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ClientService {
    void delete(Long id);

    List< ClientDTO> readAll();

    ClientDTO createClient(ClientDTO clientDTO);

    ClientDTO read(Long id);
    Client findByEmail(String email);

    ClientDTO update( ClientDTO clientDto);

    ResponseEntity<String> readClient(@PathVariable("id") Long id);

    ResponseEntity<List<ClientDTO>> getAllClients();

    Client getClient(Long id);

    void save(Client client);
}
