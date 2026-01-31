package com.myapp.service;

import com.myapp.dto.EmployeeDTO;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);

    EmployeeDTO findById(Long employeeId);

    List<EmployeeDTO> getAllEmployees();

    EmployeeDTO updateEmployeeSalary(Long id, Double salary);

    void deleteEmployee(Long id);
}
