package com.nidyran.rolebasedspringsecurity.service;

import com.nidyran.rolebasedspringsecurity.dao.repository.UserRepository;
import com.nidyran.rolebasedspringsecurity.enmus.AuthorityEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component("securityService")
public class SecurityService {
    private final UserRepository userRepository;

    @Value("${application.security.enabled:true}")
    private boolean securityEnabled;
    public boolean hasAnyRole(String role) {
        if (!securityEnabled) {
            return true;
        }
        final SecurityContext context = SecurityContextHolder.getContext();
        final String username = context.getAuthentication().getPrincipal().toString();
        return userRepository.findByUsername(username).getAuthority().equals(AuthorityEnum.valueOf(role));
    }
}
