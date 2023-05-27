package com.nidyran.rolebasedspringsecurity.dao.repository;

import com.nidyran.rolebasedspringsecurity.dao.entity.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {

    List<Commande> findByRestaurantId(long restaurantId);

    List<Commande> findByCreatedAtBefore(LocalDateTime thresholdTime);

}
