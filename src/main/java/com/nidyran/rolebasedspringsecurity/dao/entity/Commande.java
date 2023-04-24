package com.nidyran.rolebasedspringsecurity.dao.entity;


import com.nidyran.rolebasedspringsecurity.enmus.CommandeStatus;
import com.nidyran.rolebasedspringsecurity.enmus.PaymentMethod;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
  //  private LocalDateTime dateCommande;
    @Enumerated(EnumType.STRING)
    private CommandeStatus status;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "panier_id")
    private Panier panier;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;


}
