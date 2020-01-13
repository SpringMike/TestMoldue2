package com.codegym.model;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String birthDate;
    private String address;
    private long salary;
    private String avatar;


    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    public Employee(String name, String birthDate, String address, long salary,String avatar, Department department) {
        this.name = name;
        this.birthDate = birthDate;
        this.address = address;
        this.avatar = avatar;
        this.salary = salary;
        this.department = department;
    }

    public Employee(){

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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }
}