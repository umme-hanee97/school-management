package com.example.schoolmanagement.student.model;

import com.example.schoolmanagement.common.model.Attachment;
import com.example.schoolmanagement.common.lookup.model.StudentClass;
import com.example.schoolmanagement.common.lookup.model.StudentSection;
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
@Table(name = "students")
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name = "name")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;
    @Column(nullable = false, name = "father_name")
    @Size(min = 2, max = 50, message = "Father Name must be between 2 and 50 characters")
    private String fatherName;
    @Column(nullable = false, name = "mother_name")
    @Size(min = 2, max = 50, message = "Mother name must be between 2 and 50 characters")
    private String motherName;
    @Column(nullable = false, name = "email", unique = true)
    @Size(min = 2, max = 50)
    @Email
    private String email;
    @Column(nullable = false, name = "phone_number")
    @Size(min = 11, max = 14, message = "Phone number must be between 11 and 14 characters")
    private String phoneNumber;
    @Column(name = "is_active")
    private boolean isActive = true;
    @Column(nullable = false, name = "address")
    @Size(min = 5, max = 100, message = "Address must be between 5 and 100 characters")
    private String address;
    @Column(nullable = false, name = "date_of_birth")
    @Size(min = 10, max = 10, message = "Date of Birth must be in the format YYYY-MM-DD")
    private String dateOfBirth;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    private StudentClass classId;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "section_id")
    private StudentSection sectionId;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "student_class_subject", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "subject_id"))
    private List<StudentSubject> subjects;
    @OneToMany(mappedBy = "studentId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Attachment> attachments;
    @Column(nullable = false, name = "roll_number", unique = true)
    @Size(min = 1, max = 20, message = "Roll number must be between 1 and 20 characters")
    private int rollNumber;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Teacher teacherId;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "emergency_contacts", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "emergency_contact_id"))
    private List<EmergencyContact> emergencyContacts;
}
