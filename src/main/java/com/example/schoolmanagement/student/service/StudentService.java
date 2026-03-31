package com.example.schoolmanagement.student.service;

import com.example.schoolmanagement.common.model.ErrorHandler;
import com.example.schoolmanagement.student.dto.StudentDto;
import jakarta.transaction.Transactional;

import java.util.List;

public interface StudentService {
    List<StudentDto> getAll();

    StudentDto getById(Long id);

    StudentDto getByEmail(String email);

    StudentDto saveData(StudentDto dto) throws ErrorHandler;

    StudentDto editProfileData(StudentDto dto) throws ErrorHandler;

    String updateData(Long id, StudentDto dto);

    void deleteData(Long id);
}
