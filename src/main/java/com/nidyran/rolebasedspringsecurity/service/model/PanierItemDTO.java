package com.nidyran.rolebasedspringsecurity.service.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class PanierItemDTO {
    private Long id;
    private MealDto meal;
    private Integer qte;


}
