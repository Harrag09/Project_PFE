package com.nidyran.rolebasedspringsecurity.service.model.commande;



import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@RequiredArgsConstructor
public class AddCommandeDTO {



    private Long userId;

    private Long PanierId;

    private String address;

    private String nom;

    private String tel;

    private String description;

    private String paymentMethod;

    private Long restaurantId;
}
