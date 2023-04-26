package com.nidyran.rolebasedspringsecurity.dao.entity;


import com.nidyran.rolebasedspringsecurity.enmus.CommandeStatus;
import com.nidyran.rolebasedspringsecurity.enmus.PaymentMethod;
import lombok.*;

import javax.persistence.*;


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
    @Enumerated(EnumType.STRING)
    private CommandeStatus status;

    private Integer quantity;
    private String address;
    private String tel ;
    private String desc;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @ManyToOne
    @JoinColumn(name = "mealId")
    private Meal meal;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "panierId")
    private Panier panier;

    @ManyToOne
    @JoinColumn(name = "panierItemId")
    private PanierItem panierItem;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "restaurantId")
    private Restaurant restaurant;

}
