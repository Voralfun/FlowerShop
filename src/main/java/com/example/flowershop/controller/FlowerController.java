package com.example.flowershop.controller;

import com.example.flowershop.model.dto.FlowerDTO;
import com.example.flowershop.service.FlowerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/flower")

public class FlowerController {

    private final FlowerService flowerService;

    @PostMapping
    public FlowerDTO createClient(@RequestBody FlowerDTO flowerDTO) {
        return flowerService.createFlower(flowerDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> read(@PathVariable("id") Long id) {
        return flowerService.readFlower(id);
    }

    @PutMapping
    public ResponseEntity<FlowerDTO> update(@RequestBody FlowerDTO flowerDTO) {
        return ResponseEntity.ok(flowerService.update(flowerDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FlowerDTO> delete(@PathVariable("id") Long id) {
        flowerService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<FlowerDTO>> getAllFlowers() {
        return flowerService.getAllFlowers();
    }
}
