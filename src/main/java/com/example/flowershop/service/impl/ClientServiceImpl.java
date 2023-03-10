package com.example.flowershop.service.impl;

import com.example.flowershop.exceptions.FlowerServiceException;
import com.example.flowershop.model.dto.ClientDTO;
import com.example.flowershop.model.entity.Client;
import com.example.flowershop.model.entity.Status;
import com.example.flowershop.model.repository.ClientRepository;
import com.example.flowershop.service.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

   ClientRepository clientRepository;
   ObjectMapper mapper;


    public Client createClient(ClientDTO clientDTO) {
        clientRepository.findByEmail(clientDTO.getEmail()).ifPresent(
                h -> {
                    throw new FlowerServiceException("Пользователь с таким email уже существует");
                }
        );

        Client client = mapper.convertValue(clientDTO, Client.class);
        Client save = clientRepository.save(client);
        return mapper.convertValue(save, Client.class);
    }
    private static final String EXC_MESSAGE = "Не найден клиент с такми id";
    @Override
    public ClientDTO read(String email) {
       Client client = clientRepository.findByEmail(email).orElseThrow(() ->
                new FlowerServiceException(String.format(EXC_MESSAGE, email)));
        return mapper.convertValue(client, ClientDTO.class);
    }
    @Override
    public ClientDTO update(Client clientDTO) {
        String email = clientDTO.getEmail();
        if (email == null) {
            throw new FlowerServiceException("почта не найдена");
        }
        read(email);
        Client client = mapper.convertValue(clientDTO, Client.class);
        client.setStatus(Status.valueOf(String.valueOf(Status.UPDATED)));
        Client save = clientRepository.save(client);
        return mapper.convertValue(save, ClientDTO.class);

    }


    @Override
    public void delete(String email) {
        Client client = getClient(email);
        client.setStatus(Status.DELETED);
        client.setUpdatedAt(LocalDateTime.now());
        clientRepository.save(client);
    }

    @Override
    public Client getClient(String email) {
        return clientRepository.findByEmail(email)
                .orElseThrow(() -> new FlowerServiceException("Пользователь с таким email не найден"));
    }

}
