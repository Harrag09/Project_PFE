package com.nidyran.rolebasedspringsecurity.service.model.commande;

import com.nidyran.rolebasedspringsecurity.dao.entity.Commande;
import com.nidyran.rolebasedspringsecurity.service.model.meal.MealDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class AddCommandeItemDTO {


    private Long mealId;
    private Integer quantity;
    private Commande commande;


}
