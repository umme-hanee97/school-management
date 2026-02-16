package com.example.schoolmanagement.common.studentCommon.controller;

import com.example.schoolmanagement.common.model.MessageResponse;
import com.example.schoolmanagement.common.studentCommon.dto.SubjectDto;
import com.example.schoolmanagement.common.studentCommon.service.SubjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/subjects")
public class SubjectController {

    @Autowired
    private SubjectService service;

    @GetMapping
    public ResponseEntity<?> getAllData(){
        List<SubjectDto> subjects = service.getAll();
        return ResponseEntity.ok(subjects);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        SubjectDto subject = service.getById(id);
        return ResponseEntity.ok(subject);
    }

    @PostMapping
    public ResponseEntity<?> saveData(@RequestBody SubjectDto subject){
        try {
            service.saveData(subject);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error occurred!"));
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateData(@PathVariable Long id, @RequestBody @Valid SubjectDto subject){
        service.updateData(id, subject);
        return ResponseEntity.ok().build();
    }
}
