package com.nidyran.rolebasedspringsecurity.service.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class LoginResponseDto {
    private long id;
    private String token;
}
