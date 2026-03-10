package com.example.schoolmanagement.student.service;

import com.example.schoolmanagement.student.dto.TeacherDto;
import java.util.List;

public interface TeacherService {
    List<TeacherDto> getAll();

    TeacherDto getById(Long id);

    String saveData(TeacherDto dto);

    String updateData(Long id, TeacherDto dto);

    void deleteData(Long id);
}
