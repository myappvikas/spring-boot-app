package com.myapp;

import com.myapp.dto.EmployeeDTO;
import com.myapp.service.EmployeeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.time.LocalDateTime;

@SpringBootApplication
public class DemoWebAppApplication implements CommandLineRunner {

	private final EmployeeService employeeService;

	public DemoWebAppApplication(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoWebAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		/*EmployeeDTO dto = EmployeeDTO.builder()
				.email("vikas@gmail.com")
				.employeeName("Vikas")
				.age(35)
				.salary(75000d)
				.dateOfBirth(LocalDateTime.of(1990, 5, 15, 0, 0))
				.employeeId(1).
				build();
        EmployeeDTO savedEmployee = employeeService.save(dto);
        System.out.println(dto);*/

		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setEmail("vikas@gmail.com");
		employeeDTO.setEmployeeName("vikas");
		employeeDTO.setAge(32);
		employeeDTO.setSalary(100000d);
		employeeDTO.setDateOfBirth(LocalDateTime.now());
		EmployeeDTO savedEmployee = employeeService.save(employeeDTO);
		System.out.println(savedEmployee);
	}
}
