package com.example.schoolmanagement.student.controller;

import com.example.schoolmanagement.student.dto.StudentDto;
import com.example.schoolmanagement.student.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@CrossOrigin(origins = "http://localhost:5173/", allowCredentials = "true")
public class StudentController {

    private final StudentService service;

    private StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<StudentDto> students = service.getAll();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        StudentDto student = service.getById(id);
        return ResponseEntity.ok(student);
    }

    @PostMapping
    public ResponseEntity<?> saveData(@RequestBody StudentDto dto) {
        String name = service.saveData(dto);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateData(@PathVariable Long id, @RequestBody StudentDto dto) {
        String name = service.updateData(id, dto);
        return ResponseEntity.ok(name);
    }

}
