package com.nidyran.rolebasedspringsecurity.service;

import com.nidyran.rolebasedspringsecurity.Exeption.CategoryNotFoundException;
import com.nidyran.rolebasedspringsecurity.Exeption.MealNotFoundException;
import com.nidyran.rolebasedspringsecurity.dao.entity.Category;
import com.nidyran.rolebasedspringsecurity.dao.entity.Meal;
import com.nidyran.rolebasedspringsecurity.dao.repository.CategoryRepository;
import com.nidyran.rolebasedspringsecurity.dao.repository.MealRepository;
import com.nidyran.rolebasedspringsecurity.service.model.category.CategoryDto;
import com.nidyran.rolebasedspringsecurity.service.model.meal.AddMealDto;
import com.nidyran.rolebasedspringsecurity.service.model.meal.MealDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
@Service
public class MealService {

    private final MealRepository mealRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;



    public MealDto create(AddMealDto addMealDto) {
        log.warn("Meal added by {}", SecurityContextHolder.getContext().getAuthentication().getName());
        Meal meal = modelMapper.map(addMealDto, Meal.class);
        Optional<Category> optionalCategory = categoryRepository.findById(addMealDto.getCategoryId());
        if (!optionalCategory.isPresent()) {
            throw new CategoryNotFoundException(addMealDto.getCategoryId());
        }

        meal.setCategory(optionalCategory.get());
        return modelMapper.map(mealRepository.save(meal), MealDto.class);
    }

    public MealDto updateMealInfo(long mealId, MealDto newInfo) {
        log.warn("Meal updated by {}", SecurityContextHolder.getContext().getAuthentication().getName());
        Optional<Meal> optionalMeal = mealRepository.findById(mealId);
        if (!optionalMeal.isPresent()) {
            throw new MealNotFoundException(mealId);
        }
        Meal meal = optionalMeal.get();
        modelMapper.typeMap(MealDto.class, Meal.class)
                .addMappings(mapper -> mapper.skip(Meal::setCategory))
                .map(newInfo, meal);
        return modelMapper.map(mealRepository.save(meal), MealDto.class);
    }

    public void deleteMeal(long mealId) {
        log.warn("Meal deleted by {}", SecurityContextHolder.getContext().getAuthentication().getName());
        Optional<Meal> optionalMeal = mealRepository.findById(mealId);
        if (!optionalMeal.isPresent()) {
            throw new MealNotFoundException(mealId);
        }
        mealRepository.deleteById(mealId);
    }

    public MealDto getMealById(long mealId) {
        Optional<Meal> optionalMeal = mealRepository.findById(mealId);
        if (!optionalMeal.isPresent()) {
            throw new MealNotFoundException(mealId);
        }
        return modelMapper.map(optionalMeal.get(), MealDto.class);
    }

    public List<MealDto> searchMeals(String keyword) {
        List<Meal> meals = mealRepository.findByNameContainingOrDescContaining(keyword, keyword);
        return meals.stream().map(meal -> modelMapper.map(meal, MealDto.class)).collect(Collectors.toList());
    }

    public List<MealDto> getMealsByCategory(long categoryId) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if (!optionalCategory.isPresent()) {
            throw new CategoryNotFoundException(categoryId);
        }
        List<Meal> meals = mealRepository.findByCategory(optionalCategory.get());
        return meals.stream().map(meal -> modelMapper.map(meal, MealDto.class)).collect(Collectors.toList());
    }
    public List<MealDto> getAllMeal() {
        List<Meal> meals = mealRepository.findAll();
        return meals.stream().map(meal -> modelMapper.map(meal, MealDto.class)).collect(Collectors.toList());
    }

}