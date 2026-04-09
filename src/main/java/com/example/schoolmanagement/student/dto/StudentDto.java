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
    private String address;
    private String dateOfBirth;
    private Long classId;
    private String className;
    private Long sectionId;
    private String sectionName;
    private List<Long> subjects;
    private List<String> subjectNames;
    private Integer rollNumber;
    private Long teacherId;
    private String teacherName;
    private List<Long> emergencyContactsIds;
    private List<EmergencyContactDto> emergencyContacts;
    private String fileB64;
    private String fileType;
    private String fileName;

}
