package com.example.schoolmanagement.student.model;

import com.example.schoolmanagement.common.lookup.model.Relationship;
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
@Table(name = "emergency_contacts")
@Entity
public class EmergencyContact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student studentId;
    @Column(nullable = false, name = "name")
    @Size(min = 2, max = 255, message = "Name must be between 2 and 255 characters")
    private String name;
    @OneToOne
    @JoinColumn(name = "relationship_id")
    private Relationship relationshipId;
    @Column(nullable = false, name = "phone_number")
    @Size(min = 11, max = 14, message = "Phone number must be between 11 and 14 characters")
    private String phoneNumber;
    @Column(name = "email")
    @Email
    @Size(max = 255, message = "Email must be less than 255 characters")
    private String email;
}
