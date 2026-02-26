package com.example.schoolmanagement.student.service;

import com.example.schoolmanagement.student.dto.StudentDto;
import com.example.schoolmanagement.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepository repository;

    @Override
    public List<StudentDto> getAll() {
        return List.of();
    }

    @Override
    public StudentDto getById(Long id) {
        return null;
    }

    @Override
    public String saveData(StudentDto dto) {
        return "";
    }

    @Override
    public String updateData(Long id, StudentDto dto) {
        return "";
    }

    @Override
    public void deleteData(Long id) {

    }
}
