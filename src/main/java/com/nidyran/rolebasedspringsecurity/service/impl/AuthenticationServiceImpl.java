package com.nidyran.rolebasedspringsecurity.service.impl;

import com.nidyran.rolebasedspringsecurity.dao.entity.User;
import com.nidyran.rolebasedspringsecurity.dao.repository.UserRepository;
import com.nidyran.rolebasedspringsecurity.enmus.AuthorityEnum;
import com.nidyran.rolebasedspringsecurity.service.AuthenticationService;
import com.nidyran.rolebasedspringsecurity.service.model.LoginRequestDto;
import com.nidyran.rolebasedspringsecurity.service.model.LoginResponseDto;
import com.nidyran.rolebasedspringsecurity.service.model.RegisterRequestDto;
import com.nidyran.rolebasedspringsecurity.service.model.RegisterResponseDto;
import com.nidyran.rolebasedspringsecurity.utils.BackendUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${jwt.validity}")
    private Long validity;

    @Value("${jwt.secret}")
    private String secret;

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword()));
        return LoginResponseDto.builder()
                .token(BackendUtils.generateToken(loginRequestDto.getUsername(), secret, validity))
                .build();
    }

    @Override
    public RegisterResponseDto register(RegisterRequestDto registerRequestDto) {
        if (registerRequestDto.getAuthority().equals(AuthorityEnum.ADMIN_AUTHORITY)) {
            log.warn("Admin registration is not allowed");
            return null;
        }
        if(AuthorityEnum.RESTAURANT_AUTHORITY.equals(registerRequestDto.getAuthority())){
            return register(registerRequestDto.getUsername(), registerRequestDto.getPassword(), AuthorityEnum.RESTAURANT_AUTHORITY, false);
        }
        return register(registerRequestDto.getUsername(), registerRequestDto.getPassword(), AuthorityEnum.CUSTOMER_AUTHORITY, true);
    }

    @Override
    public RegisterResponseDto register(String username, String password, AuthorityEnum authorityEnum, boolean active) {
        User user = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .authority(authorityEnum)
                .enabled(active)
                .build();
        user = userRepository.save(user);
        return RegisterResponseDto.builder().id(user.getId()).username(user.getUsername()).build();
    }
}
