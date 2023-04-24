package com.nidyran.rolebasedspringsecurity.service.model.commande;


import com.nidyran.rolebasedspringsecurity.service.model.meal.MealDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@RequiredArgsConstructor
public class CommandeItemDTO {

    private Integer quantity;

    private MealDto meal;

}
