package com.nidyran.rolebasedspringsecurity.service.model.category;

import com.nidyran.rolebasedspringsecurity.service.model.meal.AddMealDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class AddCategoryDto {
    private long id;
    private long restaurantId;
    private String name;
    private String image;

}
