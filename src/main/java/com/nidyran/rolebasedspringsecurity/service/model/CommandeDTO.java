package com.nidyran.rolebasedspringsecurity.service.model;

import com.nidyran.rolebasedspringsecurity.enmus.CommandeStatus;
import com.nidyran.rolebasedspringsecurity.enmus.PaymentMethod;
import lombok.Getter;

import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@RequiredArgsConstructor
public class CommandeDTO {

    private Long id;
    private LocalDateTime dateCommande;
    private CommandeStatus status;
    private PaymentMethod paymentMethod;
    private  UserDto user;
    private RestaurantDto restaurant;
    private List<CommandeItemDTO> commandeItems;
}
