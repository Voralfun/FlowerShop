package com.example.demo.service;

import com.example.demo.model.dto.CrewDTORequest;
import com.example.demo.model.dto.CrewDTOResponse;
import org.springframework.data.domain.Sort;
import org.springframework.ui.ModelMap;


public interface CrewService {
    CrewDTORequest create(CrewDTORequest crewDTORequest);
    CrewDTORequest update(CrewDTORequest crewDTORequest);
    CrewDTORequest get(String task);
    void delete(String task);

    CrewDTOResponse addToShip(String task, String serialNUM);
    ModelMap getAllCrewList(Integer page, Integer perPage, String sort, Sort.Direction order);
}
