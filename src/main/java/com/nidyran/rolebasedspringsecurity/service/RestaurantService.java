package com.nidyran.rolebasedspringsecurity.service;

import com.nidyran.rolebasedspringsecurity.service.model.AddRestaurantDto;
import com.nidyran.rolebasedspringsecurity.service.model.RestaurantDto;
import java.util.List;

public interface RestaurantService {
    RestaurantDto create(AddRestaurantDto addRestaurantDto);

    List<RestaurantDto> findAll();
}
