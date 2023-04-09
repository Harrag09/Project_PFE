package com.nidyran.rolebasedspringsecurity.bootstrap;

import com.nidyran.rolebasedspringsecurity.enmus.AuthorityEnum;
import com.nidyran.rolebasedspringsecurity.service.AuthenticationService;
import com.nidyran.rolebasedspringsecurity.utils.BackendUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DummyData implements CommandLineRunner {

    public static final String CUSTOMER = "customer";
    public static final String RESTAURANT = "restaurant";
    private final AuthenticationService authenticationService;

    @Value("${default.user.login}")
    private String username;

    @Value("${default.user.password}")
    private String password;

    @Value("${default.user.authority}")
    private String authority;

    @Value("${default.user.active}")
    private boolean active;

    @Value("${jwt.validity}")
    private Long validity;

    @Value("${jwt.secret}")
    private String secret;

    @Override
    public void run(String... args) {
        addUser(username, password, AuthorityEnum.valueOf(authority), active);
        addUser(CUSTOMER, CUSTOMER, AuthorityEnum.CUSTOMER_AUTHORITY, true);
        addUser("customer_disabled", CUSTOMER, AuthorityEnum.CUSTOMER_AUTHORITY, false);
        addUser(RESTAURANT, RESTAURANT, AuthorityEnum.RESTAURANT_AUTHORITY, true);
        addUser("restaurant_disabled", RESTAURANT, AuthorityEnum.RESTAURANT_AUTHORITY, false);
    }

    private void addUser(String username, String password, AuthorityEnum authorityEnum, boolean active) {
        authenticationService.register(username, password, authorityEnum, active);
        log.warn("{} token {}", username, BackendUtils.generateToken(username, secret, validity));
    }
}