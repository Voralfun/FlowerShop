package com.example.demo.service.impl;

import com.example.demo.model.dto.CrewDTO;
import com.example.demo.model.dto.ShipDTO;
import com.example.demo.model.entity.Crew;
import com.example.demo.model.entity.Ship;
import com.example.demo.model.enums.Type;
import com.example.demo.model.repository.ShipRepository;
import com.example.demo.service.ShipService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShipServiceImpl implements ShipService {
    private final ShipRepository shipRepository;
    private final ObjectMapper mapper;
    @Override
    public ShipDTO createShipName(ShipDTO shipDTO) {
       Ship ship = mapper.convertValue(shipDTO,Ship.class);
       ship.setUpdatedAt(LocalDateTime.now());
        List<Crew> crews = shipDTO.getCrews()
                .stream()
                .map(h -> {
                    Crew crew = new Crew();
                    crew.setCrewNumber(h.getCrewNumber());
                    crew.setCrewType(h.getCrewType());
                    return crew;
                })
                .collect(Collectors.toList());
        ship.setCrew(crews);
        Ship entity = shipRepository.save(ship);
        ShipDTO result = mapper.convertValue(entity,ShipDTO.class);
        List<CrewDTO> crewsDTO = entity.getCrew()
                .stream()
                .map(h -> {
                    CrewDTO crewDTO = new CrewDTO();
                    crewDTO.setCrewNumber(h.getCrewNumber());
                    crewDTO.setCrewType(h.getCrewType());
                    return crewDTO;
                })
                .collect(Collectors.toList());
        result.setCrews(crewsDTO);
        return result;
    }

    @Override
    public String getShipComponents() {
        return "Engine";
    }

    @Override
    public Integer getEngineInfo() {
        return 153550;
    }
}
