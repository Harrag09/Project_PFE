package com.nidyran.rolebasedspringsecurity.service.model.commande;

import com.nidyran.rolebasedspringsecurity.enmus.CommandeStatus;
import com.nidyran.rolebasedspringsecurity.enmus.PaymentMethod;
import com.nidyran.rolebasedspringsecurity.service.model.restaurant.RestaurantDto;
import com.nidyran.rolebasedspringsecurity.service.model.user.LoginRequestDto;
import lombok.Getter;

import lombok.RequiredArgsConstructor;
import lombok.Setter;


import java.util.List;


@Getter
@Setter
@RequiredArgsConstructor
public class CommandeDTO {
    private long id;

    private CommandeStatus status;

    private PaymentMethod paymentMethod;

    private List<CommandeItemDTO> items;

    private LoginRequestDto user;

    private RestaurantDto restaurant;





}
