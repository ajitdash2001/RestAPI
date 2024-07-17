package com.app.ems.service.impl;

import com.app.ems.dto.DepartmentDto;
import com.app.ems.entity.Department;
import com.app.ems.exception.ResourceNotFoundException;
import com.app.ems.repository.DepartmentRepository;
import com.app.ems.service.DepartmentService;
import com.app.ems.util.MapperLibrary;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        Department department = MapperLibrary.mapToDepartmentEntity(departmentDto);
        Department created = departmentRepository.save(department);
        return MapperLibrary.mapToDepartmentDto(created);
    }

    @Override
    public DepartmentDto getDepartmentById(long deptId) {
        Department department = departmentRepository.findById(deptId).orElseThrow(
                () -> new ResourceNotFoundException("Department", "id", deptId)
        );
        return MapperLibrary.mapToDepartmentDto(department);
    }

    @Override
    public List<DepartmentDto> getDepartments() {
        List<Department> all = departmentRepository.findAll();
        return all.stream().map(MapperLibrary::mapToDepartmentDto).toList();
    }

    @Override
    public DepartmentDto updateDepartment(long id, DepartmentDto departmentDto) {
        Department department = departmentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Department", "id", id)
        );
        department.setDeptName(departmentDto.getDeptName());
        department.setDeptDescription(departmentDto.getDeptDescription());
        Department updated = departmentRepository.save(department);
        return MapperLibrary.mapToDepartmentDto(updated);
    }
    @Override
    public void deleteDepartment(long id) {
        Department department = departmentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Department", "id", id)
        );
        departmentRepository.delete(department);
    }
}
