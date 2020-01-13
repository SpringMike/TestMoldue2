package com.codegym.model;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;


public class EmployeeForm {

    private long id;
    private String name;
    private String birthDate;
    private String address;
    private long salary;
    private MultipartFile avatar;

    private Department department;


    public  EmployeeForm(){}

    public EmployeeForm(String name, String birthDate, String address,long salary ,MultipartFile avatar) {

        this.name = name;
        this.birthDate = birthDate;
        this.address = address;
        this.salary = salary;
        this.avatar = avatar;

    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public MultipartFile getAvatar() {
        return avatar;
    }

    public void setAvatar(MultipartFile avatar) {
        this.avatar = avatar;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }
}