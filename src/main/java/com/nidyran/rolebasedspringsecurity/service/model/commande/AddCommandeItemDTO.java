package com.nidyran.rolebasedspringsecurity.service.model.commande;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class AddCommandeItemDTO {
    private Long mealId;

    private int quantity;

}
