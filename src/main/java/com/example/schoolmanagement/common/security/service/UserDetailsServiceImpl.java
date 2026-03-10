package com.example.schoolmanagement.common.security.service;

import com.example.schoolmanagement.common.user.model.User;
import com.example.schoolmanagement.common.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByName(username).orElseThrow(() -> new UsernameNotFoundException("User not found with user: " + username));
        return UserDetailsImpl.build(user);
    }
}
