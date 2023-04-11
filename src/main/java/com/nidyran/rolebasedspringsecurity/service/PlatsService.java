package com.nidyran.rolebasedspringsecurity.service;

import com.nidyran.rolebasedspringsecurity.service.model.AddPlatsDto;
import com.nidyran.rolebasedspringsecurity.service.model.PlatsDto;

import java.util.List;

public interface PlatsService {

    PlatsDto create(AddPlatsDto addPlatsDto);

    List<PlatsDto> findAll();
}
