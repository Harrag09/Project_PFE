package com.nidyran.rolebasedspringsecurity.dao.repository;

import com.nidyran.rolebasedspringsecurity.dao.entity.Delivery;
import com.nidyran.rolebasedspringsecurity.dao.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface DeliveryRepository  extends JpaRepository<Delivery, Long> {
    Optional<Delivery> findById(Long id);
    List<Delivery> findByNameContainingOrAddressContaining(String name, String address);
}
