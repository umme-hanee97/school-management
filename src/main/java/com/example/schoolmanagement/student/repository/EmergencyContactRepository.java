package com.example.schoolmanagement.student.repository;

import com.example.schoolmanagement.student.model.EmergencyContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmergencyContactRepository extends JpaRepository<EmergencyContact, Long> {
    @Query(value = "SELECT * FROM emergency_contacts WHERE student_id = :studentId", nativeQuery = true)
    List<EmergencyContact> findByStudentId(Long studentId);
}
