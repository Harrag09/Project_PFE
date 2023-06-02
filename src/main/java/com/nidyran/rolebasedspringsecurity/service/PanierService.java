package com.nidyran.rolebasedspringsecurity.service;


import com.nidyran.rolebasedspringsecurity.Exeption.MealNotFoundException;
import com.nidyran.rolebasedspringsecurity.Exeption.PanierNotFoundException;
import com.nidyran.rolebasedspringsecurity.dao.entity.*;

import com.nidyran.rolebasedspringsecurity.dao.repository.MealRepository;
import com.nidyran.rolebasedspringsecurity.dao.repository.PanierItemRepository;
import com.nidyran.rolebasedspringsecurity.dao.repository.PanierRepository;

import com.nidyran.rolebasedspringsecurity.dao.repository.UserRepository;
import com.nidyran.rolebasedspringsecurity.service.model.meal.MealDto;
import com.nidyran.rolebasedspringsecurity.service.model.panier.AddPanierDTO;
import com.nidyran.rolebasedspringsecurity.service.model.panier.PanierDTO;
import com.nidyran.rolebasedspringsecurity.service.model.panier.PanierItemDTO;
import com.nidyran.rolebasedspringsecurity.service.model.user.UserDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor
public class PanierService {
    private final MealRepository mealRepository;
    private final PanierRepository panierRepository;
    private final ModelMapper modelMapper;
    private final PanierItemRepository panierItemRepository;
    private final UserRepository userRepository;

    public PanierDTO createPanier(AddPanierDTO addPanierDTO) {
        Panier panier = modelMapper.map(addPanierDTO, Panier.class);
        Panier savedPanier = panierRepository.save(panier);
        return modelMapper.map(savedPanier, PanierDTO.class);
    }
    public void addItemToPanier(Long panierId, Long mealId, int quantity) {
        Panier panier = panierRepository.findById(panierId).orElseThrow(() -> new PanierNotFoundException());
        Meal meal = mealRepository.findById(mealId).orElseThrow(() -> new MealNotFoundException(mealId));

        // Check if the meal is already in the panier
        Optional<PanierItem> existingItem = panier.getPanierItems().stream()
                .filter(item -> item.getMeal().getId()==(mealId))
                .findFirst();

        if (existingItem.isPresent()) {
            // Update the quantity of the existing item
            PanierItem panierItem = existingItem.get();
            panierItem.setQty(panierItem.getQty() + quantity);
        } else {
            // Create a new panier item and add it to the panier
            PanierItem panierItem = new PanierItem();
            panierItem.setMeal(meal);
            panierItem.setQty(quantity);
            panierItem.setPanier(panier);
            panierItem.setNameMeal(meal.getName());
            panierItem.setImage(meal.getPhoto());
            panierItem.setPrice(meal.getPrice());
            panier.getPanierItems().add(panierItem);
        }

        // Recalculate the total of the panier
        double panierTotal = panier.getPanierItems().stream()
                .mapToDouble(item -> item.getMeal().getPrice() * item.getQty())
                .sum();
        panier.setTotal(panierTotal);
        panier.setTotalItem(panier.getTotalItem()+quantity);

        panierRepository.save(panier);
    }



    public void deleteItemFromPanier(Long panierId, Long mealId, int quantity) {
        Panier panier = panierRepository.findById(panierId).orElseThrow(() -> new PanierNotFoundException());
        Meal meal = mealRepository.findById(mealId).orElseThrow(() -> new MealNotFoundException(mealId));

        // Check if the meal is in the panier
        Optional<PanierItem> existingItem = panier.getPanierItems().stream()
                .filter(item -> item.getMeal().getId()==(mealId))
                .findFirst();

        if (existingItem.isPresent()) {
            // Update the quantity of the existing item or remove it if the quantity reaches zero
            PanierItem panierItem = existingItem.get();
            int newQuantity = panierItem.getQty() - quantity;
            if (newQuantity > 0) {
                panierItem.setQty(newQuantity);
            } else {
                panier.getPanierItems().remove(panierItem);
            }
        } 

        // Recalculate the total of the panier
        double panierTotal = panier.getPanierItems().stream()
                .mapToDouble(item -> item.getMeal().getPrice() * item.getQty())
                .sum();
        panier.setTotal(panierTotal);
        panier.setTotalItem(panier.getTotalItem()-quantity);

        panierRepository.save(panier);
    }


    public void clearPanier(Long panierId) {
        Panier panier = panierRepository.findById(panierId).orElseThrow(() -> new PanierNotFoundException());
        panier.setTotal(0);
        panier.getPanierItems().clear();

        panierRepository.save(panier);
    }

    public PanierDTO getPanierById(Long panierId) {
        Panier panier = panierRepository.findById(panierId).orElseThrow(() -> new PanierNotFoundException());

        return modelMapper.map(panier, PanierDTO.class);
    }



    public List<PanierItemDTO> getPanierItems(Long panierId) {
        Panier panier = panierRepository.findById(panierId).orElseThrow(() -> new PanierNotFoundException());

        return panier.getPanierItems().stream()
                .map(item -> modelMapper.map(item, PanierItemDTO.class))
                .collect(Collectors.toList());
    }
    public Long getIdPanierByIdUser(Long userId) {
        Panier panier = panierRepository.findByUserId(userId);
        if (panier == null) {
            throw new PanierNotFoundException();
        }
        return panier.getId();
    }
    public int getQtyByIdPanierFromPanierItem(Long panierId, Long mealId) {
        PanierItem panierItem = panierItemRepository.findByPanierIdAndMealId(panierId, mealId);
        if (panierItem != null) {
            return panierItem.getQty();
        } else {
            return 0;
        }
    }




}


