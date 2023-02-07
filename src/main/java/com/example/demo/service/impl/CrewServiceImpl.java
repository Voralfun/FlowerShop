package com.example.demo.service.impl;

import com.example.demo.exceptions.CustomException;
import com.example.demo.model.dto.CrewDTORequest;
import com.example.demo.model.dto.CrewDTOResponse;
import com.example.demo.model.dto.ShipDTO;
import com.example.demo.model.entity.Crew;
import com.example.demo.model.entity.Ship;
import com.example.demo.model.enums.CrewStatus;
import com.example.demo.model.repository.CrewRepository;
import com.example.demo.service.CrewService;
import com.example.demo.service.ShipService;
import com.example.demo.utils.PaginationUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CrewServiceImpl implements CrewService {
    private final ShipService shipService;
    private final CrewRepository crewRepository;
    private ObjectMapper mapper;
    @Override
    public CrewDTORequest create(CrewDTORequest crewDTORequest) {
        crewRepository.findByTask(crewDTORequest.getTask()).ifPresent(
                h -> {
                    throw new CustomException("This task has already been set", HttpStatus.BAD_REQUEST);}
        );
        Crew crew = mapper.convertValue(crewDTORequest,Crew.class);
        crewRepository.save(crew);
        return mapper.convertValue(crew, CrewDTORequest.class);
    }
    @Override
    public CrewDTORequest update(CrewDTORequest crewDTORequest) {
        Crew crew = getCrew(crewDTORequest.getTask());
        crew.setCrewNumber(crewDTORequest.getCrewNumber() == null ? crew.getCrewNumber(): crewDTORequest.getCrewNumber());
        crew.setCrewType(crewDTORequest.getCrewType() == null ? crew.getCrewType() : crewDTORequest.getCrewType());
        crew.setUpdatedAt(LocalDateTime.now());
        crew.setStatus(CrewStatus.UPDATED);
        Crew save = crewRepository.save(crew);
        return mapper.convertValue(crewRepository.save(crew), CrewDTORequest.class);
    }

    @Override
    public CrewDTORequest get(String task) {
        return mapper.convertValue(getCrew(task), CrewDTORequest.class);
    }

    @Override
    public void delete(String task) {
        Crew crew = getCrew(task);
        crew.setStatus(CrewStatus.DELETED);
        crew.setUpdatedAt(LocalDateTime.now());
        crewRepository.save(crew);
    }
    private Crew getCrew(String crewDTO) {
        return crewRepository.findByTask(crewDTO)
                .orElseThrow(() -> new CustomException("The specified task was not found", HttpStatus.NOT_FOUND));
    }
    @Override
    public CrewDTOResponse addToShip(String task, String serialNUM) {
        Ship ship = shipService.getShip(serialNUM);
        Crew crew = getCrew(task);
        crew.setShip(ship);
        Crew save = crewRepository.save(crew);
        CrewDTOResponse response = mapper.convertValue(save,CrewDTOResponse.class);
        response.setShipDTO(mapper.convertValue(ship, ShipDTO.class));
        return response;
    }
    @Override
    public ModelMap getAllCrewList(Integer page, Integer perPage, String sort, Sort.Direction order) {
        Pageable pageRequest = PaginationUtil.getPageRequest(page, perPage, sort, order);
        Page<Crew> pageResult = crewRepository.findAll(pageRequest);
        List<CrewDTORequest> content = pageResult.getContent().stream().map(h -> mapper.convertValue(h, CrewDTORequest.class)).collect(Collectors.toList());
        PagedListHolder<CrewDTORequest> result = new PagedListHolder<>(content);
        result.setPage(page);
        result.setPageSize(perPage);
        ModelMap map = new ModelMap();
        map.addAttribute("content", result.getPageList());
        map.addAttribute("pageNumber", page);
        map.addAttribute("pageSize", result.getPageSize());
        map.addAttribute("totalPages", result.getPageCount());
        return map;
    }
}
