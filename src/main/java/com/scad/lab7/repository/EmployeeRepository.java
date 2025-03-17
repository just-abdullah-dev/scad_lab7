
package com.scad.lab7.repository;

import com.scad.lab7.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByEmpSalaryGreaterThan(Double salary);
    List<Employee> findByEmpNameContainingIgnoreCase(String name);
}
