package com.example.demo.service;

import com.example.demo.model.dto.ShipDTO;
import com.example.demo.model.entity.Ship;

public interface ShipService {

    String getShipComponents();
    Integer getEngineInfo();

    ShipDTO update(ShipDTO shipDTO);

    ShipDTO get(String serialNUM);

    void delete(String serialNUM);

    ShipDTO create(ShipDTO shipDTO);

    Ship getShip(String shipDTO);
}
