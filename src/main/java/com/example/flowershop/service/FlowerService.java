package com.example.flowershop.service;

import com.example.flowershop.model.dto.FlowerDTO;
import com.example.flowershop.model.entity.Flower;

import java.util.List;
public interface FlowerService {


    FlowerDTO read(Long id);

    void updateFlower(FlowerDTO flowerDTO, Long flowerId);

    FlowerDTO delete(Long id);

    List<FlowerDTO> readAll();

    Flower findById(Long flowerId);

    FlowerDTO getFlowerDTO(Flower flower);
    void createFlower(FlowerDTO flowerDTO);

    List<FlowerDTO> getAllFlowers();
}
