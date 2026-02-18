package com.example.schoolmanagement.common.lookup.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Table(name = "student_subjects")
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
public class StudentSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name = "subject_name")
    private String subjectName;
    @Column(nullable = false, name = "subject_code")
    private String subjectCode;
}
