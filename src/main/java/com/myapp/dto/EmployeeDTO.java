package com.myapp.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDTO {

    private int employeeId;

    @NotBlank(message = "Employee name is required")
    @Size(min = 2, max = 50, message = "Employee name must be between 2 and 50 characters")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Employee name must contain only letters and spaces")
    private String employeeName;

    @Min(value = 18, message = "Age must be at least 18")
    @Max(value = 65, message = "Age must not exceed 65")
    private int age;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email address")
    private String email;

    @PositiveOrZero(message = "Salary must be zero or positive")
    @DecimalMin(value = "10000.00", message = "Salary must be at least 10,000")
    @DecimalMax(value = "1000000.00", message = "Salary must not exceed 1,000,000")
    private double salary;

    @Past(message = "Date of birth must be in the past")
    @NotNull(message = "Date of birth is required")
    private LocalDateTime dateOfBirth;
}
