package com.nidyran.rolebasedspringsecurity.service.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponseDto {
    private String token;
}
