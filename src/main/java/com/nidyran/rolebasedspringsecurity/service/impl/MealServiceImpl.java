package com.nidyran.rolebasedspringsecurity.service.impl;

import com.nidyran.rolebasedspringsecurity.dao.entity.Category;
import com.nidyran.rolebasedspringsecurity.dao.entity.Meal;
import com.nidyran.rolebasedspringsecurity.dao.repository.MealRepository;
import com.nidyran.rolebasedspringsecurity.service.MealService;
import com.nidyran.rolebasedspringsecurity.service.model.AddMealDto;
import com.nidyran.rolebasedspringsecurity.service.model.MealDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class MealServiceImpl implements MealService {

    private final MealRepository mealRepository;
    private final ModelMapper modelMapper;



    @Override
    public MealDto create(AddMealDto addMealDto) {
        log.warn("Category added by {}", SecurityContextHolder.getContext().getAuthentication().getName());
        Meal meal = modelMapper.map(addMealDto, Meal.class);
        meal.setCategory(new Category(addMealDto.getCategoryId()));
        return modelMapper.map(mealRepository.save(meal), MealDto.class);
    }


    @Override
    public List<MealDto> findAll() {
        return mealRepository.findAll().stream().map(meal -> modelMapper.map(meal, MealDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<MealDto> findByIds(List<Long> ids) {
        List<Meal> meals = mealRepository.findAllById(ids);
        return meals.stream()
                .map(meal -> modelMapper.map(meal, MealDto.class))
                .collect(Collectors.toList());
    }


}
