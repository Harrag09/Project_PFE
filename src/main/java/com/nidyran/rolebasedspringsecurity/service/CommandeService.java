package com.nidyran.rolebasedspringsecurity.service;




import com.nidyran.rolebasedspringsecurity.Exeption.PanierNotFoundException;
import com.nidyran.rolebasedspringsecurity.Exeption.RestaurantNotFoundException;
import com.nidyran.rolebasedspringsecurity.dao.entity.*;
import com.nidyran.rolebasedspringsecurity.dao.repository.*;
import com.nidyran.rolebasedspringsecurity.enmus.CommandeStatus;
import com.nidyran.rolebasedspringsecurity.service.model.commande.AddCommandeDTO;
import com.nidyran.rolebasedspringsecurity.service.model.commande.CommandeDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class CommandeService {
    private final CommandeItemRepository commandeItemRepository;
    private final CommandeRepository commandeRepository;
    private final PanierRepository panierRepository;
    private final ModelMapper modelMapper;
    private final RestaurantRepository restaurantRepository;
    private final UserDetailsServiceImpl userDetailsService;

    public AddCommandeDTO createCommande(Long panierId, AddCommandeDTO addCommandeDTO) {
        User user = userDetailsService.getUserById(addCommandeDTO.getUserId());
        Restaurant restaurant = restaurantRepository.findById(addCommandeDTO.getRestaurantId())
                .orElseThrow(() -> new RestaurantNotFoundException(addCommandeDTO.getRestaurantId()));

        Panier panier = panierRepository.findById(panierId)
                .orElseThrow(() -> new PanierNotFoundException());

        Commande commande = new Commande();
        commande.setUserId(user.getId());
        commande.setTotal(panier.getTotal());
        commande.setRestaurant(restaurant);
        commande.setAddress(addCommandeDTO.getAddress());
        commande.setNom(addCommandeDTO.getNom());
        commande.setTel(addCommandeDTO.getTel());
        commande.setDescription(addCommandeDTO.getDescription());
        commande.setPaymentMethod(addCommandeDTO.getPaymentMethod());
        commande.setCommandeStatus(String.valueOf(CommandeStatus.CANCELED));
        commande=commandeRepository.save(commande);

        List<CommandeItem> commandeItems = new ArrayList<>();
        for (PanierItem panierItem : panier.getPanierItems()) {
            CommandeItem commandeItem = commandeItemRepository.save(convert(panierItem));
            commandeItem.setCommande(commande);
            commandeItems.add(commandeItem);
        }
        commandeRepository.save(commande);

        panierRepository.delete(panier);
       return modelMapper.map(commande,AddCommandeDTO.class);
    }


    private CommandeItem convert (PanierItem panierItem)
    {  CommandeItem commandeItem = new CommandeItem();
        commandeItem.setQuantity(panierItem.getQty());
        commandeItem.setMeal(panierItem.getMeal());
        return commandeItem;
    }

    public List<Commande> getAllCommandes() {
        return commandeRepository.findAll();
    }

}
