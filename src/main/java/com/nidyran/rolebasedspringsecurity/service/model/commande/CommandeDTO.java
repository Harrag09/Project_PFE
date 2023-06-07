package com.nidyran.rolebasedspringsecurity.service.model.commande;



import lombok.Getter;

import lombok.RequiredArgsConstructor;
import lombok.Setter;




@Getter
@Setter
@RequiredArgsConstructor
public class CommandeDTO {

    private Long userId;

    private Long PanierId;

    private String address;

    private String nom;

    private String tel;
    private double total;
    private String description;
    private String commandeStatus;
    private String paymentMethod;
    private long restaurantId;

    private String latitude;
    private String longitude;
}


