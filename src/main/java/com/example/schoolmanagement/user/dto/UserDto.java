package com.example.schoolmanagement.user.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter@RequiredArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private Set<String> roles = new HashSet<>();
}
