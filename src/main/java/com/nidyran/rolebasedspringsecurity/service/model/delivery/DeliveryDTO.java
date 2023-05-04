package com.nidyran.rolebasedspringsecurity.service.model.delivery;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@RequiredArgsConstructor
public class DeliveryDTO {

    private long id;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String image;
}
