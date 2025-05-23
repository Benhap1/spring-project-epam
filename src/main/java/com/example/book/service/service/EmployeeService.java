package com.example.book.service.service;

import com.example.book.service.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDTO> getAllEmployees();

    EmployeeDTO getEmployeeByEmail(String email);

    EmployeeDTO updateEmployeeByEmail(String email, EmployeeDTO employee);

    void deleteEmployeeByEmail(String email);

    EmployeeDTO addEmployee(EmployeeDTO employee);
}
