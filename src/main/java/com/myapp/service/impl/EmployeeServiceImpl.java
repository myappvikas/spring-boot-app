package com.myapp.service.impl;

import com.myapp.dto.EmployeeDTO;
import com.myapp.entity.Employee;
import com.myapp.repository.EmployeeRepository;
import com.myapp.service.EmployeeService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final ModelMapper modelMapper;
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(ModelMapper modelMapper,
                               EmployeeRepository employeeRepository) {
        this.modelMapper = modelMapper;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {

        if (employeeDTO == null) {
            throw new IllegalArgumentException("EmployeeDTO cannot be null");
        }
        Employee employee = modelMapper.map(employeeDTO, Employee.class);
        Employee savedEmployee = employeeRepository.save(employee);
        return modelMapper.map(savedEmployee, EmployeeDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeDTO> getAllEmployees() {

        return employeeRepository.findAll()
                .stream()
                .map(emp -> modelMapper.map(emp, EmployeeDTO.class))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeDTO findById(Long employeeId) {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Employee not found with id " + employeeId));
        return modelMapper.map(employee, EmployeeDTO.class);
    }

    @Override
    public EmployeeDTO updateEmployeeSalary(Long id, Double newSalary) {

        if (newSalary < 0) {
            throw new IllegalArgumentException("Salary cannot be negative");
        }
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Employee not found with id " + id));

        employee.setSalary(newSalary);
        Employee savedEmployee = employeeRepository.save(employee);

        return modelMapper.map(savedEmployee, EmployeeDTO.class);
    }

    @Override
    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new EntityNotFoundException("Employee not found with id " + id);
        }
        employeeRepository.deleteById(id);
    }
}
