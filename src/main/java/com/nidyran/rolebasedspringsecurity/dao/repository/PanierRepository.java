package com.nidyran.rolebasedspringsecurity.dao.repository;

import com.nidyran.rolebasedspringsecurity.dao.entity.Panier;

import com.nidyran.rolebasedspringsecurity.service.model.panier.PanierDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PanierRepository extends JpaRepository<Panier, Long> {


    Panier findByUserId(Long userId);


}
