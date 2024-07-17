package com.app.ems.service;

import com.app.ems.dto.DepartmentDto;

import java.util.List;

public interface DepartmentService {
    DepartmentDto createDepartment(DepartmentDto departmentDto);
    DepartmentDto getDepartmentById(long deptId);
    List<DepartmentDto> getDepartments();
    DepartmentDto updateDepartment(long id, DepartmentDto departmentDto);
    void deleteDepartment(long id);
}
