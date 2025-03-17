
package com.scad.lab7.dto;

import com.scad.lab7.entity.Address;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateEmployeeRequest {
    private String empName;
    private Double empSalary;
    private Address address;
    private Long departmentId;
}
