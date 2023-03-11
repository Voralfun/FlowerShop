package com.example.flowershop.service;

import com.example.flowershop.model.dto.FlowerDTO;
import com.example.flowershop.model.entity.Flower;

import java.util.List;
public interface FlowerService {

    void updateFlower(FlowerDTO flowerDTO, Long flowerId);

    Flower findById(Long flowerId);

    FlowerDTO getFlowerDTO(Flower flower);

    void delete(Long flowerId);

    void createFlower(FlowerDTO flowerDTO);

    List<FlowerDTO> getAllFlowers();
}
