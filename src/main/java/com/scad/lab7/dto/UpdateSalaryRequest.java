
package com.scad.lab7.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateSalaryRequest {
    private Long empId;
    private Double newSalary;
}
