package com.nidyran.rolebasedspringsecurity.service.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddRestaurantDto {
    private String name;
    private String address;
    private String log;
}
