package com.example.schoolmanagement.common.user.service;

import com.example.schoolmanagement.common.model.ErrorHandler;
import com.example.schoolmanagement.common.role.model.Roles;
import com.example.schoolmanagement.common.role.repository.RolesRepository;
import com.example.schoolmanagement.common.user.dto.UserDto;
import com.example.schoolmanagement.common.user.model.User;
import com.example.schoolmanagement.common.user.repository.UserRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor(force = true)
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolesRepository rolesRepository;

    private PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public List<UserDto> getAll() {
        List<User> users = userRepository.findAll(Sort.by("name"));
        return users.stream().map(user -> mapToDto(user, new UserDto())).toList();
    }

    @Override
    public UserDto getById(Long id) {
        UserDto userDto = new UserDto();
        User user = userRepository.findById(id).get();
        mapToDto(user, userDto);
        return userDto;
    }

    @Override
    public UserDto get(String username) {
        Optional<User> user = userRepository.findByName(username);
        return user.map(oUser -> mapToDto(oUser, new UserDto())).orElseThrow(RuntimeException::new);
    }

    @Override
    public String saveData(UserDto userDto) {
        User user = new User();
        mapToEntity(user, userDto);
        return userRepository.save(user).getName();
    }

    @Override
    public String updateData(String username, UserDto userDto) {
        User user = userRepository.findByName(username).orElseThrow(RuntimeException::new);
        mapToEntity(user, userDto);
        return userRepository.save(user).getName();
    }

    @Override
    public void delete(String username) {
        userRepository.deleteByName(username);
    }

    @Override
    public boolean usernameExists(String username) {
        return userRepository.existsByNameIgnoreCase(username);
    }

    @Override
    public boolean emailExists(String email) {
        return userRepository.existsByEmailIgnoreCase(email);
    }

    @Override
    public UserDto changePassword(UserDto dto, String newPassword) throws ErrorHandler {
        UserDto userDto = get(dto.getName());
        try{
            if (!encoder().encode(dto.getPassword()).equals(userDto.getPassword())){
                throw new ErrorHandler("Current password is incorrect!");
            }
            userDto.setPassword(encoder().encode(newPassword));
            updateData(dto.getName(), userDto);
            return userDto;
        } catch (Exception e) {
            throw new ErrorHandler("Error occurred!!", e.getCause());
        }
    }

    private UserDto mapToDto(User user, UserDto dto) {
        dto.setName(user.getName());
        dto.setPassword(user.getPassword());
        dto.setEmail(user.getEmail());
        dto.setRoles(user.getRoles() == null ? null : user.getRoles().stream().map(Roles::getName).collect(Collectors.toSet()));
        return dto;
    }

    private User mapToEntity(User user, UserDto dto) {
        if (dto.getName() != null) user.setName(dto.getName());
        if (dto.getPassword() != null) user.setPassword(encoder().encode(dto.getPassword()));
        if (dto.getEmail() != null) user.setEmail(dto.getEmail());
        user.setEnabled(true);
        List<Roles> roles = rolesRepository.findAllById(dto.getRoles() == null ? Collections.EMPTY_LIST : dto.getRoles());
        if (roles.size() != (dto.getRoles() == null ? 0 : dto.getRoles().size())) {
            throw new RuntimeException("One of roles not found!");
        }
        user.setRoles(roles.stream().collect(Collectors.toSet()));
        return user;
    }
}
