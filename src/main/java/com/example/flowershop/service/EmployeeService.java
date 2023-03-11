package com.example.flowershop.service;

import com.example.flowershop.exceptions.FlowerServiceException;
import com.example.flowershop.model.dto.EmployeeDTO;
import com.example.flowershop.model.entity.Employee;

import java.util.List;

public interface EmployeeService {
    void create(EmployeeDTO employeeDTO);

    void update(EmployeeDTO employeeDTO, Long employeeId) throws FlowerServiceException;

    void delete(Long employeeId);

    EmployeeDTO getEmployeeDTO(Employee employee);

    List<EmployeeDTO> getAllEmployees();
}
