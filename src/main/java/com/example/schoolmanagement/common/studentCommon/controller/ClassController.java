package com.example.schoolmanagement.common.studentCommon.controller;

import com.example.schoolmanagement.common.model.MessageResponse;
import com.example.schoolmanagement.common.studentCommon.dto.ClassDto;
import com.example.schoolmanagement.common.studentCommon.service.ClassService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/class")
public class ClassController {

    @Autowired
    private ClassService service;

    @GetMapping
    public ResponseEntity<?> getAllData(){
        List<ClassDto> classes = service.getAll();
        return ResponseEntity.ok(classes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        ClassDto classDto = service.getById(id);
        return ResponseEntity.ok(classDto);
    }

    @PostMapping
    public ResponseEntity<?> saveData(@RequestBody ClassDto classDto){
        try {
            service.saveData(classDto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error occurred!"));
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateData(@PathVariable Long id, @RequestBody @Valid ClassDto classDto){
        service.updateData(id, classDto);
        return ResponseEntity.ok().build();
    }
}
