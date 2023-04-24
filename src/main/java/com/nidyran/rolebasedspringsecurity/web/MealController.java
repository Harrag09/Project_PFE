package com.nidyran.rolebasedspringsecurity.web;


import com.nidyran.rolebasedspringsecurity.service.MealService;
import com.nidyran.rolebasedspringsecurity.service.model.meal.AddMealDto;
import com.nidyran.rolebasedspringsecurity.service.model.meal.MealDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Meals Resource")
@RequestMapping("/meal-configuration")
//@PreAuthorize("@securityService.hasAnyRole('RESTAURANT_AUTHORITY')")
public class MealController {
    private final MealService mealService;

    @PostMapping("/meal/create")
    public ResponseEntity<MealDto> createMeal(@Valid @RequestBody AddMealDto addMealDto) {
        MealDto mealDto = mealService.create(addMealDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(mealDto);
    }

    @PutMapping("/meal/update/{mealId}")
    public ResponseEntity<MealDto> updateMealInfo(@PathVariable long mealId, @Valid @RequestBody MealDto updateMealDto) {
        MealDto mealDto = mealService.updateMealInfo(mealId, updateMealDto);
        return ResponseEntity.ok(mealDto);
    }

    @DeleteMapping("/meal/delete/{mealId}")
    public ResponseEntity<Void> deleteMeal(@PathVariable long mealId) {
        mealService.deleteMeal(mealId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/meal/getById/{mealId}")
    public ResponseEntity<MealDto> getMealById(@PathVariable long mealId) {
        MealDto mealDto = mealService.getMealById(mealId);
        return ResponseEntity.ok(mealDto);
    }

    @GetMapping("/meal/search")
    public ResponseEntity<List<MealDto>> searchMeals(@RequestParam String keyword) {
        List<MealDto> meals = mealService.searchMeals(keyword);
        return ResponseEntity.ok(meals);
    }

    @GetMapping("/byCategory/{categoryId}")
    public ResponseEntity<List<MealDto>> getMealsByCategory(@PathVariable long categoryId) {
        List<MealDto> meals = mealService.getMealsByCategory(categoryId);
        return ResponseEntity.ok(meals);
    }

}
