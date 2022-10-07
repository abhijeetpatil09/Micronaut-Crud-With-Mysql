package com.example.entities;

import javax.persistence.*;

@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int departmentId;

    @Column
    private String departmentName;

    public Department() {
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentId=" + departmentId +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }

    public Department(int departmentId, String departmentName) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }

    public Department(int departmentId) {

        this.departmentId = departmentId;
    }

    public Department(String departmentName) {

        this.departmentName = departmentName;
    }

    public int getDepartmentId() {

        return departmentId;
    }

    public String getDepartmentName() {

        return departmentName;
    }

    public void setDepartmentId(int departmentId) {

        this.departmentId = departmentId;
    }

    public void setDepartmentName(String departmentName) {

        this.departmentName = departmentName;
    }
}
