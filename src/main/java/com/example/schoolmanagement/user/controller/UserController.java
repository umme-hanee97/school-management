package com.example.schoolmanagement.user.controller;

import com.example.schoolmanagement.common.model.MessageResponse;
import com.example.schoolmanagement.user.dto.UserDto;
import com.example.schoolmanagement.user.service.UserService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://localhost:5173/", allowCredentials = "true", allowedHeaders = "*")
public class UserController {

    @Autowired
    private UserService service;

    private BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers(){
        List<UserDto> users = service.getAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<?> getUserByName(@PathVariable String username){
        UserDto user = service.get(username);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        UserDto user = service.getById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<?> saveData(@RequestBody UserDto userDto) throws MethodArgumentNotValidException {
        if (service.usernameExists(userDto.getName())){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: username already exists!"));
        }
        if (service.emailExists(userDto.getEmail())){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: email already exists!"));
        }
        userDto.setRoles(Collections.singleton("USER"));
        try{
            service.saveData(userDto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: one of the roles not found!"));
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{username}")
    public ResponseEntity<?> updateData(@PathVariable String username, @RequestBody @Valid UserDto userDto){
        service.updateData(username, userDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{username}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<?> deleteData(@PathVariable String username){
        service.delete(username);
        return ResponseEntity.noContent().build();
    }
}
