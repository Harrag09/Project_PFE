package com.nidyran.rolebasedspringsecurity.web;

import com.nidyran.rolebasedspringsecurity.service.RestaurantService;
import com.nidyran.rolebasedspringsecurity.service.model.AddRestaurantDto;
import com.nidyran.rolebasedspringsecurity.service.model.RestaurantDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Restaurant Resource")
@RequestMapping("/restaurant-configuration")
@PreAuthorize("@securityService.hasAnyRole('RESTAURANT_AUTHORITY')")
public class RestaurantController {
    private final RestaurantService restaurantService;

    @PostMapping("/restaurant")
    public ResponseEntity<RestaurantDto> create(@RequestBody AddRestaurantDto addRestaurantDto) {
        return ResponseEntity.ok(restaurantService.create(addRestaurantDto));
    }
}
