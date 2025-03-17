
package com.scad.lab7.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empId;
    private String empName;
    private Double empSalary;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @ManyToOne
    private Department department;

    @ManyToMany
    private List<Project> projects = new ArrayList<>();
}
