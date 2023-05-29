package com.nidyran.rolebasedspringsecurity.service.model.user;

import com.nidyran.rolebasedspringsecurity.enmus.AuthorityEnum;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class    LoginResponseDto {
    private long id;
    private String token;
    private AuthorityEnum authority;
    private String firstName;
    private String lastName;
    private String phone;
    private String image;
    private String email;


}
