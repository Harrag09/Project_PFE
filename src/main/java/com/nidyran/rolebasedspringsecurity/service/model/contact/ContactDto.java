package com.nidyran.rolebasedspringsecurity.service.model.contact;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@RequiredArgsConstructor
public class ContactDto {


    private Long id;
    private String name;
    private String email;
    private String  message;
    private long userId;
    private long restaurantId;


}
