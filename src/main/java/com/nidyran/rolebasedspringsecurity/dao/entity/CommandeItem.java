package com.nidyran.rolebasedspringsecurity.dao.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommandeItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "meal_id")
    private Meal meal;
    @ManyToOne
    @JoinColumn(name = "panier_item_id")
    private PanierItem panierItem;}

