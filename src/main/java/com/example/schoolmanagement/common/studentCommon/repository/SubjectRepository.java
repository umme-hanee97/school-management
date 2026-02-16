package com.example.schoolmanagement.common.studentCommon.repository;

import com.example.schoolmanagement.common.studentCommon.model.StudentSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<StudentSubject, Long> {
}
