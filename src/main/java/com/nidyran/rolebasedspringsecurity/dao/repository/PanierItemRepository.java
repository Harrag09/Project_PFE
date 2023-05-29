package com.nidyran.rolebasedspringsecurity.dao.repository;

import com.nidyran.rolebasedspringsecurity.dao.entity.PanierItem;
import com.nidyran.rolebasedspringsecurity.service.model.panier.PanierItemDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PanierItemRepository extends JpaRepository<PanierItem, Long> {


    PanierItem findByPanierIdAndMealId(Long panierId, Long mealId);

}
