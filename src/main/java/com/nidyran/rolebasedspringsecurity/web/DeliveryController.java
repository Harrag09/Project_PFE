package com.nidyran.rolebasedspringsecurity.web;

import com.nidyran.rolebasedspringsecurity.service.DeliveryService;
import com.nidyran.rolebasedspringsecurity.service.model.delivery.AddDeliveryDTO;
import com.nidyran.rolebasedspringsecurity.service.model.delivery.DeliveryDTO;
import com.nidyran.rolebasedspringsecurity.service.model.restaurant.RestaurantDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Delivery Resource")
@RequestMapping("/delivery-resources")
public class    DeliveryController {

    private final DeliveryService deliveryService;

    @PostMapping("/delivery/create/create")
    public ResponseEntity<AddDeliveryDTO> createDelivery(@RequestBody AddDeliveryDTO addDeliveryDTO) {
        AddDeliveryDTO savedDelivery = deliveryService.createDelivery(addDeliveryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDelivery);
    }

    @GetMapping("/deliveryGetByName/{id}")
    public ResponseEntity<DeliveryDTO> getDeliveryByName(@PathVariable Long id) {
        DeliveryDTO delivery = deliveryService.getDeliveryById(id);
        return ResponseEntity.ok(delivery);
    }

    @GetMapping("/delivery/All")
    public ResponseEntity<List<DeliveryDTO>> getAllDeliver() {
        List<DeliveryDTO> deliveries = deliveryService.getAllDeliver();
        return ResponseEntity.ok(deliveries);
    }


    @GetMapping("/delivery/search")
    public ResponseEntity<List<DeliveryDTO>> searchRestaurants(@RequestParam("keyword") String keyword) {
        return ResponseEntity.ok(deliveryService.searchDelivery(keyword));
    }
}
