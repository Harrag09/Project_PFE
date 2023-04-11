package com.nidyran.rolebasedspringsecurity.service.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class RestaurantDto {

    private long id;
    private String name;
    private String address;
    private String log;
}
