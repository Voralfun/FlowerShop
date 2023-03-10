package com.example.flowershop.service.impl;

import com.example.flowershop.exceptions.FlowerServiceException;
import com.example.flowershop.model.dto.FlowerDTO;
import com.example.flowershop.model.entity.Flower;
import com.example.flowershop.model.entity.Status;
import com.example.flowershop.model.repository.FlowerRepository;
import com.example.flowershop.service.FlowerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor

public class FlowerServiceImpl implements FlowerService {

    FlowerRepository flowerRepository;
    ObjectMapper mapper;


    public Flower createFlower(FlowerDTO flowerDTO) {
        flowerRepository.findById(flowerDTO.getId()).ifPresent(
                h -> {
                    throw new FlowerServiceException("Товар с таким id уже зарегистрирован");
                }
        );

        Flower flower = mapper.convertValue(flowerDTO, Flower.class);
        Flower save = flowerRepository.save(flower);
        return mapper.convertValue(save, Flower.class);
    }
    private static final String EXC_MESSAGE = "Не найден товар с такми id";
    @Override
    public FlowerDTO read(Long id) {
        Flower flower = flowerRepository.findById(id).orElseThrow(() ->
                new FlowerServiceException(String.format(EXC_MESSAGE, id)));
        return mapper.convertValue(flower, FlowerDTO.class);
    }
    @Override
    public FlowerDTO update(Flower flowerDTO) {
        Long id = flowerDTO.getId();
        if (id == null) {
            throw new FlowerServiceException("Не найден id товара");
        }
        read(id);
        Flower flower = mapper.convertValue(flowerDTO, Flower.class);
        flower.setStatus(Status.valueOf(String.valueOf(Status.UPDATED)));
        Flower save = flowerRepository.save(flower);
        return mapper.convertValue(save, FlowerDTO.class);

    }


    @Override
    public FlowerDTO delete(Long id) {
        Flower flower = mapper.convertValue(read(id), Flower.class);
        flower.setStatus(Status.valueOf(String.valueOf(Status.DELETED)));
        Flower save = flowerRepository.save(flower);
        return mapper.convertValue(save, FlowerDTO.class);
    }

    @Override
    public List<FlowerDTO> readAll() {
        return flowerRepository.findAll().stream()
                .map(flower -> mapper.convertValue(flower, FlowerDTO.class))
                .collect(Collectors.toList());
    }
    @Override
    public Flower findById(Long flowerId) throws FlowerServiceException {
        Optional<Flower> optionalFlower = flowerRepository.findById(flowerId);
        if (optionalFlower.isEmpty()) {
            throw new FlowerServiceException("product id is invalid: " + flowerId);
        }
        return optionalFlower.get();
    }
    @Override
    public FlowerDTO getFlowerDTO(Flower flower) {
        FlowerDTO flowerDTO = new FlowerDTO();
        flowerDTO.setId(flower.getId());
        flowerDTO.setPrice(flower.getPrice());
        flowerDTO.setSort(flower.getSort());
        flowerDTO.setType(flower.getType());
        return flowerDTO;
    }
}
