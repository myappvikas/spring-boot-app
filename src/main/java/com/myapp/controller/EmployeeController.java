package com.myapp.controller;

import com.myapp.dto.EmployeeDTO;
import com.myapp.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Operation(
            summary = "To create an employee",
            description = "Create a new employee in this system",
            responses = {
                    @ApiResponse(responseCode = "201", description = "User created"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid EmployeeDTO employeeDTO){
        EmployeeDTO savedEmployee = employeeService.save(employeeDTO);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get all employees",
            description = "Fetches all existing employee information",
            responses = {
                    @ApiResponse(responseCode = "200", description = "All Users"),
                    @ApiResponse(responseCode = "404", description = "Resource not found")
            }
    )
    @GetMapping
    public ResponseEntity<?> getAllEmployees(){
        List<EmployeeDTO> allEmployees = employeeService.getAllEmployees();
        return new ResponseEntity<>(allEmployees, HttpStatus.OK);
    }

    @Operation(
            summary = "Get Employee by ID",
            description = "Fetches a employee based on the provided ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User found"),
                    @ApiResponse(responseCode = "404", description = "User not found")
            }
    )
    @GetMapping("/{employeeId}")
    public ResponseEntity<?> getEmployeeById(@PathVariable int employeeId){
        EmployeeDTO employeeDTO = employeeService.findById(employeeId);
        return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
    }

    @Operation(
            summary = "Update employee info",
            description = "To update an existing employee information",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Update user salary"),
                    @ApiResponse(responseCode = "404", description = "Resource not found")
            }
    )
    @PutMapping("/{employeeId}/{salary}")
    public ResponseEntity<?> updateEmployeeSalary(@PathVariable int employeeId, @PathVariable
                                                                      double salary){
        EmployeeDTO employees = employeeService.updateEmployeeSalary(employeeId, salary);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @Operation(
            summary = "Delete an employee",
            description = "To delete an employee information from organization",
            responses = {
                    @ApiResponse(responseCode = "204", description = "No content"),
                    @ApiResponse(responseCode = "404", description = "Resource not found")
            }
    )
    @DeleteMapping("/{employeeId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable int employeeId){
        employeeService.deleteEmployee(employeeId);
        return new ResponseEntity<>("Resource has been deleted successfully", HttpStatus.OK);
    }
}