package com.nidyran.rolebasedspringsecurity.service.model.commande;


import com.nidyran.rolebasedspringsecurity.dao.entity.CommandeItem;
import com.nidyran.rolebasedspringsecurity.dao.entity.Panier;
import com.nidyran.rolebasedspringsecurity.dao.entity.Restaurant;
import com.nidyran.rolebasedspringsecurity.dao.entity.User;
import com.nidyran.rolebasedspringsecurity.enmus.CommandeStatus;
import com.nidyran.rolebasedspringsecurity.enmus.PaymentMethod;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@RequiredArgsConstructor
public class AddCommandeDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private CommandeStatus status;

    private PaymentMethod paymentMethod;

    private Long panierId;

    private Long userId;

    private Long restaurantId;
}
