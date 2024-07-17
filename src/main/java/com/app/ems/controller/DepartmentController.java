package com.app.ems.controller;

import com.app.ems.dto.DepartmentDto;
import com.app.ems.dto.EMSResponseDto;
import com.app.ems.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    // create department REST API
    @PostMapping
    public ResponseEntity<DepartmentDto> createDepartment(@Valid @RequestBody DepartmentDto departmentDto){
        return new ResponseEntity<>(departmentService.createDepartment(departmentDto), HttpStatus.CREATED);
    }

    // get department by id REST API
    @GetMapping("/{deptId}")
    public DepartmentDto getDepartmentById(@PathVariable(value = "deptId") long id){
        return departmentService.getDepartmentById(id);
    }

    // get all departments REST API
    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<DepartmentDto> getDepartments(){
        return departmentService.getDepartments();
    }

    // update department using id REST API
    @PutMapping("/{id}")
    public DepartmentDto updateDepartment
    (
            @PathVariable long id,
            @Valid @RequestBody DepartmentDto departmentDto
    ){
        return departmentService.updateDepartment(id,departmentDto);
    }

    // delete department using id REST API
    @DeleteMapping("/{id}")
    public EMSResponseDto deleteDepartment(long id){
        departmentService.deleteDepartment(id);
        EMSResponseDto emsResponseDto = new EMSResponseDto("Department deleted successfully!");
        return emsResponseDto;
    }
}
