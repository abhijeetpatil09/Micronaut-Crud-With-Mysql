package com.example.controllers;

import com.example.entities.Department;

import com.example.services.DepartmentService;

import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;

import javax.transaction.Transactional;
import java.util.List;
@Controller("/department")
public class DepartmentController {

    @Inject
    DepartmentService departmentService;

    @Get("/show")
    public List<Department> getDepartment(){

        return departmentService.getDepartment();
    }

    @Post("/add")
    public String addDepartment(@Body Department department){

        return departmentService.addDepartment(department);
    }

    @Transactional
    @Put("/edit/{id}")
    public String update(@Body Department department, @PathVariable int id){
        boolean status = departmentService.update(department, department.getDepartmentId());
        if(status){
            return "Department updated successfully";
        }else {
            return "Department not found";
        }
    }

    @Delete("/delete/{id}")
    public String deleteById(@PathVariable int id){

        return departmentService.deleteById(id);
    }
}
