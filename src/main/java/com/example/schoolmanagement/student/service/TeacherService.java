package com.example.schoolmanagement.student.service;

import com.example.schoolmanagement.common.model.ErrorHandler;
import com.example.schoolmanagement.student.dto.TeacherDto;
import java.util.List;

public interface TeacherService {
    List<TeacherDto> getAll();

    TeacherDto getById(Long id);

    TeacherDto getByEmail(String email);

    TeacherDto saveData(TeacherDto dto) throws ErrorHandler;

    TeacherDto editProfileData(TeacherDto dto) throws ErrorHandler;

    String updateData(Long id, TeacherDto dto);

    void deleteData(Long id);
}
