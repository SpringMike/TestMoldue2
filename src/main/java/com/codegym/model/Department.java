package com.codegym.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;

    @OneToMany(mappedBy="department")
    private List<Employee> employees;

    public Department(){

    }

    public Department(String name) {
        this.name = name;

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

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}