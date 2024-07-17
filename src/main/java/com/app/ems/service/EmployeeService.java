package com.app.ems.service;

import com.app.ems.dto.EmployeeDto;
import com.app.ems.dto.EmployeeResponse;
import com.app.ems.entity.Employee;

import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployee(long id, EmployeeDto employeeDto);
    EmployeeDto findEmployeeById(Long id, Long empId);
    EmployeeDto updateEmployee(long id, EmployeeDto employeeDto);
    void deleteEmployee(long id,long empId);
    EmployeeResponse getEmployees(int pageNo, int pageSize, String sortBy, String sortDir);
}
