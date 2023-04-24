package com.nidyran.rolebasedspringsecurity.web;

import com.nidyran.rolebasedspringsecurity.service.AuthenticationService;
import com.nidyran.rolebasedspringsecurity.service.model.user.LoginRequestDto;
import com.nidyran.rolebasedspringsecurity.service.model.user.LoginResponseDto;
import com.nidyran.rolebasedspringsecurity.service.model.user.RegisterRequestDto;
import com.nidyran.rolebasedspringsecurity.service.model.user.RegisterResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Authentication Resource")
@RequestMapping("/authentication-management")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody final LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok(authenticationService.login(loginRequestDto));
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> register(@RequestBody final RegisterRequestDto registerRequestDto) {
        return ResponseEntity.ok(authenticationService.register(registerRequestDto));
    }


}
