package com.app.ems.util;

import com.app.ems.EmsApplication;
import com.app.ems.dto.DepartmentDto;
import com.app.ems.dto.EmployeeDto;
import com.app.ems.entity.Department;
import com.app.ems.entity.Employee;

public class MapperLibrary {

    public static DepartmentDto mapToDepartmentDto(Department department){
        return EmsApplication.modelMapper().map(department,DepartmentDto.class);
    }

    public static Department mapToDepartmentEntity(DepartmentDto departmentDto) {
        return EmsApplication.modelMapper().map(departmentDto,Department.class);
    }

    public static EmployeeDto mapToEmployeeDto(Employee employee){
        return EmsApplication.modelMapper().map(employee, EmployeeDto.class);
    }

    public static Employee mapToEmployeeEntity(EmployeeDto employeeDto){
        return EmsApplication.modelMapper().map(employeeDto, Employee.class);
    }
}
