package com.nidyran.rolebasedspringsecurity.service.model.commande;

import com.nidyran.rolebasedspringsecurity.dao.entity.*;
import com.nidyran.rolebasedspringsecurity.enmus.CommandeStatus;
import com.nidyran.rolebasedspringsecurity.enmus.PaymentMethod;
import com.nidyran.rolebasedspringsecurity.service.model.restaurant.RestaurantDto;
import com.nidyran.rolebasedspringsecurity.service.model.user.LoginRequestDto;
import lombok.Getter;

import lombok.RequiredArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@RequiredArgsConstructor
public class CommandeDTO {

    private Long id;
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
