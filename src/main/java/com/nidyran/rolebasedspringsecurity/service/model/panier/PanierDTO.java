package com.nidyran.rolebasedspringsecurity.service.model.panier;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class PanierDTO {
    private Long id;
    private long userId;
    private double total;

}
