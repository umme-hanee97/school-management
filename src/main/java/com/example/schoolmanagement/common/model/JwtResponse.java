package com.example.schoolmanagement.common.model;

import com.example.schoolmanagement.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class JwtResponse {

    private User user;
    private String jwtToken;

    public JwtResponse(String token) {
        this.jwtToken = token;
    }
}
