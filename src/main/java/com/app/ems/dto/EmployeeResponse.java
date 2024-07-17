package com.app.ems.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class EmployeeResponse {
    private List<EmployeeDto> contents;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
