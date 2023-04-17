package com.nidyran.rolebasedspringsecurity.web;

import com.nidyran.rolebasedspringsecurity.service.RestaurantService;
import com.nidyran.rolebasedspringsecurity.service.model.restaurant.AddRestaurantDto;
import com.nidyran.rolebasedspringsecurity.service.model.restaurant.RestaurantDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Restaurant Resource")
@RequestMapping("/restaurant-configuration")
@PreAuthorize("@securityService.hasAnyRole('RESTAURANT_AUTHORITY')")
public class    RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping("/restaurant/create")
    public ResponseEntity<RestaurantDto> createRestaurant(@RequestBody AddRestaurantDto addRestaurantDto) {
        return ResponseEntity.ok(restaurantService.createRestaurant(addRestaurantDto));
    }

    @PutMapping("/restaurant/update/{id}")
    public ResponseEntity<RestaurantDto> updateRestaurantInfo(@PathVariable("id") long restaurantId, @RequestBody RestaurantDto updateRestaurantDto) {
        return ResponseEntity.ok(restaurantService.updateRestaurantInfo(restaurantId, updateRestaurantDto));
    }

    @DeleteMapping("/restaurant/delete/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable("id") long restaurantId) {
        restaurantService.deleteRestaurant(restaurantId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/restaurant/restaurant/{id}")
    public ResponseEntity<RestaurantDto> getRestaurantById(@PathVariable("id") long restaurantId) {
        return ResponseEntity.ok(restaurantService.getRestaurantById(restaurantId));
    }

    @GetMapping("/restaurants/all")
    public ResponseEntity<List<RestaurantDto>> getAllRestaurants() {
        return ResponseEntity.ok(restaurantService.getAllRestaurants());
    }

    @GetMapping("/search")
    public ResponseEntity<List<RestaurantDto>> searchRestaurants(@RequestParam("keyword") String keyword) {
        return ResponseEntity.ok(restaurantService.searchRestaurants(keyword));
    }
}