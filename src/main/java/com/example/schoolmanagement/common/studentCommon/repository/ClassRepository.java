package com.example.schoolmanagement.common.studentCommon.repository;

import com.example.schoolmanagement.common.studentCommon.model.StudentClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends JpaRepository<StudentClass, Long> {
}
