package com.example.schoolmanagement.common.user.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private Set<String> roles = new HashSet<>();

    public UserDto(String name, String email, String s) {
        this.name = name;
        this.email = email;
        this.password = s;
    }
}
