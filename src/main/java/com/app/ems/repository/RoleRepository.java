package com.app.ems.repository;

import com.app.ems.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
//    Optional<Role> findByName(String name);
//    Boolean existsByName(String name);
    Optional<Role> findByRole(String role);
}
