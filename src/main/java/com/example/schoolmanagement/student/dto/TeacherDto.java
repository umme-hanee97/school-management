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
public class TeacherDto {

    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private String dateOfBirth;
    private List<Long> subjectIds;
    private List<String> subjectNames;
    private String fileB64;
    private String fileType;
    private String fileName;

}
