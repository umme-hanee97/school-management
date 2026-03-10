package com.example.schoolmanagement.common.role.repository;

import com.example.schoolmanagement.common.role.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Roles, String> {

    boolean existsByNameIgnoreCase(String roleName);
}
