package com.codegym.controller;


import com.codegym.model.Department;
import com.codegym.model.Employee;
import com.codegym.model.EmployeeForm;
import com.codegym.service.DepartmentService;
import com.codegym.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.Optional;


@Controller
@PropertySource("classpath:global_config_app.properties")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    Environment env;

    @ModelAttribute("departments")
    public Iterable<Department> departments() {
        return departmentService.findAll();
    }

    @GetMapping("/employee")
    public ModelAndView showPhoneList(@RequestParam("s") Optional<String> s, @PageableDefault(size = 6,sort = "salary") Pageable pageable) {
        Page<Employee> employees;
        if (s.isPresent()) {
            employees = employeeService.findAllByNameContaining(s.get(), pageable);
        } else {
            employees = employeeService.findAll(pageable);
        }

        ModelAndView modelAndView = new ModelAndView("/employee/list");
        modelAndView.addObject("employee", employees);
        return modelAndView;
    }
    @GetMapping("/searchByDepartment")
    public ModelAndView getPhoneByCategory(@RequestParam("search") Long DepartmentId, Pageable pageable) {
        Page<Employee> employees;
        if (DepartmentId == -1) {
            employees = employeeService.findAll(pageable);
        } else {
            Department department = departmentService.findById(DepartmentId);
            employees = employeeService.findAllByDepartment(department, pageable);
        }

        ModelAndView modelAndView = new ModelAndView("/employee/list");
        modelAndView.addObject("employee", employees);
        modelAndView.addObject("search", DepartmentId);
        return modelAndView;
    }

    @GetMapping("/create-employee")
    public ModelAndView createEmployee() {
        ModelAndView modelAndView = new ModelAndView("/employee/create");
        modelAndView.addObject("employeeForm", new EmployeeForm());
        return modelAndView;
    }

    @PostMapping("/create-employee")
    public ModelAndView saveEmployee(@ModelAttribute("employeeForm") EmployeeForm employeeForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("errorMessage", bindingResult.getAllErrors());
        }

        MultipartFile multipartFile = employeeForm.getAvatar();
        String fileName = multipartFile.getOriginalFilename();
        String fileUpload = env.getProperty("file_upload").toString();

        try {
            FileCopyUtils.copy(employeeForm.getAvatar().getBytes(), new File(fileUpload + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Employee employee1 = new Employee(employeeForm.getName(), employeeForm.getBirthDate(),employeeForm.getAddress(),employeeForm.getSalary(), fileName,employeeForm.getDepartment());
        employeeService.save(employee1);
        ModelAndView modelAndView = new ModelAndView("/employee/create");
        modelAndView.addObject("employeeForm", new Employee());
        modelAndView.addObject("message", "New customer created successfully");
        return modelAndView;


    }



    @GetMapping("/delete-employee/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        Employee employee = employeeService.findById(id);
        if(employee != null) {
            ModelAndView modelAndView = new ModelAndView("/employee/delete");
            modelAndView.addObject("employee", employee);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-employee")
    public ModelAndView deleteCustomer(@ModelAttribute("employee") Employee employee){
        employeeService.remove(employee.getId());
        ModelAndView modelAndView = new ModelAndView("/employee/delete");
        modelAndView.addObject("message", "customer delete successfully");
        return modelAndView;
    }

    @GetMapping("/edit-employee/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        Employee employee = employeeService.findById(id);
        if(employee != null) {
            ModelAndView modelAndView = new ModelAndView("/employee/edit");
            modelAndView.addObject("employeeForm", employee);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @GetMapping("/view/{id}")
    public ModelAndView viewPhoneDetail(@PathVariable("id") long id) {
        Employee employee = employeeService.findById(id);

        ModelAndView modelAndView = new ModelAndView("/employee/view");
        modelAndView.addObject("employee", employee);
        return modelAndView;
    }

    @PostMapping("/edit-employee")
    public ModelAndView updateCustomer(@ModelAttribute("employeeForm") EmployeeForm employeeForm){


        MultipartFile multipartFile = employeeForm.getAvatar();
        String fileName = multipartFile.getOriginalFilename();
        String fileUpload = env.getProperty("file_upload").toString();

        try {
            FileCopyUtils.copy(employeeForm.getAvatar().getBytes(), new File(fileUpload + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Employee employee1 = employeeService.findById(employeeForm.getId());
        employee1.setName(employeeForm.getName());
        employee1.setBirthDate(employeeForm.getBirthDate());
        employee1.setAddress(employeeForm.getAddress());
        employee1.setSalary(employeeForm.getSalary());
        employee1.setAvatar(fileName);
        employee1.setDepartment(employeeForm.getDepartment());

        employeeService.save(employee1);
        ModelAndView modelAndView = new ModelAndView("/employee/edit");
        modelAndView.addObject("employeeForm", employeeForm);
        modelAndView.addObject("message", "employee updated successfully");
        return modelAndView;

    }
    @GetMapping("/sort")
    public ModelAndView sortBySalary(Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("/employee/list");
        modelAndView.addObject("employee", employeeService.findAllByOrderBySalary(pageable));
        return modelAndView;
    }

}
