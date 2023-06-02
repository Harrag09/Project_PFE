package com.nidyran.rolebasedspringsecurity.service.model.restaurant;

import com.nidyran.rolebasedspringsecurity.dao.entity.User;
import com.nidyran.rolebasedspringsecurity.service.model.category.CategoryDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class RestaurantDto {
    
         private long id;
         private String name;
         private String address;
         private String log;
         private long userId;
         private String latitude;
         private String longitude;
         private String log2;
}
    