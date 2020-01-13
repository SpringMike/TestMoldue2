package com.codegym.service;

import com.codegym.model.Department;
import com.codegym.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {

    Page<Employee> findAll(Pageable pageable);

    Employee findById(long id);

    void save(Employee employee);

    void remove(long id);
    Page<Employee> findAllByNameContaining(String s, Pageable pageable);
    Page<Employee> findAllByOrderBySalary(Pageable pageable);
    Page<Employee> findAllByDepartment(Department department, Pageable pageable);

}