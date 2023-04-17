package com.nidyran.rolebasedspringsecurity.service.model.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@RequiredArgsConstructor
public class AddUserDto {
    private long id;
    private String username;
    private String password;
    private boolean enabled;
}
