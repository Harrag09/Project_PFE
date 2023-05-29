package com.nidyran.rolebasedspringsecurity.service.model.user;

import com.nidyran.rolebasedspringsecurity.enmus.AuthorityEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;


@Getter
@Setter
@RequiredArgsConstructor
public class UserDto {
    private long id;

    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private AuthorityEnum authority;
    private String firstName;
    private String lastName;
    private String phone;
    private String image;
    private String email;




}
