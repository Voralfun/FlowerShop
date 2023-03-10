package com.example.flowershop.service;

import com.example.flowershop.model.dto.ClientDTO;
import com.example.flowershop.model.entity.Client;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ClientService {



    ClientDTO read(String email);

    ClientDTO update(Client clientDTO);

    void delete(String email);

    Client getClient(String email);
}
