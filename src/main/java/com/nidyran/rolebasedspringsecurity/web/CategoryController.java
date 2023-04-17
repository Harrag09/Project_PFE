package com.nidyran.rolebasedspringsecurity.web;


import com.nidyran.rolebasedspringsecurity.service.CategoryService;
import com.nidyran.rolebasedspringsecurity.service.model.category.AddCategoryDto;
import com.nidyran.rolebasedspringsecurity.service.model.category.CategoryDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Category Resource")
@RequestMapping("/category-configuration")
@PreAuthorize("@securityService.hasAnyRole('RESTAURANT_AUTHORITY')")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/category/create")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody AddCategoryDto addCategoryDto) {
        return ResponseEntity.ok(categoryService.create(addCategoryDto));


    }

    @PutMapping("/category/update/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategoryInfo(@PathVariable long categoryId, @Valid @RequestBody CategoryDto updateCategoryDto) {
        CategoryDto categoryDto = categoryService.updateCategoryInfo(categoryId, updateCategoryDto);
        return ResponseEntity.ok(categoryDto);
    }


    @DeleteMapping("/category/delete/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable long categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/category/getById/{categoryId}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable long categoryId) {
        CategoryDto categoryDto = categoryService.getCategoryById(categoryId);
        return ResponseEntity.ok(categoryDto);
    }

    @GetMapping("/category/all")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        List<CategoryDto> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }



}