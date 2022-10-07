package com.example.controllers;

import com.example.entities.Department;
import com.example.entities.Employee;
import com.example.services.EmployeeService;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@MicronautTest
class EmployeeControllerTest {

    @Inject
    EmployeeService employeeService;

    @Inject
    EmployeeController employeeController;

    @MockBean(EmployeeService.class)
    EmployeeService employeeService() {

        return mock(EmployeeService.class);
    };

    @Test
    void getEmployees() {
        when(employeeService.getEmployees()).thenReturn(Stream.of(new Employee(),
                new Employee(),
                new Employee()).collect(Collectors.toList()));

        Assertions.assertEquals(3,employeeController.getEmployees().size());
    }

    @Test
    void addEmployee() {
        Department department = new Department(1, "Java");
        Employee employee = new Employee("Abhi",25,40000,"abhi@gmail.com",new Date(), department);

        when(employeeService.addEmployee(employee)).thenReturn(String.valueOf(employee));

        String result = employeeController.addEmployee(employee);

        assertEquals(result, employeeController.addEmployee(employee));
    }

    @Test
    void update() {
        Department department = new Department(1);

        Employee employee = new Employee("Abhi",25,35665,"abhi@gmail.com",new Date(), department);
        employee.setId(1);
        Employee newEmp= new Employee("Abhijeet Patil",25,40000,"abhi@gmail.com",new Date(), department);

        when(employeeService.update(employee, employee.getId())).thenReturn(true);


        String result = employeeController.update(employee, employee.getId());

        assertEquals(result, "Employee updated successfully");
    }

    @Test
    void deleteById() {
        Department department = new Department(1);

        Employee employee = new Employee("Abhi",25,35665,"abhi@gmail.com",new Date(), department);
        employee.setId(1);

        when(employeeService.deleteById(employee.getId())).thenReturn(String.valueOf(employee));

        String result = employeeController.deleteById(1);

        assertEquals(result, employeeController.deleteById(employee.getId()));
    }
}