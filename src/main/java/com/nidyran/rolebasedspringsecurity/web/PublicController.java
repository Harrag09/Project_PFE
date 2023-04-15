package com.nidyran.rolebasedspringsecurity.web;

import com.nidyran.rolebasedspringsecurity.service.CategoryService;
import com.nidyran.rolebasedspringsecurity.service.MealService;
import com.nidyran.rolebasedspringsecurity.service.model.AddMealDto;
import com.nidyran.rolebasedspringsecurity.service.model.CategoryDto;
import com.nidyran.rolebasedspringsecurity.service.model.MealDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Public Resource")
@RequestMapping("/public-resources")
public class PublicController {
    private final CategoryService categoryService;
    private final MealService mealService;

    @GetMapping("/categories")
    public List<CategoryDto> findAll() {
        return categoryService.findAll();
    }



    @GetMapping("/plats")
    public List<MealDto> findAllPlats() {
        return mealService.findAll();
    }

    @PostMapping("/plats")
    public ResponseEntity<MealDto> create(@RequestBody AddMealDto addMealDto) {
        return ResponseEntity.ok(mealService.create(addMealDto));
    }
}
