package com.nidyran.rolebasedspringsecurity.bootstrap;

import com.nidyran.rolebasedspringsecurity.enmus.AuthorityEnum;
import com.nidyran.rolebasedspringsecurity.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
@Slf4j
@RequiredArgsConstructor
public class DummyData implements CommandLineRunner {

    private final AuthenticationService authenticationService;

    @Value("${default.user.login}")
    private String username;

    @Value("${default.user.password}")
    private String password;

    @Value("${default.user.authority}")
    private String authority;

    @Override
    public void run(String... args) {
        authenticationService.register(username, password, AuthorityEnum.valueOf(authority));
    }
}
