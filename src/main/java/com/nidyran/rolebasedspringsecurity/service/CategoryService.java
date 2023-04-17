package com.nidyran.rolebasedspringsecurity.service;

import com.nidyran.rolebasedspringsecurity.Exeption.CategoryNotFoundException;
import com.nidyran.rolebasedspringsecurity.dao.entity.Category;
import com.nidyran.rolebasedspringsecurity.dao.repository.CategoryRepository;
import com.nidyran.rolebasedspringsecurity.service.model.category.AddCategoryDto;
import com.nidyran.rolebasedspringsecurity.service.model.category.CategoryDto;
import com.nidyran.rolebasedspringsecurity.utils.BackendUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;


    public CategoryDto create(AddCategoryDto addCategoryDto) {
        log.warn("Category added by {}", BackendUtils.getCurrentUsername());
        return modelMapper.map(categoryRepository.save(modelMapper.map(addCategoryDto, Category.class)), CategoryDto.class);
    }


    public CategoryDto updateCategoryInfo(long categoryId, CategoryDto updateCategoryDto) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            modelMapper.map(updateCategoryDto, category);
            categoryRepository.save(category);
            log.warn("Category ;",category.getName()," Update by {}", BackendUtils.getCurrentUsername());
            return modelMapper.map(category, CategoryDto.class);
        } else {
            throw new CategoryNotFoundException(categoryId);
        }
    }

    public void deleteCategory(long categoryId) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            log.warn("Category :  ",category.getName(), "delete by {}", BackendUtils.getCurrentUsername());
            categoryRepository.delete(category);
        } else {
            throw new CategoryNotFoundException(categoryId);
        }
    }

    public CategoryDto getCategoryById(long categoryId) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            return modelMapper.map(category, CategoryDto.class);
        } else {
            throw new CategoryNotFoundException(categoryId);
        }
    }

    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(category -> modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
    }
}
