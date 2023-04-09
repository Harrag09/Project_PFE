package com.nidyran.rolebasedspringsecurity.web;

import com.nidyran.rolebasedspringsecurity.service.CategoryService;
import com.nidyran.rolebasedspringsecurity.service.model.CategoryDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Public Resource")
@RequestMapping("/public-resources")
public class PublicController {
    private final CategoryService categoryService;

    @GetMapping("/categories")
    public List<CategoryDto> findAll() {
        return categoryService.findAll();
    }
}
