package com.nidyran.rolebasedspringsecurity.service.model.category;

import com.nidyran.rolebasedspringsecurity.service.model.meal.MealDto;
import com.nidyran.rolebasedspringsecurity.service.model.restaurant.RestaurantDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class CategoryDto {
    private long id;
    private long restaurantId;
    private String name;
    private String image;



}
