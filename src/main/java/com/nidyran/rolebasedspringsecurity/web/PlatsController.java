package com.nidyran.rolebasedspringsecurity.web;


import com.nidyran.rolebasedspringsecurity.service.PlatsService;
import com.nidyran.rolebasedspringsecurity.service.model.AddPlatsDto;
import com.nidyran.rolebasedspringsecurity.service.model.PlatsDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Plats Resource")
@RequestMapping("/Plats-configuration")
@PreAuthorize("hasAuthority('RESTAURANT_AUTHORITY')")
public class PlatsController {
    private final PlatsService platsService;

    @PostMapping("/plats")
    public ResponseEntity<PlatsDto> create(@RequestBody AddPlatsDto addPlatsDto) {
        return ResponseEntity.ok(platsService.create(addPlatsDto));
    }
}
