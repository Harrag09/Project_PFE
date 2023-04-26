package com.nidyran.rolebasedspringsecurity.web;

import com.nidyran.rolebasedspringsecurity.dao.entity.Commande;
import com.nidyran.rolebasedspringsecurity.service.CommandeService;
import com.nidyran.rolebasedspringsecurity.service.model.commande.AddCommandeDTO;
import com.nidyran.rolebasedspringsecurity.service.model.commande.CommandeDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/create/{panierId}")
    public AddCommandeDTO createCommande(@PathVariable Long panierId, @RequestBody AddCommandeDTO addCommandeDTO) {
        return commandeService.createCommande(panierId, addCommandeDTO);
    }

    @GetMapping("/")
    public List<Commande> getAllCommandes() {
        return commandeService.getAllCommandes();
    }

}