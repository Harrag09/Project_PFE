package com.nidyran.rolebasedspringsecurity.service;


import com.nidyran.rolebasedspringsecurity.Exeption.CommandeNotFoundException;
import com.nidyran.rolebasedspringsecurity.dao.entity.Commande;
import com.nidyran.rolebasedspringsecurity.dao.repository.CommandeRepository;
import com.nidyran.rolebasedspringsecurity.enmus.CommandeStatus;
import com.nidyran.rolebasedspringsecurity.service.model.commande.AddCommandeDTO;
import com.nidyran.rolebasedspringsecurity.service.model.commande.CommandeDTO;
import com.nidyran.rolebasedspringsecurity.service.model.panier.PanierDTO;
import com.nidyran.rolebasedspringsecurity.service.model.panier.PanierItemDTO;
import com.nidyran.rolebasedspringsecurity.service.model.restaurant.RestaurantDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class CommandeService {
    private final CommandeRepository commandeRepository;
    private final ModelMapper modelMapper;

 /*   public Commande createCommande(AddCommandeDTO addCommandeDTO, PanierDTO panierDTO, List<PanierItemDTO> panierItems, RestaurantDto restaurantDto) {
        Commande commande = modelMapper.map(addCommandeDTO, Commande.class);
        commande.setStatus(CommandeStatus.PENDING);
        commande.setPanierId(panierDTO.getId());
        commande.setRestaurantId(restaurantDto.getId());
        // set other fields as needed

        // save the command and associated information to the database
        Commande savedCommande = commandeRepository.save(commande);
        savePanierItems(savedCommande.getId(), panierItems);

        return savedCommande;
    }*/

    public Commande updateCommande(Long id, CommandeDTO commandeDTO) {
        Commande commande = getCommandeById(id);
        modelMapper.map(commandeDTO, commande);
        return commandeRepository.save(commande);
    }



    public Commande getCommandeById(Long id) {
        return commandeRepository.findById(id)
                .orElseThrow(() -> new CommandeNotFoundException(id));
    }

    public List<Commande> getAllCommandes() {
        return commandeRepository.findAll();
    }

    public void deleteCommande(Long id) {
        commandeRepository.deleteById(id);
    }
}