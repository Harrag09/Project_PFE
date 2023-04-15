package com.nidyran.rolebasedspringsecurity.service.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class PanierDTO {

    private Long id;
    private UserDto user;
    private List<PanierItemDTO> panierItems;

}
