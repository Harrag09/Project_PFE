package com.nidyran.rolebasedspringsecurity.service.model.restaurant;

import com.nidyran.rolebasedspringsecurity.dao.entity.User;
import com.nidyran.rolebasedspringsecurity.service.model.category.AddCategoryDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class AddRestaurantDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String address;
    private String log;
    private long userId;


}
