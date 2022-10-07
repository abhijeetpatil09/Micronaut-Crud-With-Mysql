package com.example.services;

import com.example.entities.EmailDetails;
import com.example.entities.Employee;
import com.example.repositories.EmployeeRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;

@Singleton
public class EmployeeService {

    @Inject
    EmployeeRepository employeeRepository;

    public List<Employee> getEmployees(){

        return employeeRepository.findAll();
    }

    public String addEmployee(Employee employee){
        Employee e = employeeRepository.save(employee);
        EmailServices.sendEmail(new EmailDetails("Employee Alert !!!", "Congratulations "+e.getName()+", Your Employee Id is "+e.getId(), e.getEmail()));
        if(e != null){
            return "Employee added successfully";
        }
        else{
            return "Employee not added !!!";
        }
    }


    public boolean update(Employee employee, int id){
        Optional<Employee> optional = employeeRepository.findById(id);
        Employee e = optional.get();

        if(e!=null) {
            e.setName(employee.getName());
            e.setAge(employee.getAge());
            e.setSal(employee.getSal());
            e.setEmail(employee.getEmail());
            e.setJoiningDate(employee.getJoiningDate());
            e.setDepartment(employee.getDepartment());

            employeeRepository.save(e);
            EmailServices.sendEmail(new EmailDetails("Information Updated !!!",
                    "Mr/Mrs. "+e.getName()+", your employee details are suceessfully updated. Please Confirm this, Your Age is "
                            +e.getAge()+". Your Present salary is "+e.getSal()+". Your Department ID is "+e.getDepartment().getDepartmentId()+
                            ". And Your Department is "+e.getDepartment().getDepartmentName(), e.getEmail()));

            return true;
        }else {
            return false;
        }


    }
    // EmailServices.sendEmail(new EmailDetails("Information Updated !!!", "Mr/Mrs. "+e.getName()+", your employee details are suceessfully updated. Please Confirm this, Your Age is "+e.getAge()+". Your Present salary is "+e.getSal()+". Your Department is "+e.getDepartment(), e.getEmail()));

    public String deleteById(int id){
         Optional<Employee> employee = employeeRepository.findById(id);
         Employee e = employee.get();
         if(e != null){
             employeeRepository.deleteById(id);
             return "Deleted employee by id "+id;
         }
         else{
             return "Employee with id"+id+" not found";
         }
    }
}
