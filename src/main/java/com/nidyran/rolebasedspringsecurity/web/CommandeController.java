package com.nidyran.rolebasedspringsecurity.web;

import com.nidyran.rolebasedspringsecurity.service.CommandeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Commande Resource")
@RequestMapping("/commande-resources")
@PreAuthorize("hasAuthority('CUSTOMER_AUTHORITY')")
public class CommandeController {

    private final CommandeService commandeService;
    private final ModelMapper modelMapper;



}