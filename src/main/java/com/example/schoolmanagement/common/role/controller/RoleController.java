package com.example.schoolmanagement.common.role.controller;


import com.example.schoolmanagement.common.role.dto.RolesDto;
import com.example.schoolmanagement.common.role.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/roles", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://localhost:5173/", allowCredentials = "true")
public class RoleController {

    @Autowired
    private RolesService service;

    @GetMapping
    public ResponseEntity<?> getAllRoles(){
        List<RolesDto> roles = service.findAll();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/{roleName}")
    public ResponseEntity<?> getById(@PathVariable String roleName){
        RolesDto role = service.getByRoleName(roleName);
        return ResponseEntity.ok(role);
    }

    @PostMapping
    public ResponseEntity<?> saveRole(@RequestBody RolesDto rolesDto){
        String roleName = service.createNew(rolesDto);
        if (roleName != null){
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{roleName}")
    public ResponseEntity<?> updateRole(@PathVariable String roleName, @RequestBody RolesDto rolesDto){
        String role = service.updateData(roleName, rolesDto);
        if (role != null){
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{roleName}")
    public ResponseEntity<?> deleteRole(@PathVariable String roleName){
        service.deleteData(roleName);
        return ResponseEntity.noContent().build();
    }
}
