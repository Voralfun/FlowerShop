package com.example.demo.controllers;

import com.example.demo.model.dto.CrewDTORequest;
import com.example.demo.model.dto.CrewDTOResponse;
import com.example.demo.service.CrewService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/crew")
@RequiredArgsConstructor
public class CrewController {
    private final CrewService crewService;
    @PostMapping
    @Operation(summary = "Создание экипажа")
    public CrewDTORequest createCrew(@RequestBody CrewDTORequest crewDTORequest){
        return crewService.create(crewDTORequest);
    }
    @PutMapping
    public ResponseEntity<CrewDTORequest> updateCrew(@RequestBody CrewDTORequest crewDTORequest){
        return ResponseEntity.ok(crewService.update(crewDTORequest));
    }
    @GetMapping
    public ResponseEntity<CrewDTORequest> getCrew(@RequestParam(name = "task",required = true) String task){
        return ResponseEntity.ok(crewService.get(task));
    }
    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteCrew(@RequestParam String task){
        crewService.delete(task);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/crewToShip")
    public ResponseEntity<CrewDTOResponse> addToShip(@RequestParam String task, @RequestParam String serialNUM){
        return ResponseEntity.ok(crewService.addToShip(task,serialNUM));
    }
    @GetMapping("/all")
    public ModelMap getAllCrewList(@RequestParam(required = false,defaultValue = "1") Integer page, @RequestParam(required = false,defaultValue = "10") Integer perPage, @RequestParam(required = false,defaultValue = "name") String sort, @RequestParam(required = false,defaultValue = "ASC") Sort.Direction order){
        return crewService.getAllCrewList(page,perPage,sort,order);
    }
}
