package com.example.services;


import com.example.entities.Department;
import com.example.repositories.DepartmentRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;

@Singleton
public class DepartmentService {
    @Inject
    DepartmentRepository departmentRepository;

    public List<Department> getDepartment(){

        return departmentRepository.findAll();
    }

    public String addDepartment(Department department){

        Department d = departmentRepository.save(department);
        if(d != null){
            return "Department added successfully";
        }
        else{
            return "Department not added !!!";
        }
    }


    public boolean update(Department department, int id){
        Optional<Department> optional = departmentRepository.findById(id);
        Department e = optional.get();

        if(e != null){
            e.setDepartmentId(department.getDepartmentId());
            e.setDepartmentName(department.getDepartmentName());


            departmentRepository.save(e);
            return true;
        }else {
            return false;
        }
    }

    public String deleteById(int id){
        Optional<Department> department = departmentRepository.findById(id);
        Department e = department.get();
        if(e != null){
            departmentRepository.deleteById(id);
            return "Deleted department by id "+id;
        }
        else{
            return "Department with id"+id+" not found";
        }
    }
}
