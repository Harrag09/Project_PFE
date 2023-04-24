package com.nidyran.rolebasedspringsecurity.service.model.category;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Getter
@Setter
@RequiredArgsConstructor
public class AddCategoryDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private long restaurantId;
    private String name;
    private String image;

}
