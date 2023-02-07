package com.example.demo.service.impl;

import com.example.demo.exceptions.CustomException;
import com.example.demo.model.dto.ShipDTO;
import com.example.demo.model.entity.Ship;
import com.example.demo.model.enums.ShipStatus;
import com.example.demo.model.repository.ShipRepository;
import com.example.demo.service.ShipService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ShipServiceImpl implements ShipService {
    private final ShipRepository shipRepository;
    private final ObjectMapper mapper;
    @Override
    public String getShipComponents() {
        return "Engine";
    }

    @Override
    public Integer getEngineInfo() {
        return 153550;
    }

    public ShipDTO create(ShipDTO shipDTO) {
        shipRepository.findByserialNUM(shipDTO.getSerialNUM()).ifPresent(
                h -> {
                    throw new CustomException("This Serial Number has already been set", HttpStatus.BAD_REQUEST);}
        );
        Ship ship = mapper.convertValue(shipDTO,Ship.class);
        Ship save = shipRepository.save(ship);
        return mapper.convertValue(save,ShipDTO.class);
    }
    @Override
    public ShipDTO update(ShipDTO shipDTO) {
        Ship ship = getShip(shipDTO.getSerialNUM());
        ship.setType(shipDTO.getType() == null ? ship.getType():shipDTO.getType());
        ship.setLength(shipDTO.getLength() == null ? ship.getLength():shipDTO.getLength());
        ship.setUpdatedAt(LocalDateTime.now());
        ship.setStatus(ShipStatus.UPDATED);
        Ship save = shipRepository.save(ship);
        return mapper.convertValue(save,ShipDTO.class);
    }

    @Override
    public ShipDTO get(String serialNUM) {
        return mapper.convertValue(getShip(serialNUM),ShipDTO.class);
    }

    @Override
    public void delete(String serialNUM) {
        Ship ship = getShip(serialNUM);
        ship.setStatus(ShipStatus.DELETED);
        ship.setUpdatedAt(LocalDateTime.now());
        shipRepository.save(ship);
    }
    @Override
    public Ship getShip(String shipDTO) {
        return shipRepository.findByserialNUM(shipDTO)
                .orElseThrow(() -> new CustomException("serial Number was not found", HttpStatus.NOT_FOUND));
    }
}
