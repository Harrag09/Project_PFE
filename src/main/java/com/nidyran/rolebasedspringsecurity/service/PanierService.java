package com.nidyran.rolebasedspringsecurity.service;


import com.nidyran.rolebasedspringsecurity.Exeption.MealNotFoundException;
import com.nidyran.rolebasedspringsecurity.Exeption.PanierNotFoundException;
import com.nidyran.rolebasedspringsecurity.dao.entity.Meal;
import com.nidyran.rolebasedspringsecurity.dao.entity.Panier;
import com.nidyran.rolebasedspringsecurity.dao.entity.PanierItem;
import com.nidyran.rolebasedspringsecurity.dao.entity.User;
import com.nidyran.rolebasedspringsecurity.dao.repository.MealRepository;
import com.nidyran.rolebasedspringsecurity.dao.repository.PanierRepository;
import com.nidyran.rolebasedspringsecurity.service.model.panier.AddPanierDTO;
import com.nidyran.rolebasedspringsecurity.service.model.panier.PanierDTO;
import com.nidyran.rolebasedspringsecurity.service.model.panier.PanierItemDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor
public class PanierService {
    private final MealRepository mealRepository;
    private final PanierRepository panierRepository;
    private final ModelMapper modelMapper;



    public PanierDTO createPanier(AddPanierDTO addPanierDTO) {
        User user = new User();
        user.setId(addPanierDTO.getUserId());
        Panier panier = new Panier();
        panier.setUser(user);
        panier = panierRepository.save(panier);
        return modelMapper.map(panier, PanierDTO.class);
    }


    public void addItemToPanier(Long panierId, Long mealId, Integer quantity) {
        Panier panier = panierRepository.findById(panierId).orElseThrow(() -> new PanierNotFoundException());
        Meal meal = mealRepository.findById(mealId).orElseThrow(() -> new MealNotFoundException(mealId));
        PanierItem panierItem = new PanierItem();
        panierItem.setMeal(meal);
        panierItem.setQty(quantity);
        panierItem.setPanier(panier);

        panier.getPanierItems().add(panierItem);

        panierRepository.save(panier);
    }

    public void removeItemFromPanier(Long panierId, Long panierItemId) {
        Panier panier = panierRepository.findById(panierId).orElseThrow(() -> new PanierNotFoundException());
        PanierItem panierItem = panier.getPanierItems().stream()
                .filter(item -> item.getId().equals(panierItemId))
                .findFirst()
                .orElseThrow(() -> new PanierNotFoundException());

        panier.getPanierItems().remove(panierItem);

        panierRepository.save(panier);
    }

    public void clearPanier(Long panierId) {
        Panier panier = panierRepository.findById(panierId).orElseThrow(() -> new PanierNotFoundException());

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
}


