package com.nidyran.rolebasedspringsecurity.service.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CommandeItemDTO {

    private Long id;
    private String nom;
    private String description;
    private double price;
    private int qte;



}
