package com.example.schoolmanagement.routine.controller;

import com.example.schoolmanagement.routine.dto.PeriodDto;
import com.example.schoolmanagement.routine.service.PeriodService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/routines")
public class PeriodController {
    private final PeriodService service;

    public PeriodController(PeriodService periodService) {
        this.service = periodService;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<PeriodDto> periods = service.getAllPeriods();
        return ResponseEntity.ok(periods);
    }

    @GetMapping("/class/{classId}/section/{sectionId}")
    public ResponseEntity<?> getAllByClassIdAndSectionId(@PathVariable Long classId, @PathVariable Long sectionId) {
        List<PeriodDto> periods = service.getPeriodsByClassIdAndSectionId(classId, sectionId);
        return ResponseEntity.ok(periods);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            PeriodDto period = service.getPeriodById(id);
            return ResponseEntity.ok(period);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getCause());
        }
    }

    @PostMapping
    public ResponseEntity<?> saveOrUpdateData(@RequestBody PeriodDto dto) {
        try {
            PeriodDto period = service.saveOrUpdatePeriod(dto);
            return ResponseEntity.ok(period);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getCause());
        }
    }

    @PostMapping("/updateStatus/{id}")
    public ResponseEntity<?> updateStatus(@PathVariable Long id, @RequestParam String status) {
        try {
            PeriodDto period = service.getPeriodById(id);
            period.setStatus(status);
            service.saveOrUpdatePeriod(period);
            return ResponseEntity.ok(period);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getCause());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        try {
            service.deletePeriodById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getCause());
        }
    }
}
