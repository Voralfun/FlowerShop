package com.example.flowershop.service.impl;

import com.example.flowershop.exceptions.FlowerServiceException;
import com.example.flowershop.model.dto.EmployeeDTO;
import com.example.flowershop.model.entity.Employee;
import com.example.flowershop.model.entity.enums.Status;
import com.example.flowershop.model.repository.EmployeeRepository;
import com.example.flowershop.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public void create(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setSurname(employeeDTO.getSurname());
        employee.setPatronymic(employeeDTO.getPatronymic());
        employee.setRegion(employee.getRegion());
        employee.setAdress(employeeDTO.getAdress());
        employee.setEmail(employeeDTO.getEmail());
        employee.setPhoneNUM(employeeDTO.getPhoneNUM());
        employee.setStatus(Status.valueOf(String.valueOf(Status.CREATED)));
        employee.setCreatedAt(LocalDateTime.now());
        employeeRepository.save(employee);
    }
    @Override
    public void update(EmployeeDTO employeeDTO, Long employeeId) throws FlowerServiceException {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        Employee employee = optionalEmployee.get();
        employee.setName(employeeDTO.getName());
        employee.setSurname(employeeDTO.getSurname());
        employee.setPatronymic(employeeDTO.getPatronymic());
        employee.setRegion(employeeDTO.getRegion());
        employee.setAdress(employeeDTO.getAdress());
        employee.setPhoneNUM(employeeDTO.getPhoneNUM());
        employee.setUpdatedAt(LocalDateTime.now());
        employeeRepository.save(employee);
    }
    @Override
    public void delete(Long employeeId) {

        Optional<Employee> optionalEmployee =employeeRepository.findById(employeeId);

        if (optionalEmployee.isEmpty()) {
            throw new FlowerServiceException("Неверный id: " + employeeId);
        }

        Employee employee = optionalEmployee.get();

        employeeRepository.delete(employee);
    }
    @Override
    public EmployeeDTO getEmployeeDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setSurname(employee.getSurname());
        employeeDTO.setPatronymic(employee.getPatronymic());
        employeeDTO.setRegion(employee.getRegion());
        employeeDTO.setAdress(employee.getAdress());
        employeeDTO.setPhoneNUM(employee.getPhoneNUM());
        return employeeDTO;
    }
    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> allEmployees = employeeRepository.findAll();

        List<EmployeeDTO> employeedto = new ArrayList<>();
        for(Employee employee: allEmployees) {
            employeedto.add(getEmployeeDTO(employee));
        }
        return employeedto;
    }
}
