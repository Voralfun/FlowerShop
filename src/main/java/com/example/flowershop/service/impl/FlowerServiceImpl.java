package com.example.flowershop.service.impl;

import com.example.flowershop.exceptions.FlowerServiceException;
import com.example.flowershop.model.dto.FlowerDTO;
import com.example.flowershop.model.entity.Flower;
import com.example.flowershop.model.entity.enums.Status;
import com.example.flowershop.model.repository.FlowerRepository;
import com.example.flowershop.service.FlowerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
public class FlowerServiceImpl implements FlowerService {
    @Autowired
    FlowerRepository flowerRepository;

    @Override
    public void createFlower(FlowerDTO flowerDTO) {
       Flower flower = new Flower();
       flower.setSort(flowerDTO.getSort());
       flower.setType(flowerDTO.getType());
       flower.setPrice(flowerDTO.getPrice());
       flower.setCreatedAt(LocalDateTime.now());
       flower.setStatus(Status.valueOf(String.valueOf(Status.CREATED)));
       flowerRepository.save(flower);
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
        flower.setUpdatedAt(LocalDateTime.now());
        flowerRepository.save(flower);
    }
    @Override
    public void delete(Long flowerId) {


        Optional<Flower> optionalFlower =flowerRepository.findById(flowerId);

        if (optionalFlower.isEmpty()) {
            throw new FlowerServiceException("Неверный id: " + flowerId);
        }

        Flower flower = optionalFlower.get();

        flowerRepository.delete(flower);
    }

    @Override
    public Flower findById(Long flowerId) throws FlowerServiceException {
        Optional<Flower> optionalFlower = flowerRepository.findById(flowerId);
        if (optionalFlower.isEmpty()) {
            throw new FlowerServiceException("Не найден id продукта: " + flowerId);
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
    @Override
    public List<FlowerDTO> getAllFlowers() {
        List<Flower> allFlowers = flowerRepository.findAll();

        List<FlowerDTO> flowerdto = new ArrayList<>();
        for(Flower flower: allFlowers) {
            flowerdto.add(getFlowerDTO(flower));
        }
        return flowerdto;
    }
}
