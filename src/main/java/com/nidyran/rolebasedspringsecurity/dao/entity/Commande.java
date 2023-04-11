package com.nidyran.rolebasedspringsecurity.dao.entity;

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
    private long id;

    @Column(unique = true)
    private int refcommande;

    @Column(nullable = false)
    private String etatcommande;

    @ManyToOne( cascade = CascadeType.ALL )
    @JoinColumn( name="idUser" )
    private User user;
}
