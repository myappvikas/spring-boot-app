package com.myapp.service.impl;

import com.myapp.dto.EmployeeDTO;
import com.myapp.entity.Employee;
import com.myapp.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import java.time.LocalDateTime;

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

        EmployeeDTO inputDto = getEmployeeDTO();
        EmployeeDTO outputDto = getEmployeeDTO();

        Employee inputEmployeeEntity = getEmployeeEntity();
        Employee outputEmployeeEntity = getEmployeeEntity();

        when(modelMapper.map(inputDto, Employee.class)).thenReturn(inputEmployeeEntity);
        when(employeeRepository.save(inputEmployeeEntity)).thenReturn(outputEmployeeEntity);
        when(modelMapper.map(outputEmployeeEntity, EmployeeDTO.class)).thenReturn(outputDto);

        EmployeeDTO result = employeeService.save(inputDto);
        assertNotNull(result);
        assertEquals("test",result.getEmployeeName());
        assertEquals("test@gmail.com",result.getEmail());
    }
    private static Employee getEmployeeEntity() {
        Employee employee = new Employee();
        employee.setEmail("test@gmail.com");
        employee.setEmployeeName("test");
        employee.setAge(1);
        employee.setSalary(1d);
        employee.setDateOfBirth(LocalDateTime.of(2026, 1, 1,
                1, 1));
        return employee;
    }

    private static EmployeeDTO getEmployeeDTO(){
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmail("test@gmail.com");
        employeeDTO.setEmployeeName("test");
        employeeDTO.setAge(1);
        employeeDTO.setSalary(1d);
        employeeDTO.setDateOfBirth(LocalDateTime.of(2026, 1, 1,
                1, 1));
        return employeeDTO;
    }
}