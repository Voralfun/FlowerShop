package com.example.demo.service;

import com.example.demo.model.dto.ShipDTO;
import com.example.demo.model.entity.Ship;

public interface ShipService {

    ShipDTO create(ShipDTO shipDTO);

    ShipDTO update(ShipDTO shipDTO);

    ShipDTO get(String serialNUM);

    void delete(String serialNUM);

    Ship getShip(String serialNUM);

    String getShipComponents();
    Integer getEngineInfo();
}
