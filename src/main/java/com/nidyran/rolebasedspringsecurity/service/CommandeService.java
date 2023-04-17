package com.nidyran.rolebasedspringsecurity.service;

import com.nidyran.rolebasedspringsecurity.Exeption.CommandeNotFoundException;
import com.nidyran.rolebasedspringsecurity.Exeption.PanierNotFoundException;
import com.nidyran.rolebasedspringsecurity.Exeption.UserNotFoundException;
import com.nidyran.rolebasedspringsecurity.dao.entity.*;
import com.nidyran.rolebasedspringsecurity.dao.repository.CommandeRepository;
import com.nidyran.rolebasedspringsecurity.dao.repository.PanierRepository;
import com.nidyran.rolebasedspringsecurity.dao.repository.UserRepository;
import com.nidyran.rolebasedspringsecurity.enmus.CommandeStatus;
import com.nidyran.rolebasedspringsecurity.service.model.commande.AddCommandeDTO;
import com.nidyran.rolebasedspringsecurity.service.model.commande.AddCommandeItemDTO;
import com.nidyran.rolebasedspringsecurity.service.model.commande.CommandeDTO;
import com.nidyran.rolebasedspringsecurity.service.model.commande.CommandeItemDTO;
import com.nidyran.rolebasedspringsecurity.service.model.meal.MealDto;
import com.nidyran.rolebasedspringsecurity.service.model.panier.PanierDTO;
import com.nidyran.rolebasedspringsecurity.service.model.panier.PanierItemDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
;

@Service
@Transactional
@RequiredArgsConstructor
public class CommandeService {
    private final CommandeRepository commandeRepository;
    private final PanierRepository panierRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public CommandeDTO createCommande(Long userId, Long panierId, AddCommandeDTO addCommandeDTO) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        Panier panier = panierRepository.findById(panierId).orElseThrow(() -> new PanierNotFoundException());

        Commande commande = modelMapper.map(addCommandeDTO, Commande.class);
        commande.setUser(user);
        commande.setPanier(panier);
        commande.setDateCommande(LocalDateTime.now());
        commande.setStatus(CommandeStatus.PENDING);

        double total = 0.0;
        List<CommandeItem> commandeItems = new ArrayList<>();
        for (PanierItem panierItem : panier.getPanierItems()) {
            CommandeItem commandeItem = new CommandeItem();
            commandeItem.setMeal(panierItem.getMeal());
            commandeItem.setQuantity(panierItem.getQty());
            commandeItem.setCommande(commande);
            commandeItems.add(commandeItem);
            total += panierItem.getMeal().getPrice() * panierItem.getQty();
        }
        commande.setCommandeItems(commandeItems);
        commande.setTotal(total);

        commandeRepository.save(commande);
        panierRepository.delete(panier);

        return modelMapper.map(commande, CommandeDTO.class);
    }

    public CommandeDTO updateCommandeInfo(Long commandeId, CommandeDTO commandeDTO) {
        Commande commande = commandeRepository.findById(commandeId).orElseThrow(() -> new CommandeNotFoundException(commandeId));

        commande.setPaymentMethod(commandeDTO.getPaymentMethod());
        commande.setStatus(commandeDTO.getStatus());

        commandeRepository.save(commande);

        return modelMapper.map(commande, CommandeDTO.class);
    }

    public void deleteCommande(Long commandeId) {
        Commande commande = commandeRepository.findById(commandeId).orElseThrow(() -> new CommandeNotFoundException(commandeId));

        commandeRepository.delete(commande);
    }

    public CommandeDTO getCommandeById(Long commandeId) {
        Commande commande = commandeRepository.findById(commandeId).orElseThrow(() -> new CommandeNotFoundException(commandeId));

        return modelMapper.map(commande, CommandeDTO.class);
    }
}