package com.nidyran.rolebasedspringsecurity.service.impl;

import com.nidyran.rolebasedspringsecurity.dao.entity.Restaurant;
import com.nidyran.rolebasedspringsecurity.dao.repository.RestaurantRepository;
import com.nidyran.rolebasedspringsecurity.service.RestaurantService;
import com.nidyran.rolebasedspringsecurity.service.model.AddRestaurantDto;
import com.nidyran.rolebasedspringsecurity.service.model.RestaurantDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;

@Override
    public RestaurantDto create(AddRestaurantDto addRestaurantDto) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(addRestaurantDto.getName());
        restaurant.setAddress(addRestaurantDto.getAddress());
        restaurant.setLog(addRestaurantDto.getLog());
        restaurant = restaurantRepository.save(restaurant);
        RestaurantDto restaurantDto = new RestaurantDto();
        restaurantDto.setId(restaurant.getId());
        restaurantDto.setName(restaurant.getName());
        restaurantDto.setAddress(restaurant.getAddress());
        restaurantDto.setLog(restaurant.getLog());
        log.warn("Restaurant added by {}", SecurityContextHolder.getContext().getAuthentication().getName());
        return restaurantDto;
    }

@Override
    public List<RestaurantDto> findAll() {
        return restaurantRepository.findAll().stream().map(restaurant -> {
            RestaurantDto restaurantDto = new RestaurantDto();
            restaurantDto.setId(restaurant.getId());
            restaurantDto.setName(restaurant.getName());
            restaurantDto.setAddress(restaurant.getAddress());
            restaurantDto.setLog(restaurant.getLog());
            return restaurantDto;
        }).collect(Collectors.toList());
    }
}
