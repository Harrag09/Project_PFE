package com.nidyran.rolebasedspringsecurity.service.model.panier;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Getter
@Setter
@RequiredArgsConstructor
public class AddPanierDTO {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private long userId;
    private double total;

}
