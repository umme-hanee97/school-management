package com.example.schoolmanagement.student.controller;

import com.example.schoolmanagement.student.dto.TeacherDto;
import com.example.schoolmanagement.student.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teachers")
public class TeacherController {

    @Autowired
    private TeacherService service;

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<TeacherDto> teachers = service.getAll();
        return ResponseEntity.ok(teachers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        TeacherDto teacher = service.getById(id);
        return ResponseEntity.ok(teacher);
    }

    @PostMapping
    public ResponseEntity<?> saveData(@RequestBody TeacherDto dto) {
        String name = service.saveData(dto);
        return ResponseEntity.ok(name);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateData(@PathVariable Long id, @RequestBody TeacherDto dto) {
        String name = service.updateData(id, dto);
        return ResponseEntity.ok(name);
    }

}
