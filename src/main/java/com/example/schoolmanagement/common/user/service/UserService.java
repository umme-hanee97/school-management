package com.example.schoolmanagement.common.user.service;

import com.example.schoolmanagement.common.model.ErrorHandler;
import com.example.schoolmanagement.common.user.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAll();
    UserDto getById(Long id);
    UserDto get(String username) throws ErrorHandler;
    UserDto saveData(UserDto userDto);
    UserDto updateData(String username, UserDto userDto) throws ErrorHandler;
    void delete(String username);
    boolean usernameExists(String username);
    boolean emailExists(String email);
    UserDto changePassword(UserDto userDto, String newPassword) throws ErrorHandler;
}
