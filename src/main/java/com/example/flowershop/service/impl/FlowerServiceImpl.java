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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FlowerServiceImpl implements FlowerService {
    @Autowired
    FlowerRepository flowerRepository;
    ObjectMapper mapper;


    @Override
    public void createFlower(FlowerDTO flowerDTO) {
       Flower flower = new Flower();
       flower.setSort(flowerDTO.getSort());
       flower.setType(flowerDTO.getType());
       flower.setPrice(flowerDTO.getPrice());
       flowerRepository.save(flower);
    }
    private static final String EXC_MESSAGE = "Не найден товар с такми id";
    @Override
    public FlowerDTO read(Long id) {
        Flower flower = flowerRepository.findById(id).orElseThrow(() ->
                new FlowerServiceException(String.format(EXC_MESSAGE, id)));
        return mapper.convertValue(flower, FlowerDTO.class);
    }
    @Override
    public void updateFlower(FlowerDTO flowerDTO, Long flowerId) throws FlowerServiceException {
        Optional<Flower> optionalFlower = flowerRepository.findById(flowerId);
        if (!optionalFlower.isPresent()) {
            throw new FlowerServiceException("Не добавлен продукт");
        }
        Flower flower = optionalFlower.get();
        flower.setSort(flowerDTO.getSort());
        flower.setType(flowerDTO.getType());
        flower.setPrice(flowerDTO.getPrice());
        flowerRepository.save(flower);
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
    public List<FlowerDTO> getAllFlowers() {
        List<Flower> allFlowers = flowerRepository.findAll();

        List<FlowerDTO> flowerdto = new ArrayList<>();
        for(Flower flower: allFlowers) {
            flowerdto.add(getFlowerDTO(flower));
        }
        return flowerdto;
    }
}
