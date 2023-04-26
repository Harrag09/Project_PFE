package com.nidyran.rolebasedspringsecurity.service;

import com.nidyran.rolebasedspringsecurity.Exeption.MealNotFoundException;
import com.nidyran.rolebasedspringsecurity.dao.entity.CommandeItem;
import com.nidyran.rolebasedspringsecurity.dao.entity.Meal;
import com.nidyran.rolebasedspringsecurity.dao.entity.PanierItem;
import com.nidyran.rolebasedspringsecurity.dao.repository.MealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommandeItemService {
    private final MealRepository mealRepository;

    public CommandeItem createCommandeItemFromPanierItem(PanierItem panierItem) {
        Meal meal = mealRepository.findById(panierItem.getMeal().getId())
                .orElseThrow(() -> new MealNotFoundException(panierItem.getMeal().getId()));

        CommandeItem commandeItem = new CommandeItem();
        commandeItem.setMeal(meal);
        commandeItem.setQuantity(panierItem.getQty());
        return commandeItem;
    }

}
