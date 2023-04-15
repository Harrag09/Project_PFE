package com.nidyran.rolebasedspringsecurity.web;

import com.nidyran.rolebasedspringsecurity.service.CategoryService;
import com.nidyran.rolebasedspringsecurity.service.model.AddCategoryDto;
import com.nidyran.rolebasedspringsecurity.service.model.CategoryDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Restaurant Resource")
@RequestMapping("/category-configuration")
@PreAuthorize("@securityService.hasAnyRole('RESTAURANT_AUTHORITY')")
public class CategoryController {
    private final CategoryService categoryService;



   @PostMapping("/categories")
    public ResponseEntity<CategoryDto> create(@RequestBody AddCategoryDto addCategoryDto) {
       return ResponseEntity.ok(categoryService.create(addCategoryDto));

   }


    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        categoryService.delete(id);
        return ResponseEntity.ok().build();
    }
}
