package com.nidyran.rolebasedspringsecurity.service.model.panier;

import com.nidyran.rolebasedspringsecurity.service.model.meal.MealDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class AddPanierItemDTO {

    private Long id;
    private long idPanier;
    private MealDto meal;
    private Integer qte;


}
