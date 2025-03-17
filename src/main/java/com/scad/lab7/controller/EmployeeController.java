package com.scad.lab7.controller;

import com.scad.lab7.dto.*;
import com.scad.lab7.entity.*;
import com.scad.lab7.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final ProjectRepository projectRepository;

    // Add Employee
    @PostMapping("/employee/add")
    public String addEmployee(@RequestBody CreateEmployeeRequest request) {
        Department dept = departmentRepository.findById(request.getDepartmentId()).orElse(null);
        Employee emp = new Employee();
        emp.setEmpName(request.getEmpName());
        emp.setEmpSalary(request.getEmpSalary());
        emp.setAddress(request.getAddress());
        emp.setDepartment(dept);
        employeeRepository.save(emp);
        return "Employee added successfully.";
    }

    // Assign Project to Employee
    @PostMapping("/employee/assignProject")
    public String assignProject(@RequestParam Long empId, @RequestParam Long projectId) {
        Employee emp = employeeRepository.findById(empId).orElse(null);
        Project project = projectRepository.findById(projectId).orElse(null);
        if (emp != null && project != null) {
            emp.getProjects().add(project);
            employeeRepository.save(emp);
            return "Project assigned successfully.";
        }
        return "Employee or Project not found.";
    }

    // Update Salary
    @PutMapping("/employee/updateSalary")
    public String updateSalary(@RequestBody UpdateSalaryRequest request) {
        Employee emp = employeeRepository.findById(request.getEmpId()).orElse(null);
        if (emp != null) {
            emp.setEmpSalary(request.getNewSalary());
            employeeRepository.save(emp);
            return "Salary updated.";
        }
        return "Employee not found.";
    }

    // Delete Employee
    @DeleteMapping("/employee/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeRepository.deleteById(id);
        return "Employee deleted.";
    }

    // Employees with Salary > X
    @GetMapping("/employee/salaryGreaterThan")
    public List<Employee> getBySalary(@RequestParam Double salary) {
        return employeeRepository.findByEmpSalaryGreaterThan(salary);
    }

    // Employees by Name Pattern
    @GetMapping("/employee/searchByName")
    public List<Employee> getByName(@RequestParam String name) {
        return employeeRepository.findByEmpNameContainingIgnoreCase(name);
    }

    // All Employees with Details
    @GetMapping("/employee/all")
    public List<Employee> getAllEmployeesWithDetails() {
        return employeeRepository.findAll();
    }

    // Employee by ID with Details
    @GetMapping("/employee/details/{id}")
    public Employee getEmployeeByIdWithDetails(@PathVariable Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    // Create Department
    @PostMapping("/department/add")
    public String addDepartment(@RequestBody Department department) {
        departmentRepository.save(department);
        return "Department added successfully.";
    }

    // Get All Departments
    @GetMapping("/department/all")
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    // Create Project
    @PostMapping("/project/add")
    public String addProject(@RequestBody Project project) {
        projectRepository.save(project);
        return "Project added successfully.";
    }

    // Get All Projects
    @GetMapping("/project/all")
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }
}
