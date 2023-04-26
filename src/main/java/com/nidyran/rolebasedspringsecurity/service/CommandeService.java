package com.nidyran.rolebasedspringsecurity.service;




import com.nidyran.rolebasedspringsecurity.Exeption.PanierNotFoundException;
import com.nidyran.rolebasedspringsecurity.Exeption.RestaurantNotFoundException;
import com.nidyran.rolebasedspringsecurity.dao.entity.*;
import com.nidyran.rolebasedspringsecurity.dao.repository.CommandeRepository;
import com.nidyran.rolebasedspringsecurity.dao.repository.PanierItemRepository;
import com.nidyran.rolebasedspringsecurity.dao.repository.PanierRepository;
import com.nidyran.rolebasedspringsecurity.dao.repository.RestaurantRepository;
import com.nidyran.rolebasedspringsecurity.enmus.CommandeStatus;
import com.nidyran.rolebasedspringsecurity.service.model.commande.AddCommandeDTO;
import com.nidyran.rolebasedspringsecurity.service.model.commande.CommandeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class CommandeService {

    private final CommandeRepository commandeRepository;
    private final PanierRepository panierRepository;
    private final PanierItemRepository panierItemRepository;
    private final RestaurantRepository restaurantRepository;
private final UserDetailsServiceImpl userDetailsService;
    private final  CommandeItemService commandeItemService;

    public AddCommandeDTO createCommande(Long panierId, AddCommandeDTO addCommandeDTO) {
        User user = userDetailsService.getUserById(addCommandeDTO.getUserId());
        Restaurant restaurant = restaurantRepository.findById(addCommandeDTO.getRestaurantId())
                .orElseThrow(() -> new RestaurantNotFoundException(addCommandeDTO.getRestaurantId()));

        Panier panier = panierRepository.findById(panierId)
                .orElseThrow(() -> new PanierNotFoundException());

        CommandeDTO commande = new CommandeDTO();
        commande.setUserId(user.getId());
        commande.setTotal(panier.getTotal());
        commande.setRestaurantId(restaurant.getId());
        commande.setAddress(addCommandeDTO.getAddress());
        commande.setNom(addCommandeDTO.getNom());
        commande.setTel(addCommandeDTO.getTel());
        commande.setDescription(addCommandeDTO.getDescription());
        commande.setPaymentMethod(addCommandeDTO.getPaymentMethod());
        commande.setCommandeStatus(String.valueOf(CommandeStatus.CANCELED));

        List<CommandeItem> commandeItems = new ArrayList<>();
        for (PanierItem panierItem : panier.getPanierItems()) {
            CommandeItem commandeItem = commandeItemService.createCommandeItemFromPanierItem(panierItem);
            commandeItems.add(commandeItem);
        }


        panierRepository.delete(panier);

    }



    public List<Commande> getAllCommandes() {
        return commandeRepository.findAll();
    }
}