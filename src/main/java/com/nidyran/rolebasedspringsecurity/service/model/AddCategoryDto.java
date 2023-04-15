package com.nidyran.rolebasedspringsecurity.service.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class AddCategoryDto {


    private long id;
    private String name;
    private String image;
    private RestaurantDto restaurant;
}
