package com.nidyran.rolebasedspringsecurity.service;

import com.nidyran.rolebasedspringsecurity.service.model.AddMealDto;
import com.nidyran.rolebasedspringsecurity.service.model.MealDto;

import java.util.List;

public interface MealService {

    MealDto create(AddMealDto addMealDto);

    List<MealDto> findAll();
}
