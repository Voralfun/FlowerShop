package com.example.flowershop.controller;

import com.example.flowershop.common.ApiResponse;
import com.example.flowershop.model.dto.FlowerDTO;
import com.example.flowershop.service.FlowerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flower")
public class FlowerController {
    @Autowired
    FlowerService flowerService;
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> createFlower(@RequestBody FlowerDTO flowerDTO) {
        flowerService.createFlower(flowerDTO);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Товар был добавлен в корзину"), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<FlowerDTO>> getAllFlowers() {
        List<FlowerDTO> flowers =flowerService.getAllFlowers();
        return new ResponseEntity<>(flowers, HttpStatus.OK);
    }
    @PostMapping("/update/{flowerId}")
    public ResponseEntity<ApiResponse> updateFlower(@PathVariable("flowerId") Long flowerId, @RequestBody FlowerDTO flowerDTO){
        flowerService.updateFlower(flowerDTO, flowerId);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Товар был обновлён"), HttpStatus.OK);
    }
}
