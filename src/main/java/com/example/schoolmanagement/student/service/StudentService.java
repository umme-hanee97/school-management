package com.example.schoolmanagement.student.service;

import com.example.schoolmanagement.student.dto.StudentDto;
import java.util.List;

public interface StudentService {
    List<StudentDto> getAll();

    StudentDto getById(Long id);

    String saveData(StudentDto dto);

    String updateData(Long id, StudentDto dto);

    void deleteData(Long id);
}
