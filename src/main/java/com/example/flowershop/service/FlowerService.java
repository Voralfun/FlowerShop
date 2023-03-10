package com.example.flowershop.service;

import com.example.flowershop.model.dto.FlowerDTO;
import com.example.flowershop.model.entity.Flower;

import java.util.List;
public interface FlowerService {


    FlowerDTO read(Long id);

    FlowerDTO update(Flower flowerDTO);

    FlowerDTO delete(Long id);

    List<FlowerDTO> readAll();

    Flower findById(Long flowerId);

    FlowerDTO getFlowerDTO(Flower flower);
}
