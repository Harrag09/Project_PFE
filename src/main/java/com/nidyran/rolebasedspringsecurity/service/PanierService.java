package com.nidyran.rolebasedspringsecurity.service;


import com.nidyran.rolebasedspringsecurity.dao.entity.Panier;
import com.nidyran.rolebasedspringsecurity.dao.repository.PanierRepository;
import com.nidyran.rolebasedspringsecurity.service.model.PanierDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class PanierService {
    private final PanierRepository panierRepository;
    private final ModelMapper modelMapper;

    public PanierDTO getPanierById(Long id) {
        Optional<Panier> panier = panierRepository.findById(id);
        return panier.map(this::convertToDTO).orElse(null);
    }

    public void createPanier(PanierDTO panierDTO) {
        Panier panier = convertToEntity(panierDTO);
        panierRepository.save(panier);
    }

    public void updatePanier(PanierDTO panierDTO) {
        Panier panier = convertToEntity(panierDTO);
        panierRepository.save(panier);
    }

    public void deletePanier(Long id) {
        panierRepository.deleteById(id);
    }

    private PanierDTO convertToDTO(Panier panier) {
        return modelMapper.map(panier, PanierDTO.class);
    }

    private Panier convertToEntity(PanierDTO panierDTO) {
        return modelMapper.map(panierDTO, Panier.class);
    }


}
