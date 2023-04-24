package com.nidyran.rolebasedspringsecurity.service.model.commande;

import com.nidyran.rolebasedspringsecurity.dao.entity.Commande;
import com.nidyran.rolebasedspringsecurity.dao.entity.Meal;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@RequiredArgsConstructor
public class AddCommandeItemDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private Long panierItemId;

    private Integer quantity;


}
