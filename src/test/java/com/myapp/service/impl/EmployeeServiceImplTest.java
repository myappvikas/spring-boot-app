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

        EmployeeDTO dto = buildEmployeeDTO();
        Employee entity = buildEmployeeEntity();

        when(modelMapper.map(dto, Employee.class)).thenReturn(entity);
        when(employeeRepository.save(entity)).thenReturn(entity);
        when(modelMapper.map(entity, EmployeeDTO.class)).thenReturn(dto);

        EmployeeDTO result = employeeService.createEmployee(dto);

        assertNotNull(result);
        assertEquals("test", result.getEmployeeName());
        assertEquals("test@gmail.com", result.getEmail());
    }

    @Test
    void testFindById() {

        EmployeeDTO dto = buildEmployeeDTO();
        Employee entity = buildEmployeeEntity();

        when(employeeRepository.findById(1L))
                .thenReturn(Optional.of(entity));
        when(modelMapper.map(entity, EmployeeDTO.class))
                .thenReturn(dto);

        EmployeeDTO result = employeeService.findById(1L);

        assertNotNull(result);
        assertEquals("test", result.getEmployeeName());
        assertEquals("test@gmail.com", result.getEmail());

        verify(employeeRepository).findById(1L);
        verify(modelMapper).map(entity, EmployeeDTO.class);
    }

    @Test
    void testGetAllEmployees() {

        Employee entity = buildEmployeeEntity();
        EmployeeDTO dto = buildEmployeeDTO();

        when(employeeRepository.findAll())
                .thenReturn(List.of(entity));
        when(modelMapper.map(entity, EmployeeDTO.class))
                .thenReturn(dto);

        List<EmployeeDTO> result = employeeService.getAllEmployees();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("test", result.getFirst().getEmployeeName());
        assertEquals("test@gmail.com", result.getFirst().getEmail());

        verify(employeeRepository).findAll();
        verify(modelMapper).map(entity, EmployeeDTO.class);
    }

    @Test
    void testUpdateEmployeeSalary() {

        Employee entity = buildEmployeeEntity();
        entity.setSalary(50000D);

        EmployeeDTO dto = buildEmployeeDTO();
        dto.setSalary(60000D);

        Employee savedEmployee = buildEmployeeEntity();
        savedEmployee.setSalary(60000D);

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(entity));
        doReturn(savedEmployee).when(employeeRepository).save(any(Employee.class));
        doReturn(dto).when(modelMapper).map(any(Employee.class), eq(EmployeeDTO.class));

        EmployeeDTO result = employeeService.updateEmployeeSalary(1L, 60000D);

        assertNotNull(result);
        assertEquals(60000D, result.getSalary());

        verify(employeeRepository).findById(1L);
        verify(employeeRepository).save(any(Employee.class));
        verify(modelMapper).map(any(Employee.class), eq(EmployeeDTO.class));
    }

    @Test
    void testDeleteEmployee() {

        when(employeeRepository.existsById(1L))
                .thenReturn(true);

        employeeService.deleteEmployee(1L);
        verify(employeeRepository).existsById(1L);
        verify(employeeRepository).deleteById(1L);
    }

    private static Employee buildEmployeeEntity() {
        Employee employee = new Employee();
        employee.setEmployeeName("test");
        employee.setAge(1);
        employee.setSalary(1d);
        employee.setEmail("test@gmail.com");
        employee.setDateOfBirth(LocalDateTime.of(2026, 1, 1,
                1, 1));
        return employee;
    }

    private static EmployeeDTO buildEmployeeDTO() {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmployeeName("test");
        employeeDTO.setAge(1);
        employeeDTO.setSalary(1d);
        employeeDTO.setEmail("test@gmail.com");
        employeeDTO.setDateOfBirth(LocalDateTime.of(2026, 1, 1,
                1, 1));
        return employeeDTO;
    }
}