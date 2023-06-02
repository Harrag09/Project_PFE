package com.nidyran.rolebasedspringsecurity.service;

import com.nidyran.rolebasedspringsecurity.Exeption.RestaurantNotFoundException;
import com.nidyran.rolebasedspringsecurity.Exeption.UserNotFoundException;
import com.nidyran.rolebasedspringsecurity.dao.entity.Restaurant;
import com.nidyran.rolebasedspringsecurity.dao.entity.User;
import com.nidyran.rolebasedspringsecurity.dao.repository.RestaurantRepository;
import com.nidyran.rolebasedspringsecurity.dao.repository.UserRepository;
import com.nidyran.rolebasedspringsecurity.service.model.restaurant.AddRestaurantDto;
import com.nidyran.rolebasedspringsecurity.service.model.restaurant.RestaurantDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
@Service
@RequiredArgsConstructor
public class    RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public RestaurantDto createRestaurant(AddRestaurantDto addRestaurantDto) {
        log.warn("Restaurant added by {}", SecurityContextHolder.getContext().getAuthentication().getName());
        Restaurant restaurant = modelMapper.map(addRestaurantDto, Restaurant.class);
        return modelMapper.map(restaurantRepository.save(restaurant), RestaurantDto.class);
    }


    public RestaurantDto updateRestaurantInfo( long restaurantId, RestaurantDto updateRestaurantDto) {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurantId);
        if (restaurantOptional.isPresent()) {
            Restaurant restaurant = restaurantOptional.get();
            modelMapper.map(updateRestaurantDto, restaurant);
            restaurantRepository.save(restaurant);
            return modelMapper.map(restaurant, RestaurantDto.class);
        } else {
            throw new RestaurantNotFoundException(restaurantId);
        }
    }

    public void deleteRestaurant(long restaurantId) {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurantId);
        if (restaurantOptional.isPresent()) {
            Restaurant restaurant = restaurantOptional.get();
            restaurantRepository.delete(restaurant);
        } else {
            throw new RestaurantNotFoundException(restaurantId);
        }
    }

    public RestaurantDto getRestaurantById(long restaurantId) {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurantId);
        if (restaurantOptional.isPresent()) {
            Restaurant restaurant = restaurantOptional.get();
            return modelMapper.map(restaurant, RestaurantDto.class);
        } else {
            throw new RestaurantNotFoundException(restaurantId);
        }
    }

    public List<RestaurantDto> searchRestaurants(String keyword) {
        List<Restaurant> restaurants = restaurantRepository.findByNameContainingOrAddressContaining(keyword, keyword);
        return restaurants.stream().map(restaurant -> modelMapper.map(restaurant, RestaurantDto.class)).collect(Collectors.toList());
    }

    public List<RestaurantDto> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants.stream().map(restaurant -> modelMapper.map(restaurant, RestaurantDto.class)).collect(Collectors.toList());
    }

    public RestaurantDto getRestaurantIdByUserId(Long userId) {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findByUserId(userId);
        if (restaurantOptional.isPresent()) {
            Restaurant restaurant = restaurantOptional.get();
            return getRestaurantById(restaurant.getId());
        } else {
            throw new RestaurantNotFoundException(userId);
        }
    }
}