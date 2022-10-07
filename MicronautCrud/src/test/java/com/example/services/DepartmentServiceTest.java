package com.example.services;

import com.example.entities.Department;
import com.example.repositories.DepartmentRepository;
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
class DepartmentServiceTest {
    @Inject
    DepartmentService departmentService;
    @Inject
    DepartmentRepository departmentRepository;
    @MockBean
            (DepartmentRepository.class)
    DepartmentRepository departmentRepository() {

        return mock(DepartmentRepository.class);
    };
    @Test
    void getDepartment() {
        when(departmentRepository.findAll()).thenReturn(Stream.of(new Department(),
                new Department(),
                new Department()).collect(Collectors.toList()));

        Assertions.assertEquals(3,departmentService.getDepartment().size());
    }

    @Test
    void addDepartment() {
        Department department = new Department(1, "Java");


        when(departmentRepository.save(department)).thenReturn(department);

        String result = departmentService.addDepartment(department);

        assertEquals(result, "Department added successfully");
    }

    @Test
    void update() {
        Department department = new Department( "Java");
        department.setDepartmentId(1);
        Department newDep = new Department("Aerospike");

        when(departmentRepository.findById(1)).thenReturn(Optional.of(newDep));

        boolean result = departmentService.update(department, department.getDepartmentId());

        assertEquals(result, true);

    }

    @Test
    void deleteById() {
        Department department = new Department( "Java");
        department.setDepartmentId(1);

        when(departmentRepository.findById(1)).thenReturn(Optional.of(department));

        String result = departmentService.deleteById(1);

        assertEquals(result, "Deleted department by id "+department.getDepartmentId());

    }
}
