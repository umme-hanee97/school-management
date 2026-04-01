package com.example.schoolmanagement.common.Controller;


import com.example.schoolmanagement.common.model.Dashboard;
import com.example.schoolmanagement.common.service.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dashboard")
@CrossOrigin(origins = "http://localhost:5173/", allowCredentials = "true")
public class DashboardController {

    private final DashboardService service;

    public DashboardController(DashboardService service) {
        this.service = service;
    }

    @GetMapping("/countStats")
    public ResponseEntity<?> countStats() {
        Dashboard dashboard = service.countStats();
        return ResponseEntity.ok(dashboard);
    }
}
