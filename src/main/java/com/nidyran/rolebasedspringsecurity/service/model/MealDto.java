package com.nidyran.rolebasedspringsecurity.service.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MealDto {
    private long id;
    private String name;
    private double price;
    private String desc;
    private CategoryDto category;
}
