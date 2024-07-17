package com.app.ems.controller;

import com.app.ems.dto.EMSResponseDto;
import com.app.ems.dto.EmployeeDto;
import com.app.ems.dto.EmployeeResponse;
import com.app.ems.service.EmployeeService;
import com.app.ems.util.AppConstants;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/departments/{id}/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Method to handle the create employee REST API
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(
            @PathVariable long id,
            @Valid @RequestBody EmployeeDto employeeDto
    ){
        EmployeeDto employeeDto1 = employeeService.createEmployee(id, employeeDto);
        return new ResponseEntity<>(employeeDto1, HttpStatus.CREATED);
    }

    // Method to handle the get employee by Id REST API
    @GetMapping("/{empId}")
    public EmployeeDto getEmployeeById(
            @PathVariable(value = "id") long id,
            @PathVariable(value = "empId") long empId
    ){
        return employeeService.findEmployeeById(id, empId);
    }

    // Method to handle the update employee by Id REST API
    @PutMapping("/{empId}")
    public EmployeeDto updateEmployeeById(@PathVariable long id,@Valid @RequestBody EmployeeDto employeeDto){
        return employeeService.updateEmployee(id, employeeDto);
    }

    // Method to handle the delete of the employee record REST API
    @DeleteMapping("/{empId}")
    public EMSResponseDto deleteEmployee(
            @PathVariable long id,
            @PathVariable long empId){
        employeeService.deleteEmployee(id,empId);
        EMSResponseDto emsResponseDto = new EMSResponseDto("Employee deleted successfully!");
        return emsResponseDto;
    }

    // Method to handle fetch all employees REST API
    @GetMapping
    public EmployeeResponse getEmployees(@RequestParam(value = "pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NO,required = false) int pageNo,
                                         @RequestParam(value = "pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE,required = false) int pageSize,
                                         @RequestParam(value = "sortBy",defaultValue = AppConstants.DEFAULT_SORT_BY,required = false) String sortBy,
                                         @RequestParam(value = "sortDir",defaultValue = AppConstants.DEFAULT_SORT_DIR,required = false) String sortDir){
        EmployeeResponse employees = employeeService.getEmployees(pageNo, pageSize, sortBy, sortDir);
        return employees;
    }
}
