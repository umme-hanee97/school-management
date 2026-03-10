package com.example.schoolmanagement.student.repository;

import com.example.schoolmanagement.student.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    List<Teacher> findAllByIsActive(boolean isActive);

    Teacher findByEmail(String email);
}
