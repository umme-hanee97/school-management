package com.example.schoolmanagement.common.lookup.repository;

import com.example.schoolmanagement.common.lookup.model.StudentSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<StudentSubject, Long> {
}
