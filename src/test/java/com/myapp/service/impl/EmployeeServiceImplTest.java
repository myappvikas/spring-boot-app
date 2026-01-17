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
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
    @Test
    void testFindById() {

        EmployeeDTO outputDto = getEmployeeDTO();
        Employee outputEmployeeEntity = getEmployeeEntity();

        when(employeeRepository.findById(1))
                .thenReturn(Optional.of(outputEmployeeEntity));
        when(modelMapper.map(outputEmployeeEntity, EmployeeDTO.class))
                .thenReturn(outputDto);

        EmployeeDTO result = employeeService.findById(1);

        assertNotNull(result);
        assertEquals("test", result.getEmployeeName());
        assertEquals("test@gmail.com", result.getEmail());

        verify(employeeRepository).findById(1);
        verify(modelMapper).map(outputEmployeeEntity, EmployeeDTO.class);
    }

    @Test
    void testGetAllEmployees() {

        Employee employee = getEmployeeEntity();
        EmployeeDTO employeeDTO = getEmployeeDTO();

        when(employeeRepository.findAll())
                .thenReturn(List.of(employee));
        when(modelMapper.map(employee, EmployeeDTO.class))
                .thenReturn(employeeDTO);

        List<EmployeeDTO> result = employeeService.getAllEmployees();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("test", result.getFirst().getEmployeeName());
        assertEquals("test@gmail.com", result.getFirst().getEmail());

        verify(employeeRepository).findAll();
        verify(modelMapper).map(employee, EmployeeDTO.class);
    }

    @Test
    void testUpdateEmployeeSalary() {
        Employee employee = getEmployeeEntity();
        employee.setSalary(50000);

        EmployeeDTO employeeDTO = getEmployeeDTO();
        employeeDTO.setSalary(60000);

        when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));
        when(modelMapper.map(employee, EmployeeDTO.class)).thenReturn(employeeDTO);
        EmployeeDTO result = employeeService.updateEmployeeSalary(1, 60000);

        // Assert
        assertNotNull(result);
        assertEquals(60000, result.getSalary());

        verify(employeeRepository).findById(1);
        verify(modelMapper).map(employee, EmployeeDTO.class);
        verify(employeeRepository, Mockito.never()).save(Mockito.any());
    }

    @Test
    void testDeleteEmployee_Success() {

        when(employeeRepository.existsById(1))
                .thenReturn(true);

        employeeService.deleteEmployee(1);
        verify(employeeRepository).existsById(1);
        verify(employeeRepository).deleteById(1);
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