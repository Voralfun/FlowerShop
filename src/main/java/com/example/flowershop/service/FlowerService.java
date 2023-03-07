package com.example.flowershop.service;

import com.example.flowershop.model.dto.ClientDTO;
import com.example.flowershop.model.dto.FlowerDTO;
import com.example.flowershop.model.entity.Client;
import com.example.flowershop.model.entity.Flower;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface FlowerService {
    void delete(Long id);
    List<FlowerDTO> readAll();
    List<FlowerDTO> getAll();
    FlowerDTO createFlower(FlowerDTO FlowerDTO);

    FlowerDTO read(Long id);

    FlowerDTO update( FlowerDTO flowerDTO);

    ResponseEntity<String> readFlower(@PathVariable("id") Long id);

    ResponseEntity<List<FlowerDTO>> getAllFlowers();

    Flower getFlower(Long Id);
    void addToClientCart(Long flowerId,String email);
}
