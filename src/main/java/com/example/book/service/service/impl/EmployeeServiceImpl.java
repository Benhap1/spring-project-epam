package com.example.book.service.service.impl;

import com.example.book.service.dto.EmployeeCreateRequestDTO;
import com.example.book.service.dto.EmployeeDTO;
import com.example.book.service.exception.AlreadyExistException;
import com.example.book.service.exception.NotFoundException;
import com.example.book.service.model.Employee;
import com.example.book.service.repo.EmployeeRepository;
import com.example.book.service.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

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
                .orElseThrow(() -> new NotFoundException("Employee not found with email: " + email));
        return modelMapper.map(employee, EmployeeDTO.class);
    }

    @Override
    public EmployeeDTO updateEmployeeByEmail(String email, EmployeeDTO updatedDto) {
        Employee employee = employeeRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Employee not found with email: " + email));

        modelMapper.map(updatedDto, employee);
        Employee saved = employeeRepository.save(employee);

        return modelMapper.map(saved, EmployeeDTO.class);
    }

    @Override
    public void deleteEmployeeByEmail(String email) {
        Employee employee = employeeRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Employee not found with email: " + email));
        employeeRepository.delete(employee);
    }

    @Override
    public EmployeeDTO addEmployee(EmployeeCreateRequestDTO dto) {
        if (employeeRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new AlreadyExistException("Employee already exists with email: " + dto.getEmail());
        }

        Employee employee = Employee.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .birthDate(dto.getBirthDate())
                .phone(dto.getPhone())
                .enabled(true)
                .build();

        Employee saved = employeeRepository.save(employee);
        return modelMapper.map(saved, EmployeeDTO.class);
    }
}