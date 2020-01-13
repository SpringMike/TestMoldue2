package com.codegym.service.impl;

import com.codegym.model.Department;
import com.codegym.model.Employee;
import com.codegym.repository.EmployeeRepository;
import com.codegym.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Page<Employee> findAll(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    @Override
    public Employee findById(long id) {
        return employeeRepository.findOne(id);
    }

    @Override
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void remove(long id) {
        employeeRepository.delete(id);

    }

    @Override
    public Page<Employee> findAllByNameContaining(String s, Pageable pageable) {
        return employeeRepository.findAllByNameContaining(s,pageable);
    }

    @Override
    public Page<Employee> findAllByOrderBySalary(Pageable pageable) {
        return employeeRepository.findAllByOrderBySalary(pageable);
    }

    @Override
    public Page<Employee> findAllByDepartment(Department department, Pageable pageable) {
        return employeeRepository.findAllByDepartment(department,pageable);
    }


}