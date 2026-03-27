package com.example.schoolmanagement.student.controller;

import com.example.schoolmanagement.common.model.MessageResponse;
import com.example.schoolmanagement.student.dto.TeacherDto;
import com.example.schoolmanagement.student.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teachers")
@CrossOrigin(origins = "http://localhost:5173/", allowCredentials = "true")
public class TeacherController {


    private final TeacherService service;

    private TeacherController(TeacherService service) {
        this.service = service;
    }

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
        MessageResponse messageResponse = new MessageResponse();
        String name = service.saveData(dto);
        messageResponse.setMessage("User Registered Successfully!");
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateData(@PathVariable Long id, @RequestBody TeacherDto dto) {
        String name = service.updateData(id, dto);
        return ResponseEntity.ok(name);
    }

}
