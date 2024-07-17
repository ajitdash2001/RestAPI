package com.app.ems.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private Long id;
    // name should not be null or empty
    // name length should have minimum 4 characters
    @NotEmpty(message = "Employee name should not be null or empty")
    @Size(min = 4,message = "Employee name must be of 4 characters minimum")
    private String name;
    // city should not be null or empty
    // city length must be minimum 3 characters
    @NotEmpty(message = "Employee city should not be null or empty")
    @Size(min = 3,message = "Employee city must be of 3 characters minimum")
    private String city;
    // email must be a valid email
    // email should not be null or empty
    @Email(message = "Email should be a valid email")
    @NotEmpty(message = "Employee email should not be null or empty")
    private String email;
}
