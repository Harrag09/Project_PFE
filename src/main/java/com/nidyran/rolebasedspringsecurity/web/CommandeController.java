package com.nidyran.rolebasedspringsecurity.web;

import com.nidyran.rolebasedspringsecurity.service.CommandeService;
import com.nidyran.rolebasedspringsecurity.service.model.CommandeDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@Tag(name = "Commande Resource")
@RequestMapping("/commande-resources")
@PreAuthorize("hasAuthority('CUSTOMER_AUTHORITY')")
public class CommandeController {

    private final CommandeService commandeService;

    @GetMapping("/{id}")
    public CommandeDTO getCommandeById1(@PathVariable("id") Long id) {
        return commandeService.getCommandeById(id);
    }

    @PostMapping
    public void createCommande1(@RequestBody CommandeDTO commandeDTO) {
        commandeService.createCommande(commandeDTO);
    }


    @PutMapping("/{id}")
    public void updateCommande1(@PathVariable("id") Long id, @RequestBody CommandeDTO commandeDTO) {
        commandeDTO.setId(id);
        commandeService.updateCommande(commandeDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCommande1(@PathVariable("id") Long id) {
        commandeService.deleteCommande(id);
    }

}
