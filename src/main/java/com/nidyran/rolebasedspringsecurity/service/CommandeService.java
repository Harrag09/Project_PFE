//package com.nidyran.rolebasedspringsecurity.service;
//
//import com.nidyran.rolebasedspringsecurity.Exeption.CommandeNotFoundException;
//import com.nidyran.rolebasedspringsecurity.Exeption.PanierNotFoundException;
//import com.nidyran.rolebasedspringsecurity.Exeption.UserNotFoundException;
//import com.nidyran.rolebasedspringsecurity.dao.entity.*;
//import com.nidyran.rolebasedspringsecurity.dao.repository.CommandeItemRepository;
//import com.nidyran.rolebasedspringsecurity.dao.repository.CommandeRepository;
//import com.nidyran.rolebasedspringsecurity.dao.repository.PanierRepository;
//import com.nidyran.rolebasedspringsecurity.dao.repository.UserRepository;
//import com.nidyran.rolebasedspringsecurity.enmus.CommandeStatus;
//import com.nidyran.rolebasedspringsecurity.service.model.commande.AddCommandeDTO;
//import com.nidyran.rolebasedspringsecurity.service.model.commande.AddCommandeItemDTO;
//import com.nidyran.rolebasedspringsecurity.service.model.commande.CommandeDTO;
//import com.nidyran.rolebasedspringsecurity.service.model.commande.CommandeItemDTO;
//import com.nidyran.rolebasedspringsecurity.service.model.meal.MealDto;
//import com.nidyran.rolebasedspringsecurity.service.model.panier.PanierDTO;
//import com.nidyran.rolebasedspringsecurity.service.model.panier.PanierItemDTO;
//import lombok.RequiredArgsConstructor;
//import org.modelmapper.ModelMapper;
//import org.springframework.stereotype.Service;
//
//import javax.transaction.Transactional;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//;
//
//@Service
//@Transactional
//@RequiredArgsConstructor
//public class CommandeService {
//    private final CommandeRepository commandeRepository;
//    private final CommandeItemRepository commandeItemRepository;
//    private final PanierService panierService;
//    private final ModelMapper modelMapper;
///*
//
//    public Commande createCommande(AddCommandeDTO addCommandeDTO) {
//        // get the panier from the panier ID
//        return null;
//    }
//
//
//    public Commande getCommandeById(Long id) {
//        return commandeRepository.findById(id).orElse(null);
//    }
//
//    public List<Commande> getAllCommande() {
//        return commandeRepository.findAll();
//    }
//
//    public Commande addCommandeItem(Long commandeId, AddCommandeItemDTO addCommandeItemDTO) {
//        Commande commande = getCommandeById(commandeId);
//        if (commande == null) {
//            throw new CommandeNotFoundException(commandeId);
//        }
//        PanierItem panierItem = panierService.getPanierItemById(addCommandeItemDTO.getPanierItemId());
//        if (panierItem == null) {
//            throw new PanierNotFoundException();
//        }
//        CommandeItem commandeItem = modelMapper.map(addCommandeItemDTO, CommandeItem.class);
//        commandeItem.setPanierItem(panierItem);
//        commandeItem.setMeal(panierItem.getMeal());
//        commandeItem = commandeItemRepository.save(commandeItem);
//        commande.getCommandeItems().add(commandeItem);
//        return commandeRepository.save(commande);
//    }
//*/
//}