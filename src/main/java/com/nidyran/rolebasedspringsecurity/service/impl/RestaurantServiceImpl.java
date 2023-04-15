package com.nidyran.rolebasedspringsecurity.service.impl;

import com.nidyran.rolebasedspringsecurity.dao.entity.Restaurant;
import com.nidyran.rolebasedspringsecurity.dao.entity.User;
import com.nidyran.rolebasedspringsecurity.dao.repository.RestaurantRepository;
import com.nidyran.rolebasedspringsecurity.dao.repository.UserRepository;
import com.nidyran.rolebasedspringsecurity.service.RestaurantService;
import com.nidyran.rolebasedspringsecurity.service.model.AddRestaurantDto;
import com.nidyran.rolebasedspringsecurity.service.model.LoginResponseDto;
import com.nidyran.rolebasedspringsecurity.service.model.RestaurantDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;




    @Override
    public RestaurantDto create(AddRestaurantDto addRestaurantDto) {
        log.warn("Restaurant added by {}", SecurityContextHolder.getContext().getAuthentication().getName());
        return modelMapper.map(restaurantRepository.save(modelMapper.map(addRestaurantDto, Restaurant.class)), RestaurantDto.class);
    }


    @Override
    public List<RestaurantDto> findAll() {
        return restaurantRepository.findAll().stream().map(restaurant -> modelMapper.map(restaurant, RestaurantDto.class)).collect(Collectors.toList());
    }
}