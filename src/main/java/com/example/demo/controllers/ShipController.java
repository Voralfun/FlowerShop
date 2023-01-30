package com.example.demo.controllers;

import com.example.demo.model.dto.ShipDTO;
import com.example.demo.service.ShipService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ships")
@RequiredArgsConstructor
public class ShipController {

    private final ShipService ShipService;

    @PostMapping
    public ShipDTO createShipName(@RequestBody ShipDTO shipDTO){
        return ShipService.createShipName(shipDTO);
    }

    @GetMapping("/components")
    public String getShipComponents(){
        return ShipService.getShipComponents();
    }
    @GetMapping("/engine")
    public Integer getEngineInfo(){
        return ShipService.getEngineInfo();
    }
}
