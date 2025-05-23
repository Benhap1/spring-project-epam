package com.example.book.service.service.impl;

import com.example.book.service.dto.EmployeeDTO;
import com.example.book.service.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return List.of();
    }

    @Override
    public EmployeeDTO getEmployeeByEmail(String email) {
        return null;
    }

    @Override
    public EmployeeDTO updateEmployeeByEmail(String email, EmployeeDTO employee) {
        return null;
    }

    @Override
    public void deleteEmployeeByEmail(String email) {

    }

    @Override
    public EmployeeDTO addEmployee(EmployeeDTO employee) {
        return null;
    }
}
