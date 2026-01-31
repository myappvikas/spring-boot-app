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

        EmployeeDTO inputDto = getEmployeeDTO();
        EmployeeDTO outputDto = getEmployeeDTO();

        Employee inputEmployeeEntity = getEmployeeEntity();
        Employee outputEmployeeEntity = getEmployeeEntity();

        when(modelMapper.map(inputDto, Employee.class)).thenReturn(inputEmployeeEntity);
        when(employeeRepository.save(inputEmployeeEntity)).thenReturn(outputEmployeeEntity);
        when(modelMapper.map(outputEmployeeEntity, EmployeeDTO.class)).thenReturn(outputDto);

        EmployeeDTO result = employeeService.createEmployee(inputDto);
        assertNotNull(result);
        assertEquals("test",result.getEmployeeName());
        assertEquals("test@gmail.com",result.getEmail());
    }
    @Test
    void testFindById() {

        EmployeeDTO outputDto = getEmployeeDTO();
        Employee outputEmployeeEntity = getEmployeeEntity();

        when(employeeRepository.findById(1L))
                .thenReturn(Optional.of(outputEmployeeEntity));
        when(modelMapper.map(outputEmployeeEntity, EmployeeDTO.class))
                .thenReturn(outputDto);

        EmployeeDTO result = employeeService.findById(1L);

        assertNotNull(result);
        assertEquals("test", result.getEmployeeName());
        assertEquals("test@gmail.com", result.getEmail());

        verify(employeeRepository).findById(1L);
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
        employee.setSalary(50000D);

        EmployeeDTO employeeDTO = getEmployeeDTO();
        employeeDTO.setSalary(60000D);

        Employee savedEmployee = getEmployeeEntity();
        savedEmployee.setSalary(60000D);

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        // use doReturn() stubbing to avoid strict stubbing mismatches
        doReturn(savedEmployee).when(employeeRepository).save(any(Employee.class));
        doReturn(employeeDTO).when(modelMapper).map(any(Employee.class), eq(EmployeeDTO.class));

        EmployeeDTO result = employeeService.updateEmployeeSalary(1L, 60000D);

        assertNotNull(result);
        assertEquals(60000D, result.getSalary());

        verify(employeeRepository).findById(1L);
        verify(employeeRepository).save(any(Employee.class));
        verify(modelMapper).map(any(Employee.class), eq(EmployeeDTO.class));
    }

    @Test
    void testDeleteEmployee_Success() {

        when(employeeRepository.existsById(1L))
                .thenReturn(true);

        employeeService.deleteEmployee(1L);
        verify(employeeRepository).existsById(1L);
        verify(employeeRepository).deleteById(1L);
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