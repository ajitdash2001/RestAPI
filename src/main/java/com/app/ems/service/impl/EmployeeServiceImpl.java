package com.app.ems.service.impl;

import com.app.ems.dto.EmployeeDto;
import com.app.ems.dto.EmployeeResponse;
import com.app.ems.entity.Department;
import com.app.ems.entity.Employee;
import com.app.ems.exception.EmployeeAPIException;
import com.app.ems.exception.ResourceNotFoundException;
import com.app.ems.repository.DepartmentRepository;
import com.app.ems.repository.EmployeeRepository;
import com.app.ems.service.EmployeeService;
import com.app.ems.util.MapperLibrary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               DepartmentRepository departmentRepository
    ) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public EmployeeDto createEmployee(long id, EmployeeDto employeeDto) {
        Department department = departmentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Department", "id", id)
        );
        Employee employee = MapperLibrary.mapToEmployeeEntity(employeeDto);
        employee.setDepartment(department);
        Employee save = employeeRepository.save(employee);
        return MapperLibrary.mapToEmployeeDto(save);
    }

    @Override
    public EmployeeDto findEmployeeById(Long id,Long empId) {
        // department is there or not
        Department department = departmentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Department", "id", id)
        );
        // employee is there or not
        Employee employee = employeeRepository.findById(empId).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "id", id)
        );

        // check whether employee belongs to the particular department or not
        if(!department.getId().equals(employee.getDepartment().getId())){
            throw new EmployeeAPIException(HttpStatus.BAD_REQUEST,"Employee doesn't belong to the department with id :: "+id);
        }
        EmployeeDto employeeDto = MapperLibrary.mapToEmployeeDto(employee);
        return employeeDto;
    }

    @Override
    public EmployeeDto updateEmployee(long id, EmployeeDto employeeDto) {
        Employee employee1 = employeeRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Employee","id",id)
        );
        employee1.setName(employeeDto.getName());
        employee1.setCity(employeeDto.getCity());
        Employee updated = employeeRepository.save(employee1);
        return MapperLibrary.mapToEmployeeDto(updated);
    }

    @Override
    public void deleteEmployee(long id, long empId) {
        // department is there or not
        Department department = departmentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Department", "id", id)
        );
        // employee is there or not
        Employee employee = employeeRepository.findById(empId).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "id", id)
        );

        // check whether employee belongs to the particular department or not
        if(!department.getId().equals(employee.getDepartment().getId())){
            throw new EmployeeAPIException(HttpStatus.BAD_REQUEST,"Employee doesn't belong to the department with id :: "+id);
        }
        employeeRepository.delete(employee);
    }

    @Override
    public EmployeeResponse getEmployees(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo,pageSize,sort);
        Page<Employee> all = employeeRepository.findAll(pageable);
        List<EmployeeDto> list = all.stream().map(MapperLibrary::mapToEmployeeDto).toList();

        // create EmployeeResponse object
        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setContents(list);
        employeeResponse.setPageNo(all.getNumber());
        employeeResponse.setPageSize(all.getSize());
        employeeResponse.setTotalPages(all.getTotalPages());
        employeeResponse.setTotalElements(all.getTotalElements());
        employeeResponse.setLast(all.isLast());
        return employeeResponse;
    }
}
