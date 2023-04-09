package com.nidyran.rolebasedspringsecurity.enmus;

import org.springframework.security.core.GrantedAuthority;

public enum AuthorityEnum implements GrantedAuthority {
    ADMIN_AUTHORITY, CUSTOMER_AUTHORITY , RESTAURANT_AUTHORITY;

    @Override
    public String getAuthority() {
        return name();
    }
}
