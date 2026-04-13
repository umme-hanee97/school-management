package com.example.schoolmanagement.student.model;

import com.example.schoolmanagement.common.lookup.model.StudentSubject;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "teachers")
@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name = "name")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;
    @Column(nullable = false, name = "email", unique = true)
    @Size(min = 2, max = 50)
    @Email
    private String email;
    @Column(nullable = false, name = "phone_number")
    @Size(min = 11, max = 14, message = "Phone number must be between 11 and 14 characters")
    private String phoneNumber;
    @Column(nullable = false, name = "address")
    @Size(min = 5, max = 100, message = "Address must be between 5 and 100 characters")
    private String address;
    @Column(nullable = false, name = "date_of_birth")
    @Size(min = 10, max = 10, message = "Date of Birth must be in the format YYYY-MM-DD")
    private String dateOfBirth;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "teacher_subjects", joinColumns = @JoinColumn(name = "teacher_id"), inverseJoinColumns = @JoinColumn(name = "subject_id"))
    private List<StudentSubject> subjects;
    @Column(name = "file_b64", columnDefinition = "mediumblob")
    private byte[] fileB64;
    @Column(name = "file_type")
    @Size(min = 1, max = 8, message = "File type must be between 1 and 8 characters")
    private String fileType;
    @Column(name = "file_name")
    @Size(min = 1, max = 255, message = "File name must be between 1 and 255 characters")
    private String fileName;
    @Column(name = "is_active")
    private boolean isActive = true;
    @Column(nullable = false, length = 20, name = "profile_status")
    private String profileStatus;
}
