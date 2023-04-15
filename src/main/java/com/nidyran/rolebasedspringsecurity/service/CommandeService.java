package com.nidyran.rolebasedspringsecurity.service;

import com.nidyran.rolebasedspringsecurity.dao.entity.Commande;
import com.nidyran.rolebasedspringsecurity.dao.repository.CommandeRepository;
import com.nidyran.rolebasedspringsecurity.service.model.CommandeDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommandeService {

    private final CommandeRepository commandeRepository;
    private final ModelMapper modelMapper;

    public CommandeDTO getCommandeById(Long id) {
        Optional<Commande> commande = commandeRepository.findById(id);
        return commande.map(this::convertToDTO).orElse(null);
    }

    public void createCommande(CommandeDTO commandeDTO) {
        Commande commande = convertToEntity(commandeDTO);
        commandeRepository.save(commande);
    }

    public void updateCommande(CommandeDTO commandeDTO) {
        Commande commande = convertToEntity(commandeDTO);
        commandeRepository.save(commande);
    }

    public void deleteCommande(Long id) {
        commandeRepository.deleteById(id);
    }

    private CommandeDTO convertToDTO(Commande commande) {
        return modelMapper.map(commande, CommandeDTO.class);
    }

    private Commande convertToEntity(CommandeDTO commandeDTO) {
        return modelMapper.map(commandeDTO, Commande.class);
    }


}
