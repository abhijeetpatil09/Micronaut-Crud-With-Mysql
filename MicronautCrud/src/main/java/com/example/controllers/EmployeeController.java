package com.example.controllers;

import com.example.entities.Employee;
import com.example.repositories.EmployeeRepository;
import com.example.services.EmployeeService;
import com.example.services.KafkaServices;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;

import javax.transaction.Transactional;
import java.util.List;

@Controller("/employee")
public class EmployeeController {

    @Inject
    EmployeeService employeeService;
    @Inject
    KafkaServices kafkaServices;
    @Inject
    EmployeeRepository employeeRepository;

    @Get("/show")
    public List<Employee> getEmployees(){

        return employeeService.getEmployees();
    }

    @Post("/add")
    public String addEmployee(@Body Employee employee){
        kafkaServices.insert(employee);
        return employeeService.addEmployee(employee);

    }

    @Transactional
    @Put("/edit/{id}")
    public String update(@Body Employee employee, @PathVariable int id){
        boolean status = employeeService.update(employee, id);
        if(status){
            kafkaServices.update(employee);
            return "Employee updated successfully";
        }else {
            return "Employee not found";
        }
    }

    @Delete("/delete/{id}")
    public String deleteById(@PathVariable int id){

        return employeeService.deleteById(id);
    }
}
