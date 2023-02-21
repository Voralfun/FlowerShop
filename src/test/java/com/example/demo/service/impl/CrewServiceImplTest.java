package com.example.demo.service.impl;

import com.example.demo.exceptions.CustomException;
import com.example.demo.model.dto.CrewDTORequest;
import com.example.demo.model.dto.CrewDTOResponse;
import com.example.demo.model.dto.ShipDTO;
import com.example.demo.model.entity.Crew;
import com.example.demo.model.entity.Ship;
import com.example.demo.model.repository.CrewRepository;
import com.example.demo.service.ShipService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CrewServiceImplTest {
    @InjectMocks
    private CrewServiceImpl crewService;
    @Mock
    private  CrewRepository crewRepository;
    @Spy
    private  ObjectMapper mapper;

    @Test
    public void create() {
        CrewDTORequest request = new CrewDTORequest();
        request.setTask("Clean ship");
        when(crewRepository.save(any(Crew.class))).thenReturn(any(Crew.class));
        CrewDTORequest result = crewService.create(request);
        assertEquals(request.getTask() , result.getTask());
    }
    @Test(expected = CustomException.class)

    public void create_exception() {
        CrewDTORequest request = new CrewDTORequest();
        request.setTask("Clean ship");

        when(crewRepository.findByTask(anyString())).thenThrow(CustomException.class);
        crewService.create(request);

    }
    @Test
    public void update() {
    }

    @Test
    public void get() {
    }

    @Test
    public void delete() {
        Crew crew = new Crew();
        crew.setTask("Clean ship");
        when(crewRepository.findByTask(anyString())).thenReturn(Optional.of(crew));
        when(crewRepository.save(any(Crew.class))).thenReturn(any(Crew.class));
        crewService.delete("Clean ship");
    }

    @Test
    public void addToShip() {
        ShipService shipService = mock(ShipService.class);
        Ship ship = new Ship();
        ship.setSerialNUM("xxx");
        when(shipService.getShip(anyString())).thenReturn(ship);
        CrewDTOResponse building = crewService.addToShip("Repair ship", "xxx");
        building.getShipDTO().getSerialNUM();
    }

    @Test
    public void getAllCrewList() {
    }
}