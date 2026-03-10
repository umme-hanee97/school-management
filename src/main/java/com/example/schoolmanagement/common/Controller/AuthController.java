package com.example.schoolmanagement.common.Controller;

import com.example.schoolmanagement.common.model.JwtResponse;
import com.example.schoolmanagement.common.model.LoginRequest;
import com.example.schoolmanagement.common.model.MessageResponse;
import com.example.schoolmanagement.common.security.jwt.JwtUtils;
import com.example.schoolmanagement.common.security.service.UserDetailsImpl;
import com.example.schoolmanagement.common.role.model.Roles;
import com.example.schoolmanagement.common.role.repository.RolesRepository;
import com.example.schoolmanagement.common.user.dto.UserDto;
import com.example.schoolmanagement.common.user.model.User;
import com.example.schoolmanagement.common.user.repository.UserRepository;
import com.example.schoolmanagement.common.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/v1/auth", produces = "application/json")
@CrossOrigin(origins = "http://localhost:5173/", allowCredentials = "true")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolesRepository rolesRepository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    private final JwtUtils jwtUtils = new JwtUtils();

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        ResponseCookie cookie = jwtUtils.generateJwtCookie(userDetails);
        String token = jwtUtils.generateJwtToken(userDetails);
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body(new JwtResponse(token));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDto userDto) {
        MessageResponse messageResponse = new MessageResponse();
        if (userService.usernameExists(userDto.getName())) {
            messageResponse.setMessage("Username already exists!");
            return ResponseEntity.badRequest().body(messageResponse);
        }
        if (userService.emailExists(userDto.getEmail())) {
            messageResponse.setMessage("Email already exists!");
            return ResponseEntity.badRequest().body(messageResponse);
        }
        userDto.setRoles(Collections.singleton("USER"));
        User user = new User(userDto.getName(), userDto.getEmail(), encoder.encode(userDto.getPassword()));
        Set<String> strRoles = userDto.getRoles();
        List<Roles> roles = rolesRepository.findAllById(strRoles == null ? Collections.EMPTY_LIST : strRoles);
        if (roles.size() != (strRoles == null ? 0 : strRoles.size())) {
            throw new RuntimeException("One of roles not found!");
        }
        if (strRoles != null) {
            Roles oRoles = rolesRepository.findById("USER").orElseThrow(() -> new RuntimeException("Error: Role is not found!"));
            roles.add(oRoles);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "ADMIN":
                        Roles adminRole = rolesRepository.findById("ADMIN").orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                        roles.add(adminRole);
                        break;
                    case "MODERATOR":
                        Roles moderatorRole = rolesRepository.findById("MODERATOR").orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                        roles.add(moderatorRole);
                        break;
                    case "STUDENT":
                        Roles studentRole = rolesRepository.findById("STUDENT").orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                        roles.add(studentRole);
                        break;
                    case "TEACHER":
                        Roles teacherRole = rolesRepository.findById("TEACHER").orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                        roles.add(teacherRole);
                        break;
                    default:
                        Roles userRole = rolesRepository.findById("USER").orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles.stream().collect(Collectors.toSet()));
        userRepository.save(user);
        messageResponse.setMessage("User Registered Successfully!");
        return ResponseEntity.ok(messageResponse);
    }

    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body(new MessageResponse("You've been signed out!"));
    }

}
