package com.example.controllers;

import com.example.entities.Department;
import com.example.repositories.DepartmentRepository;
import com.example.services.DepartmentService;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@MicronautTest
class DepartmentControllerTest {

    @Inject
    DepartmentController departmentController;
    @Inject
    DepartmentService departmentService;
    @MockBean
            (DepartmentService.class)
    DepartmentService departmentService() {

        return mock(DepartmentService.class);
    };
    @Test
    void getDepartment() {
        when(departmentService.getDepartment()).thenReturn(Stream.of(new Department(),
                new Department(),
                new Department()).collect(Collectors.toList()));

        Assertions.assertEquals(3,departmentController.getDepartment().size());
    }

    @Test
    void addDepartment() {
        Department department = new Department(1, "Java");


        when(departmentService.addDepartment(department)).thenReturn(String.valueOf(department));

        String result = departmentController.addDepartment(department);

        assertEquals(result, departmentController.addDepartment(department));
    }

    @Test
    void update() {
        Department department = new Department( "Java");
        department.setDepartmentId(1);
        Department newDep = new Department("Aerospike");

        when(departmentService.update(department, department.getDepartmentId())).thenReturn(true);

        String result = departmentController.update(department, department.getDepartmentId());

        assertEquals(result, "Department updated successfully");

    }

    @Test
    void deleteById() {
        Department department = new Department( "Java");
        department.setDepartmentId(1);

        when(departmentService.deleteById(department.getDepartmentId())).thenReturn(String.valueOf(department));

        String result = departmentController.deleteById(1);

        assertEquals(result, departmentController.deleteById(department.getDepartmentId()));

    }
}