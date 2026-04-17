package com.example.schoolmanagement.student.repository;

import com.example.schoolmanagement.student.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByIsActiveTrue(boolean isActive);

    Student findByEmail(String email);

    @Query(value = "SELECT COUNT(*) FROM school.students WHERE is_active = true", nativeQuery = true)
    Integer countStudent();

    List<Student> findByStudentClassIdAndSectionIdAndIsActiveTrue(Long classId, Long sectionId, boolean b);
}
