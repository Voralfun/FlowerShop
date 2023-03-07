package com.example.flowershop.service.impl;

import com.example.flowershop.exceptions.CustomException;
import com.example.flowershop.exceptions.FlowerServiceException;
import com.example.flowershop.model.dto.ClientDTO;
import com.example.flowershop.model.entity.Client;
import com.example.flowershop.model.entity.Status;
import com.example.flowershop.model.repository.ClientRepository;
import com.example.flowershop.service.ClientService;
import com.example.flowershop.utils.JsonConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ObjectMapper objectMapper;
    private static final String EXC_MESSAGE = "Client with id %d is not found";

    @Override
    public ClientDTO createClient(ClientDTO clientDTO) {
        clientRepository.findById(clientDTO.getId()).ifPresent(
                h -> {
                    throw new CustomException("Client with current id is already exists", HttpStatus.BAD_REQUEST);
                }
        );

        Client client = objectMapper.convertValue(clientDTO, Client.class);
        Client save = clientRepository.save(client);
        client.setStatus(Status.CREATED);
        return objectMapper.convertValue(save, ClientDTO.class);
    }
    @Override
    public ClientDTO update(ClientDTO clientDTO) {
       Client client = getClient(clientDTO.getId());
       client.setBirthdate(clientDTO.getBirthdate() == null ? client.getBirthdate() : clientDTO.getBirthdate());
       client.setEmail(clientDTO.getEmail() == null ? client.getEmail() : clientDTO.getEmail());
       client.setName(clientDTO.getName() == null ? client.getName() : clientDTO.getName());
       client.setSurname(clientDTO.getSurname() == null ? client.getSurname() : clientDTO.getSurname());
       client.setPatronymic(clientDTO.getPatronymic() == null ? client.getPatronymic() : clientDTO.getPatronymic());
        client.setPhoneNUM(clientDTO.getPhoneNUM() == null ? client.getPhoneNUM() : clientDTO.getPhoneNUM());
       client.setUpdatedAt(LocalDateTime.now());
       client.setStatus(Status.UPDATED);
       return objectMapper.convertValue(clientRepository.save(client), ClientDTO.class);
    }

    @Override
    public void delete(Long id) {
        Client client = getClient(id);
        client.setStatus(Status.DELETED);
        client.setUpdatedAt(LocalDateTime.now());
        clientRepository.save(client);
    }

    @Override
    public ClientDTO read(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() ->
                new FlowerServiceException(String.format(EXC_MESSAGE, id)));
        return objectMapper.convertValue(client, ClientDTO.class);
    }

    @Override
    public Client findByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    @Override
    public List< ClientDTO> readAll() {
        return clientRepository.findAll().stream()
                .map(client -> objectMapper.convertValue(client, ClientDTO.class))
                .collect(Collectors.toList());
    }


    @Override
    public ResponseEntity<String> readClient(Long id) {
        if (id == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client is not found! Id is null");
        }

        ClientDTO client = read(id);
        String dto = JsonConverter.getString(client, objectMapper);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ClientDTO>> getAllClients() {
        return ResponseEntity.ok(readAll());
    }
    @Override
    public Client getClient(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new CustomException("Client with this id is not founded", HttpStatus.NOT_FOUND));
    }

    @Override
    public void save(Client client) {
            clientRepository.save(client);
    }

}
