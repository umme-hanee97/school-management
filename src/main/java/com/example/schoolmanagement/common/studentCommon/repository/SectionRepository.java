package com.example.schoolmanagement.common.studentCommon.repository;

import com.example.schoolmanagement.common.studentCommon.model.StudentSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionRepository extends JpaRepository<StudentSection, Long> {
}
