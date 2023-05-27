package com.nidyran.rolebasedspringsecurity.service.model.meal;

import com.nidyran.rolebasedspringsecurity.dao.entity.Category;
import com.nidyran.rolebasedspringsecurity.service.model.category.CategoryDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class MealDto {
    private Long id ;
    private String name;
    private double price;
    private String desc;
    private String photo;
    private long categoryId;



}
