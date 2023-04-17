package com.nidyran.rolebasedspringsecurity.web;

import com.nidyran.rolebasedspringsecurity.dao.entity.Commande;
import com.nidyran.rolebasedspringsecurity.dao.entity.CommandeItem;
import com.nidyran.rolebasedspringsecurity.service.CommandeService;
import com.nidyran.rolebasedspringsecurity.service.model.commande.AddCommandeDTO;
import com.nidyran.rolebasedspringsecurity.service.model.commande.AddCommandeItemDTO;
import com.nidyran.rolebasedspringsecurity.service.model.commande.CommandeDTO;
import com.nidyran.rolebasedspringsecurity.service.model.commande.CommandeItemDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@Tag(name = "Commande Resource")
@RequestMapping("/commande-resources")
@PreAuthorize("hasAuthority('CUSTOMER_AUTHORITY')")
public class CommandeController {

    private final CommandeService commandeService;


    @PostMapping("/create/{userId}/{panierId}")
    public ResponseEntity<CommandeDTO> createCommande(@PathVariable Long userId, @PathVariable Long panierId, @RequestBody AddCommandeDTO addCommandeDTO) {
        CommandeDTO commandeDTO = commandeService.createCommande(userId, panierId, addCommandeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(commandeDTO);
    }

    @PutMapping("/update/{commandeId}")
    public ResponseEntity<CommandeDTO> updateCommandeInfo(@PathVariable Long commandeId, @RequestBody CommandeDTO commandeDTO) {
        CommandeDTO updatedCommandeDTO = commandeService.updateCommandeInfo(commandeId, commandeDTO);
        return ResponseEntity.ok(updatedCommandeDTO);
    }

    @DeleteMapping("/delete/{commandeId}")
    public ResponseEntity<Void> deleteCommande(@PathVariable Long commandeId) {
        commandeService.deleteCommande(commandeId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/byId/{commandeId}")
    public ResponseEntity<CommandeDTO> getCommandeById(@PathVariable Long commandeId) {
        CommandeDTO commandeDTO = commandeService.getCommandeById(commandeId);
        return ResponseEntity.ok(commandeDTO);
    }
}