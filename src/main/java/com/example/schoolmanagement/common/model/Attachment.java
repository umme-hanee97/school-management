package com.example.schoolmanagement.common.model;

import com.example.schoolmanagement.student.model.Student;
import com.example.schoolmanagement.student.model.Teacher;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "attachments")
@Entity
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student studentId;
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacherId;
    @Column(nullable = false, name = "file_name")
    @Size(min = 1, max = 255, message = "File name must be between 1 and 255 characters")
    private String fileName;
//    @Column(nullable = false, name = "file_type")
//    @Size(min = 1, max = 50, message = "File type must be between 1 and 50 characters")
//    private String fileType;
    @Column(nullable = false, name = "file_data", columnDefinition = "TEXT")
    @Size(min = 1, message = "File data must not be empty")
    private String fileB64;
//    @Column(nullable = false, name = "file_size")
//    @Size(min = 1, message = "File size must be greater than 0")
//    private int fileSize;
}
