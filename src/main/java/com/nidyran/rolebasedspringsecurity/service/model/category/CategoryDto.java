package com.nidyran.rolebasedspringsecurity.service.model.category;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@RequiredArgsConstructor
public class CategoryDto {
    private long id;
    private long restaurantId;
    private String name;
    private String image;

}
