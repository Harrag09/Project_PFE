package com.nidyran.rolebasedspringsecurity.service.model.panier;

import com.nidyran.rolebasedspringsecurity.service.model.meal.MealDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@RequiredArgsConstructor
public class AddPanierItemDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long PanierId;
    private long mealId;
    private Integer qte;


}
