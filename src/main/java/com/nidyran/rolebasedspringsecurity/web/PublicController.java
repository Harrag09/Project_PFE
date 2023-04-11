package com.nidyran.rolebasedspringsecurity.web;

import com.nidyran.rolebasedspringsecurity.service.CategoryService;
import com.nidyran.rolebasedspringsecurity.service.PlatsService;
import com.nidyran.rolebasedspringsecurity.service.model.AddCategoryDto;
import com.nidyran.rolebasedspringsecurity.service.model.AddPlatsDto;
import com.nidyran.rolebasedspringsecurity.service.model.CategoryDto;
import com.nidyran.rolebasedspringsecurity.service.model.PlatsDto;
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
    private final PlatsService platsService;

    @GetMapping("/categories")
    public List<CategoryDto> findAll() {
        return categoryService.findAll();
    }

    @PostMapping("/categories")
    public ResponseEntity<CategoryDto> create(@RequestBody AddCategoryDto addCategoryDto) {
        return ResponseEntity.ok(categoryService.create(addCategoryDto));
    }

    @GetMapping("/plats")
    public List<PlatsDto> findAllPlats() {
        return platsService.findAll();
    }

    @PostMapping("/plats")
    public ResponseEntity<PlatsDto> create(@RequestBody AddPlatsDto addPlatsDto) {
        return ResponseEntity.ok(platsService.create(addPlatsDto));
    }
}
