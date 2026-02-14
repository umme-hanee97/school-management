package com.example.schoolmanagement.user.service;

import com.example.schoolmanagement.role.model.Roles;
import com.example.schoolmanagement.role.repository.RolesRepository;
import com.example.schoolmanagement.user.dto.UserDto;
import com.example.schoolmanagement.user.model.User;
import com.example.schoolmanagement.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolesRepository rolesRepository;

    private BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    public List<UserDto> getAll(){
        List<User> users = userRepository.findAll(Sort.by("name"));
        return users.stream().map(user -> mapToDto(user, new UserDto())).toList();
    }

    public UserDto getById(Long id){
        UserDto userDto = new UserDto();
        User user = userRepository.findById(id).get();
        mapToDto(user, userDto);
        return userDto;
    }

    public UserDto get(String username){
        Optional<User> user = userRepository.findByName(username);
        return user.map(oUser -> mapToDto(oUser, new UserDto())).orElseThrow(RuntimeException::new);
    }

    public String saveData(UserDto userDto){
        User user = new User();
        mapToEntity(user, userDto);
        return userRepository.save(user).getName();
    }

    public String updateData(String username, UserDto userDto){
        User user = userRepository.findByName(username).orElseThrow(RuntimeException::new);
        mapToEntity(user, userDto);
         return userRepository.save(user).getName();
    }

    public void delete(String username){
        userRepository.deleteByName(username);
    }

    public boolean usernameExists(String username){
        return userRepository.existsByNameIgnoreCase(username);
    }

    public boolean emailExists(String email){
        return userRepository.existsByEmailIgnoreCase(email);
    }

    private UserDto mapToDto(User user, UserDto dto){
        dto.setName(user.getName());
        dto.setPassword(user.getPassword());
        dto.setEmail(user.getEmail());
        dto.setRoles(user.getRoles() == null ? null : user.getRoles().stream().map(Roles::getName).collect(Collectors.toSet()));
        return dto;
    }

    private User mapToEntity(User user, UserDto dto){
        user.setName(dto.getName());
        user.setPassword(encoder().encode(dto.getPassword()));
        user.setEmail(dto.getEmail());
        user.setEnabled(true);
        List<Roles> roles = rolesRepository.findAllById(dto.getRoles() == null ? Collections.EMPTY_LIST : dto.getRoles());
        if (roles.size() != (dto.getRoles() == null ? 0 : dto.getRoles().size())){
            throw new RuntimeException("One of roles not found!");
        }
        user.setRoles(roles.stream().collect(Collectors.toSet()));
        return user;
    }
}
