package com.myapp.repository;

import com.myapp.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    Optional<Employee> findByEmployeeNameAndEmail(String employeeName, String email);

    Optional<Employee> findByEmployeeNameOrEmail(String employeeName, String email);

    List<Employee> findByAge(int age);

    @Query(value = "SELECT * FROM employees WHERE salary = ?1", nativeQuery = true)
    List<Employee> getEmployeesBySalary(double salary);

    @Query("FROM Employee e WHERE e.employeeName = ?1")
    List<Employee> getEmployeesByEmployeeName(String employeeName);
}
