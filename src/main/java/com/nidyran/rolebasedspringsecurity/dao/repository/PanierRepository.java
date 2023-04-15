package com.nidyran.rolebasedspringsecurity.dao.repository;

import com.nidyran.rolebasedspringsecurity.dao.entity.Panier;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface PanierRepository extends JpaRepository<Panier, Long> {


}
