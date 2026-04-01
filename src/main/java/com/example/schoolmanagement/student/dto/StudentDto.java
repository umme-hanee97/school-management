package com.example.schoolmanagement.student.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class StudentDto {

    private Long id;
    private String name;
    private String fatherName;
    private String motherName;
    private String email;
    private String phoneNumber;
//    private boolean isActive = true;
    private String address;
    private String dateOfBirth;
    private Long classId;
    private String className;
    private Long sectionId;
    private List<Long> subjects;
    private Integer rollNumber;
    private Long teacherId;
    private List<Long> emergencyContacts;
    private String fileB64;
    private String fileType;
    private String fileName;

}
