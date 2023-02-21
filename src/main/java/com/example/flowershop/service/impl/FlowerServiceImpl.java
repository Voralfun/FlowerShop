package com.example.flowershop.service.impl;

import com.example.flowershop.exceptions.CustomException;
import com.example.flowershop.exceptions.FlowerServiceException;
import com.example.flowershop.model.dto.FlowerDTO;
import com.example.flowershop.model.entity.Flower;
import com.example.flowershop.model.entity.Status;
import com.example.flowershop.model.repository.FlowerRepository;
import com.example.flowershop.service.FlowerService;
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
public class FlowerServiceImpl implements FlowerService {

    private final FlowerRepository flowerRepository;
    private final ObjectMapper objectMapper;
    private static final String EXC_MESSAGE = "Flower with id %d is not found";

    @Override
    public FlowerDTO createFlower(FlowerDTO flowerDTO) {
        flowerRepository.findById(flowerDTO.getId()).ifPresent(
                h -> {
                    throw new CustomException("Flower with current id is already exists", HttpStatus.BAD_REQUEST);
                }
        );

        Flower flower = objectMapper.convertValue(flowerDTO, Flower.class);
        Flower save = flowerRepository.save(flower);
        flower.setStatus(Status.CREATED);
        return objectMapper.convertValue(save, FlowerDTO.class);
    }
    @Override
    public FlowerDTO update(FlowerDTO flowerDTO) {
        Flower flower = getFlower(flowerDTO.getId());
        flower.setType(flowerDTO.getType() == null ? flower.getType() : flower.getType());
        flower.setSort(flowerDTO.getSort() == null ? flower.getSort() : flower.getSort());
        flower.setPrice(flowerDTO.getPrice() == null ? flower.getPrice() : flower.getPrice());
        flower.setUpdatedAt(LocalDateTime.now());
        flower.setStatus(Status.UPDATED);
        return objectMapper.convertValue(flowerRepository.save(flower), FlowerDTO.class);
    }

    @Override
    public void delete(Long id) {
        Flower flower = getFlower(id);
        flower.setStatus(Status.DELETED);
        flower.setUpdatedAt(LocalDateTime.now());
        flowerRepository.save(flower);
    }

    @Override
    public FlowerDTO read(Long id) {
        Flower client = flowerRepository.findById(id).orElseThrow(() ->
                new FlowerServiceException(String.format(EXC_MESSAGE, id)));
        return objectMapper.convertValue(client, FlowerDTO.class);
    }
    @Override
    public List< FlowerDTO> readAll() {
        return flowerRepository.findAll().stream()
                .map(client -> objectMapper.convertValue(client, FlowerDTO.class))
                .collect(Collectors.toList());
    }


    @Override
    public ResponseEntity<String> readFlower(Long id) {
        if (id == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Flower is not found! Id is null");
        }

        FlowerDTO flower = read(id);
        String dto = JsonConverter.getString(flower, objectMapper);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<FlowerDTO>> getAllFlowers() {
        return ResponseEntity.ok(readAll());
    }
    @Override
    public Flower getFlower(Long id) {
        return flowerRepository.findById(id)
                .orElseThrow(() -> new CustomException("Flower with this id is not founded", HttpStatus.NOT_FOUND));
    }
}
