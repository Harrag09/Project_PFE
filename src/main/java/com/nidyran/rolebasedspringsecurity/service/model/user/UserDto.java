package com.nidyran.rolebasedspringsecurity.service.model.user;

import com.nidyran.rolebasedspringsecurity.enmus.AuthorityEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@RequiredArgsConstructor
public class UserDto {
    private long id;
    private String username;
    private String email;
    private String password;
    private boolean enabled;
    private AuthorityEnum authority;
}
