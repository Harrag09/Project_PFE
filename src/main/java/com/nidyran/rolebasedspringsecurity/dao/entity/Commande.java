package com.nidyran.rolebasedspringsecurity.dao.entity;


import com.nidyran.rolebasedspringsecurity.enmus.CommandeStatus;
import com.nidyran.rolebasedspringsecurity.enmus.PaymentMethod;
import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity
@Setter
@Getter
@NoArgsConstructor
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total")
    private double total;

    @Column(name = "user_id")
    private Long userId;

    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL)
    private List<CommandeItem> commandeItems;

    @Column(name = "address")
    private String address;

    @Column(name = "nom")
    private String nom;

    @Column(name = "tel")
    private String tel;

    @Column(name = "description")
    private String description;

    @Column(name = "commandeStatus")
    private String commandeStatus;

    @Column(name = "paymentMethod")
    private String paymentMethod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurantId")
    private Restaurant restaurant;

}
