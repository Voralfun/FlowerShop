package com.example.flowershop.controller;

import com.example.flowershop.common.ApiResponse;
import com.example.flowershop.model.dto.EmployeeDTO;
import com.example.flowershop.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        employeeService.create(employeeDTO);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Сотрудник успешно добавлен"), HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        List<EmployeeDTO> flowers = employeeService.getAllEmployees();
        return new ResponseEntity<>(flowers, HttpStatus.OK);
    }
    @PostMapping("/update/{employeeId}")
    public ResponseEntity<ApiResponse> updateEmployee(@PathVariable("employeeId") Long employeeId, @RequestBody EmployeeDTO employeeDTO){
        employeeService.update(employeeDTO, employeeId);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Информация о сотруднике была обновлена"), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{employeeId}")
    public ResponseEntity<ApiResponse> delete(@PathVariable("employeeId") Long itemId) {
        employeeService.delete(itemId);
        return new ResponseEntity<>(new ApiResponse(true, "Информация о сотруднике была удалена"), HttpStatus.OK);
    }
}

