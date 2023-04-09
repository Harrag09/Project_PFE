package com.nidyran.rolebasedspringsecurity.service;

import com.nidyran.rolebasedspringsecurity.service.model.AddCategoryDto;
import com.nidyran.rolebasedspringsecurity.service.model.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto create(AddCategoryDto addCategoryDto);

    List<CategoryDto> findAll();
}