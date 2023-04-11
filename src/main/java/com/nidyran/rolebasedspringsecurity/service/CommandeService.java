package com.nidyran.rolebasedspringsecurity.service;

import com.nidyran.rolebasedspringsecurity.service.model.AddCommandeDto;
import com.nidyran.rolebasedspringsecurity.service.model.CommandeDto;


import java.util.List;

public interface CommandeService {

    CommandeDto create(AddCommandeDto addCommandeDto);

    List<CommandeDto> findAll();
}
