package com.nidyran.rolebasedspringsecurity.service;

import com.nidyran.rolebasedspringsecurity.enmus.AuthorityEnum;
import com.nidyran.rolebasedspringsecurity.service.model.LoginRequestDto;
import com.nidyran.rolebasedspringsecurity.service.model.LoginResponseDto;
import com.nidyran.rolebasedspringsecurity.service.model.RegisterRequestDto;
import com.nidyran.rolebasedspringsecurity.service.model.RegisterResponseDto;

public interface AuthenticationService {
    LoginResponseDto login(LoginRequestDto loginRequestDto);
    RegisterResponseDto register(RegisterRequestDto registerRequestDto);
    RegisterResponseDto register(String username, String password, AuthorityEnum authorityEnum);
}