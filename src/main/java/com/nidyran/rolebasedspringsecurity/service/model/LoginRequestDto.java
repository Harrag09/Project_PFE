package com.nidyran.rolebasedspringsecurity.service.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginRequestDto {
    private String username;
    private String password;
}
