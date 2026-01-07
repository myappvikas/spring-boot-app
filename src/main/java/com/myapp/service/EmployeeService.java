package com.myapp.service;

import com.myapp.dto.EmployeeDTO;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    EmployeeDTO save(EmployeeDTO employeeDTO);

    EmployeeDTO findById(int employeeId);

    List<EmployeeDTO> getAllEmployees();

    EmployeeDTO updateEmployeeSalary(int id, double salary);

    void deleteEmployee(int id);
}
