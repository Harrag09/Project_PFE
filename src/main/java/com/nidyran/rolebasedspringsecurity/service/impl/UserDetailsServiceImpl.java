package com.nidyran.rolebasedspringsecurity.service.impl;

import com.nidyran.rolebasedspringsecurity.dao.repository.UserRepository;
import com.nidyran.rolebasedspringsecurity.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return new CustomUserDetails(userRepository.findByUsername(username));
    }



}
