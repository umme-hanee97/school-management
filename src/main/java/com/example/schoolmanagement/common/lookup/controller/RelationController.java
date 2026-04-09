package com.example.schoolmanagement.common.lookup.controller;

import com.example.schoolmanagement.common.lookup.dto.RelationshipDto;
import com.example.schoolmanagement.common.lookup.service.RelationService;
import com.example.schoolmanagement.common.model.MessageResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/lookup/relations")
@CrossOrigin(origins = "http://localhost:5173/", allowCredentials = "true")
public class RelationController {

    @Autowired
    private RelationService service;

    @GetMapping
    public ResponseEntity<?> getAllData() {
        List<RelationshipDto> relationships = service.getAll();
        return ResponseEntity.ok(relationships);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        RelationshipDto relationship = service.getById(id);
        return ResponseEntity.ok(relationship);
    }

    @PostMapping
    public ResponseEntity<?> saveData(@RequestBody RelationshipDto relationship) {
        try {
            service.saveData(relationship);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error occurred!"));
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateData(@PathVariable Long id, @RequestBody @Valid RelationshipDto relationship) {
        service.updateData(id, relationship);
        return ResponseEntity.ok().build();
    }
}
