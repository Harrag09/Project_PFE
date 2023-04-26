package com.nidyran.rolebasedspringsecurity.service.model.commande;


import com.nidyran.rolebasedspringsecurity.enmus.CommandeStatus;
import com.nidyran.rolebasedspringsecurity.enmus.PaymentMethod;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Getter
@Setter
@RequiredArgsConstructor
public class AddCommandeDTO {

    private long id;
    private Integer quantity;
    private String address;
    private String tel ;
    private String desc;

    private PaymentMethod paymentMethod;
    private CommandeStatus status;
    private long mealId;
    private long panierId;
    private long panierItemId;
    private long userId;

    private long restaurantId;
}
