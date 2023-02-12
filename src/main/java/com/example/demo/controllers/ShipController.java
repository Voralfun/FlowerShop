package com.example.demo.controllers;

import com.example.demo.model.dto.ShipDTO;
import com.example.demo.service.ShipService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ships")
@RequiredArgsConstructor
public class ShipController {

    private final ShipService shipService;
    @PostMapping
    @Operation(summary = "Создание корабля")
    public ShipDTO create(@RequestBody ShipDTO shipDTO){
        return shipService.create(shipDTO);
    }
    @PutMapping
    public ResponseEntity<ShipDTO> updateShip(@RequestBody ShipDTO shipDTO){
        return ResponseEntity.ok(shipService.update(shipDTO));
    }
    @GetMapping
    public ResponseEntity<ShipDTO> getShip(@RequestParam(name = "serialNUM",required = true) String serialNUM) {
        return ResponseEntity.ok(shipService.get(serialNUM));
    }
    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteShip(@RequestBody String serialNUM){
        shipService.delete(serialNUM);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/components")
    public String getShipComponents(){
        return shipService.getShipComponents();
    }
    @GetMapping("/engine")
    public Integer getEngineInfo(){
        return shipService.getEngineInfo();
    }
}
