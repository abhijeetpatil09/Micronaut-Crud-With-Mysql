package com.example.services;

import com.example.entities.Department;
import com.example.entities.Employee;
import com.example.repositories.EmployeeRepository;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

@MicronautTest
class EmployeeServiceTest {

    @Inject
    EmployeeRepository employeeRepository;

    @Inject
    EmployeeService employeeService;

    @MockBean
    (EmployeeRepository.class)
    EmployeeRepository employeeRepository() {

        return mock(EmployeeRepository.class);
    };

    @Test
    void getEmployees() {

        when(employeeRepository.findAll()).thenReturn(Stream.of(new Employee(),
                new Employee(),
                new Employee()).collect(Collectors.toList()));

        Assertions.assertEquals(3,employeeService.getEmployees().size());
    }

    @Test
    void addEmployee() {

        Department department = new Department(1, "Java");
        Employee employee = new Employee("Abhi",25,40000,"abhi@gmail.com",new Date(), department);

        when(employeeRepository.save(employee)).thenReturn(employee);

        String result = employeeService.addEmployee(employee);

        assertEquals(result, "Employee added successfully");
    }


    @Test
    void update() {

        Department department = new Department(1);

        Employee employee = new Employee("Abhi",25,35665,"abhi@gmail.com",new Date(), department);
        employee.setId(1);
        Employee newEmp= new Employee("Abhijeet Patil",25,40000,"abhi@gmail.com",new Date(), department);

        when(employeeRepository.findById(employee.getId())).thenReturn(Optional.of(newEmp));

        boolean result = employeeService.update(employee, 1);

        assertEquals(result, true);

    }

    @Test
    void deleteById() {
        Department department = new Department(1, "Java");

        Employee employee = new Employee("Abhi",25,35665,"abhi@gmail.com",new Date(), department);
        employee.setId(1);

        when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));

        String result = employeeService.deleteById(1);

        assertEquals(result, "Deleted employee by id "+employee.getId());

    }
}