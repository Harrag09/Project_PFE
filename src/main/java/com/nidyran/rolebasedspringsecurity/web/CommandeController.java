package com.nidyran.rolebasedspringsecurity.web;

import com.nidyran.rolebasedspringsecurity.dao.entity.Commande;
import com.nidyran.rolebasedspringsecurity.service.CommandeService;
import com.nidyran.rolebasedspringsecurity.service.model.commande.AddCommandeDTO;
import com.nidyran.rolebasedspringsecurity.service.model.commande.CommandeDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Tag(name = "Commande Resource")
@RequestMapping("/commande-resources")
//@PreAuthorize("hasAuthority('CUSTOMER_AUTHORITY')")
public class CommandeController {

    private final CommandeService commandeService;
    private final ModelMapper modelMapper;


    @GetMapping("/getAllCommande/{id}")
    public List<CommandeDTO> getAllCommandes() {
        List<Commande> commandes = commandeService.getAllCommandes();
        return commandes.stream()
                .map(commande -> modelMapper.map(commande, CommandeDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/getCommandeById/{id}")
    public CommandeDTO getCommandeById(@PathVariable Long id) {
        Commande commande = commandeService.getCommandeById(id);
        return modelMapper.map(commande, CommandeDTO.class);
    }

/*    @PostMapping("/createCommande")
    public CommandeDTO createCommande(@RequestBody AddCommandeDTO addCommandeDTO) {
        Commande commande = commandeService.createCommande(addCommandeDTO);
        return modelMapper.map(commande, CommandeDTO.class);
    }*/

    @PutMapping("/update/{id}")
    public CommandeDTO updateCommande(@PathVariable Long id, @RequestBody CommandeDTO commandeDTO) {
        Commande commande = commandeService.updateCommande(id, commandeDTO);
        return modelMapper.map(commande, CommandeDTO.class);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCommande(@PathVariable Long id) {
        commandeService.deleteCommande(id);
    }


}