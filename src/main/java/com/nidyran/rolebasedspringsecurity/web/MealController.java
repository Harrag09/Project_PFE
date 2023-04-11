package com.nidyran.rolebasedspringsecurity.web;


import com.nidyran.rolebasedspringsecurity.service.MealService;
import com.nidyran.rolebasedspringsecurity.service.model.AddMealDto;
import com.nidyran.rolebasedspringsecurity.service.model.MealDto;
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
@Tag(name = "Meals Resource")
@RequestMapping("/meal-configuration")
@PreAuthorize("@securityService.hasAnyRole('RESTAURANT_AUTHORITY')")
public class MealController {
    private final MealService mealService;

    @PostMapping("/plats")
    public ResponseEntity<MealDto> create(@RequestBody AddMealDto addMealDto) {
        return ResponseEntity.ok(mealService.create(addMealDto));
    }
}
