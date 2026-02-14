package com.example.schoolmanagement.common.Controller;

import com.example.schoolmanagement.common.model.JwtResponse;
import com.example.schoolmanagement.common.model.LoginRequest;
import com.example.schoolmanagement.common.model.MessageResponse;
import com.example.schoolmanagement.common.security.jwt.JwtUtils;
import com.example.schoolmanagement.common.security.service.UserDetailsImpl;
import com.example.schoolmanagement.role.model.Roles;
import com.example.schoolmanagement.role.repository.RolesRepository;
import com.example.schoolmanagement.role.service.RolesService;
import com.example.schoolmanagement.user.dto.UserDto;
import com.example.schoolmanagement.user.model.User;
import com.example.schoolmanagement.user.repository.UserRepository;
import com.example.schoolmanagement.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("auth")
public class AuthController {

    private AuthenticationManager manager;

    @Autowired
    private UserService userService;

    @Autowired
    private RolesRepository rolesRepository;

    private PasswordEncoder encoder;

    private JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest request){
        Authentication authentication = manager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        ResponseCookie cookie = jwtUtils.generateJwtCookie(userDetails);
        String token = jwtUtils.generateJwtToken(userDetails);
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body(new JwtResponse(token));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDto userDto){
        if (userService.usernameExists(userDto.getName())){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: username already exists!"));
        }
        if (userService.emailExists(userDto.getEmail())){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: email already exists!"));
        }
        userDto.setRoles(Collections.singleton("USER"));
        User user = new User(userDto.getName(), userDto.getEmail(), encoder.encode(userDto.getPassword()));
        Set<String> strRoles = userDto.getRoles();
        List<Roles> roles = rolesRepository.findAllById(strRoles == null ? Collections.EMPTY_LIST : strRoles);
        if (roles.size() != (strRoles == null ? 0 : strRoles.size())){
            throw new RuntimeException("One of roles not found!");
        }
        if (strRoles != null){
            Roles oRoles = rolesRepository.findById("USER").orElseThrow(() -> new RuntimeException("Error: Role is not found!"));
            roles.add(oRoles);
        }
        return ResponseEntity.ok(user);
    }

}
