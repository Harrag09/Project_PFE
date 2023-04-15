package com.nidyran.rolebasedspringsecurity.web;

import com.nidyran.rolebasedspringsecurity.service.PanierService;
import com.nidyran.rolebasedspringsecurity.service.model.PanierDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Panier Resource")
@RequestMapping("/panier-resources")
@PreAuthorize("hasAuthority('CUSTOMER_AUTHORITY')")
public class PanierController {

    private final PanierService panierService;

    @GetMapping("/{id}")
    public PanierDTO getPanierById1(@PathVariable("id") Long id) {
        return panierService.getPanierById(id);
    }

    @PostMapping
    public void createPanier1(@RequestBody PanierDTO panierDTO) {
        panierService.createPanier(panierDTO);
    }

    @PutMapping("/{id}")
    public void updatePanier1(@PathVariable("id") Long id, @RequestBody PanierDTO panierDTO) {
        panierDTO.setId(id);
        panierService.updatePanier(panierDTO);
    }


    @DeleteMapping("/{id}")
    public void deletePanier1(@PathVariable("id") Long id) {
        panierService.deletePanier(id);
    }




}
