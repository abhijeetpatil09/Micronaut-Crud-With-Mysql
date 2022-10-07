package com.example.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private int age;
    @Column
    private double sal;
    @Column
    private String email;

    @Override
    public String toString() {
        return "Employee{" +

                ", name='" + name + '\'' +
                ", age=" + age +
                ", sal=" + sal +
                ", email='" + email + '\'' +
                ", joiningDate=" + joiningDate +
                ", department=" + department +
                '}';
    }

    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date joiningDate;

    @ManyToOne
    @JoinColumn(name = "departmentId")
    private Department department;

    public Employee() {
    }

    public Employee(String name, int age, double sal, String email, Date joiningDate, Department department) {
        this.name = name;
        this.age = age;
        this.sal = sal;
        this.email = email;
        this.joiningDate = joiningDate;
        this.department = department;
    }


    public int getId() {

        return id;
    }

    public String getName() {

        return name;
    }

    public int getAge() {

        return age;
    }

    public double getSal() {

        return sal;
    }

    public Date getJoiningDate() {

        return joiningDate;
    }

    public Department getDepartment() {

        return department;
    }

    public String getEmail() {

        return email;
    }

    public void setId(int id) {

        this.id = id;
    }

    public void setName(String name) {

        this.name = name;
    }

    public void setAge(int age) {

        this.age = age;
    }

    public void setSal(double sal) {

        this.sal = sal;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public void setJoiningDate(Date joiningDate) {

        this.joiningDate = joiningDate;
    }

    public void setDepartment(Department department) {

        this.department = department;
    }
}
