package com.example.demo.service;

import com.example.demo.model.dto.ShipDTO;

public interface ShipService {

    ShipDTO createShipName(ShipDTO shipDTO);
    String getShipComponents();
    Integer getEngineInfo();

}
