package com.nidyran.rolebasedspringsecurity.service.impl;

import com.nidyran.rolebasedspringsecurity.dao.entity.Category;
import com.nidyran.rolebasedspringsecurity.dao.entity.Plats;
import com.nidyran.rolebasedspringsecurity.dao.repository.PlatsRepository;
import com.nidyran.rolebasedspringsecurity.service.PlatsService;
import com.nidyran.rolebasedspringsecurity.service.model.AddCategoryDto;
import com.nidyran.rolebasedspringsecurity.service.model.AddPlatsDto;
import com.nidyran.rolebasedspringsecurity.service.model.CategoryDto;
import com.nidyran.rolebasedspringsecurity.service.model.PlatsDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class PlatsServiceImpl implements PlatsService {
    private final PlatsRepository platsRepository;

@Override
public PlatsDto create(AddPlatsDto addPlatsDto) {
    Plats plats = new Plats();
    plats.setName(addPlatsDto.getName());
    plats.setPrice(addPlatsDto.getPrice());
    plats.setDesc(addPlatsDto.getDesc());
    plats = platsRepository.save(plats);
    PlatsDto platsDto = new PlatsDto();
    platsDto.setId(plats.getId());
    platsDto.setName(plats.getName());
    platsDto.setPrice(plats.getPrice());
    platsDto.setDesc(plats.getDesc());
    log.warn("Category added by {}", SecurityContextHolder.getContext().getAuthentication().getName());
    return platsDto;
}

@Override
    public List<PlatsDto> findAll() {
        return platsRepository.findAll().stream().map(plats -> {
            PlatsDto platsDto = new PlatsDto();
            platsDto.setId(plats.getId());
            platsDto.setName(plats.getName());
            platsDto.setPrice(plats.getPrice());
            platsDto.setDesc(plats.getDesc());
            return platsDto;
        }).collect(Collectors.toList());
    }
}
