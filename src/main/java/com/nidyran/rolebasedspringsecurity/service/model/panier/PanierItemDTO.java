package com.nidyran.rolebasedspringsecurity.service.model.panier;

import com.nidyran.rolebasedspringsecurity.service.model.meal.MealDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class PanierItemDTO {
    private Long id;
    private long idPanier;
    private long mealId;
    private int  qty;
    private String NameMeal;
    private double price;
    private String image;


}
