package com.example.schoolmanagement.common.lookup.controller;

import com.example.schoolmanagement.common.model.MessageResponse;
import com.example.schoolmanagement.common.lookup.dto.SectionDto;
import com.example.schoolmanagement.common.lookup.service.SectionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/lookup/sections")
@CrossOrigin(origins = "http://localhost:5173/", allowCredentials = "true")
public class SectionController {

    @Autowired
    private SectionService service;

    @GetMapping
    public ResponseEntity<?> getAllData(){
        List<SectionDto> sections = service.getAll();
        return ResponseEntity.ok(sections);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        SectionDto sectionDto = service.getById(id);
        return ResponseEntity.ok(sectionDto);
    }

    @PostMapping
    public ResponseEntity<?> saveData(@RequestBody SectionDto sectionDto){
        try {
            service.saveData(sectionDto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error occurred!"));
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateData(@PathVariable Long id, @RequestBody @Valid SectionDto sectionDto){
        service.updateData(id, sectionDto);
        return ResponseEntity.ok().build();
    }
}
