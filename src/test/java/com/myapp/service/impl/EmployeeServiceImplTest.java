package com.myapp.service.impl;

import com.myapp.dto.EmployeeDTO;
import com.myapp.entity.Employee;
import com.myapp.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    void testSave() {

        EmployeeDTO inputDto = new EmployeeDTO();
        Employee employee = new Employee();
        Employee savedEmployee = new Employee();
        EmployeeDTO outputDto = new EmployeeDTO();

        when(modelMapper.map(inputDto, Employee.class)).thenReturn(employee);
        when(employeeRepository.save(employee)).thenReturn(savedEmployee);
        when(modelMapper.map(savedEmployee, EmployeeDTO.class)).thenReturn(outputDto);

        EmployeeDTO result = employeeService.save(inputDto);
        assertNotNull(result);
    }
}
