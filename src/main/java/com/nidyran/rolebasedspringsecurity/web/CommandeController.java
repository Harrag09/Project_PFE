//package com.nidyran.rolebasedspringsecurity.web;
//

//import com.nidyran.rolebasedspringsecurity.dao.entity.Commande;
//import com.nidyran.rolebasedspringsecurity.dao.entity.CommandeItem;
//import com.nidyran.rolebasedspringsecurity.service.CommandeService;
//import com.nidyran.rolebasedspringsecurity.service.model.commande.AddCommandeDTO;
//import com.nidyran.rolebasedspringsecurity.service.model.commande.AddCommandeItemDTO;
//import com.nidyran.rolebasedspringsecurity.service.model.commande.CommandeDTO;
//import com.nidyran.rolebasedspringsecurity.service.model.commande.CommandeItemDTO;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import lombok.RequiredArgsConstructor;
//import org.modelmapper.ModelMapper;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//
//@RestController
//@RequiredArgsConstructor
//@Tag(name = "Commande Resource")
//@RequestMapping("/commande-resources")
////@PreAuthorize("hasAuthority('CUSTOMER_AUTHORITY')")
//public class CommandeController {
//
//    private final CommandeService commandeService;
//    private final ModelMapper modelMapper;
//
//
//    @PostMapping("")
//    public ResponseEntity<CommandeDTO> createCommande(@RequestBody AddCommandeDTO addCommandeDTO) {
//        Commande commande = commandeService.createCommande(addCommandeDTO);
//        CommandeDTO commandeDTO = modelMapper.map(commande, CommandeDTO.class);
//        return ResponseEntity.ok(commandeDTO);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<CommandeDTO> getCommandeById(@PathVariable("id") Long id) {
//        Commande commande = commandeService.getCommandeById(id);
//        if (commande == null) {
//            return ResponseEntity.notFound().build();
//        }
//        CommandeDTO commandeDTO = modelMapper.map(commande, CommandeDTO.class);
//        return ResponseEntity.ok(commandeDTO);
//    }
//
//    @GetMapping("")
//    public ResponseEntity<List<CommandeDTO>> getAllCommandes() {
//        List<Commande> commandes = commandeService.getAllCommandes();
//        List<CommandeDTO> commandeDTOs = commandes.stream()
//                .map(commande -> modelMapper.map(commande, CommandeDTO.class))
//                .collect(Collectors.toList());
//        return ResponseEntity.ok(commandeDTOs);
//    }
//
//
//    @PostMapping("/{id}/items")
//    public ResponseEntity<CommandeDTO> addCommandeItem(@PathVariable("id") Long commandeId, @RequestBody AddCommandeItemDTO addCommandeItemDTO) {
//        Commande commande = commandeService.addCommandeItem(commandeId, addCommandeItemDTO);
//        CommandeDTO commandeDTO = modelMapper.map(commande, CommandeDTO.class);
//        return ResponseEntity.ok(commandeDTO);
//    }
//}