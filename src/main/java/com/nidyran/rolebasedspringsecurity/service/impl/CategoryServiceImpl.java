package com.nidyran.rolebasedspringsecurity.service.impl;

import com.nidyran.rolebasedspringsecurity.dao.entity.Category;
import com.nidyran.rolebasedspringsecurity.dao.repository.CategoryRepository;
import com.nidyran.rolebasedspringsecurity.dao.repository.RestaurantRepository;
import com.nidyran.rolebasedspringsecurity.service.CategoryService;
import com.nidyran.rolebasedspringsecurity.service.model.AddCategoryDto;
import com.nidyran.rolebasedspringsecurity.service.model.CategoryDto;
import com.nidyran.rolebasedspringsecurity.utils.BackendUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;


    @Override
    public CategoryDto create(AddCategoryDto addCategoryDto) {
        log.warn("Category added by {}", BackendUtils.getCurrentUsername());
        return modelMapper.map(categoryRepository.save(modelMapper.map(addCategoryDto, Category.class)), CategoryDto.class);
    }


    @Override
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll().stream().map(category -> modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
    }

    @Override
    public void delete(long id) {
        log.warn("Category deleted by {}", BackendUtils.getCurrentUsername());
        categoryRepository.deleteById(id);
    }
}