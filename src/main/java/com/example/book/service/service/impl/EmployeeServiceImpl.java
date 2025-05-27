package com.example.book.service.service.impl;

import com.example.book.service.dto.EmployeeDTO;
import com.example.book.service.exception.ResourceNotFoundException;
import com.example.book.service.model.Employee;
import com.example.book.service.repo.EmployeeRepository;
import com.example.book.service.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(employee -> modelMapper.map(employee, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO getEmployeeByEmail(String email) {
        Employee employee = employeeRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with email: " + email));
        return modelMapper.map(employee, EmployeeDTO.class);
    }

    @Override
    public EmployeeDTO updateEmployeeByEmail(String email, EmployeeDTO updatedDto) {
        Employee employee = employeeRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with email: " + email));

        modelMapper.map(updatedDto, employee);

        Employee saved = employeeRepository.save(employee);
        return modelMapper.map(saved, EmployeeDTO.class);
    }

    @Override
    public void deleteEmployeeByEmail(String email) {
        Employee employee = employeeRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with email: " + email));
        employeeRepository.delete(employee);
    }

    @Override
    public EmployeeDTO addEmployee(EmployeeDTO dto) {
        Employee employee = modelMapper.map(dto, Employee.class);
        Employee saved = employeeRepository.save(employee);
        return modelMapper.map(saved, EmployeeDTO.class);
    }
}
