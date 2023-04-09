package com.nidyran.rolebasedspringsecurity.service.impl;

import com.nidyran.rolebasedspringsecurity.dao.entity.Category;
import com.nidyran.rolebasedspringsecurity.dao.repository.CategoryRepository;
import com.nidyran.rolebasedspringsecurity.service.CategoryService;
import com.nidyran.rolebasedspringsecurity.service.model.AddCategoryDto;
import com.nidyran.rolebasedspringsecurity.service.model.CategoryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public CategoryDto create(AddCategoryDto addCategoryDto) {
        Category category = new Category();
        category.setName(addCategoryDto.getName());
        category.setImage(addCategoryDto.getImage());
        category = categoryRepository.save(category);
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        categoryDto.setImage(category.getImage());
        log.warn("Category added by {}", SecurityContextHolder.getContext().getAuthentication().getName());
        return categoryDto;
    }

    @Override
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll().stream().map(category -> {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setId(category.getId());
            categoryDto.setName(category.getName());
            categoryDto.setImage(category.getImage());
            return categoryDto;
        }).collect(Collectors.toList());
    }
}