package com.app.ems.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name",nullable = false)
    private String name;
    @Column(name = "city",nullable = false)
    private String city;
    @Column(name = "email",unique = true,nullable = false)
    private String email;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "deptId",nullable = false)
    private Department department;

}
