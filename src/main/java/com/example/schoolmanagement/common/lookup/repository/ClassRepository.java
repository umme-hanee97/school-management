package com.example.schoolmanagement.common.lookup.repository;

import com.example.schoolmanagement.common.lookup.model.StudentClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends JpaRepository<StudentClass, Long> {
}
