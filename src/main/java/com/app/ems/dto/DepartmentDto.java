package com.app.ems.dto;

import com.app.ems.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {
    private Long id;
    private String deptName;
    private String deptDescription;
    List<EmployeeDto> employees;
}
