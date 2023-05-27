package com.nidyran.rolebasedspringsecurity.dao.repository;

import com.nidyran.rolebasedspringsecurity.dao.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    Contact findByRestaurantId(long restaurantId);

}
