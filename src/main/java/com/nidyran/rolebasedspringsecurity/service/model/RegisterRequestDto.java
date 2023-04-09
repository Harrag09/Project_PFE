package com.nidyran.rolebasedspringsecurity.service.model;

import com.nidyran.rolebasedspringsecurity.enmus.AuthorityEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDto {
    private String username;
    private String password;
    private AuthorityEnum authority;
}
