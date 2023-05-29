package com.nidyran.rolebasedspringsecurity.web;

import com.nidyran.rolebasedspringsecurity.Exeption.MealNotFoundException;
import com.nidyran.rolebasedspringsecurity.Exeption.PanierNotFoundException;
import com.nidyran.rolebasedspringsecurity.service.PanierService;
import com.nidyran.rolebasedspringsecurity.service.model.panier.AddPanierDTO;
import com.nidyran.rolebasedspringsecurity.service.model.panier.PanierDTO;
import com.nidyran.rolebasedspringsecurity.service.model.panier.PanierItemDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Panier Resource")
@RequestMapping("/panier-resources")
public class PanierController {

    private final PanierService panierService;

    @PostMapping("AddItem/{panierId}/items")
    public ResponseEntity<?> addItemToPanier(@PathVariable Long panierId,
                                             @RequestParam Long mealId,
                                             @RequestParam int quantity) {
        panierService.addItemToPanier(panierId, mealId, quantity);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/createPanier")
    public ResponseEntity<PanierDTO> createPanier(@RequestBody AddPanierDTO addPanierDTO) {
        PanierDTO panierDTO = panierService.createPanier(addPanierDTO);
        return ResponseEntity.ok(panierDTO);
    }

    @DeleteMapping("/RemoveItem/{panierId}/items/{mealId}/{quantity}")
    public ResponseEntity<?> removeItemFromPanier(@PathVariable Long panierId, @PathVariable Long mealId, @PathVariable int quantity) {
        try {
            panierService.deleteItemFromPanier(panierId, mealId, quantity);
            return ResponseEntity.ok().build();
        } catch (PanierNotFoundException | MealNotFoundException e) {
            // Handle PanierNotFoundException, MealNotFoundException, or ItemNotFoundException and return an appropriate response
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("Clear/{panierId}")
    public ResponseEntity<?> clearPanier(@PathVariable Long panierId) {
        panierService.clearPanier(panierId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("GetByiD/{panierId}")
    public ResponseEntity<PanierDTO> getPanierById(@PathVariable Long panierId) {
        PanierDTO panierDTO = panierService.getPanierById(panierId);
        return ResponseEntity.ok(panierDTO);
    }

    @GetMapping("/{panierId}/items")
    public ResponseEntity<List<PanierItemDTO>> getPanierItems(@PathVariable Long panierId) {
        List<PanierItemDTO> panierItems = panierService.getPanierItems(panierId);
        return ResponseEntity.ok(panierItems);
    }
    @GetMapping("/panierByIdUser/{userId}")
    public ResponseEntity<Long> getIdPanierByUserId(@PathVariable Long userId) {
        Long panierId = panierService.getIdPanierByIdUser(userId);
        return ResponseEntity.ok(panierId);
    }

    @GetMapping("/{panierId}/meal/{mealId}/quantity")
    public int getQtyByIdPanierFromPanierItem(@PathVariable Long panierId, @PathVariable Long mealId) {
        return panierService.getQtyByIdPanierFromPanierItem(panierId, mealId);
    }

}
