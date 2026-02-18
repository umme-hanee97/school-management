package com.example.schoolmanagement.common.lookup.repository;

import com.example.schoolmanagement.common.lookup.model.StudentSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionRepository extends JpaRepository<StudentSection, Long> {
}
