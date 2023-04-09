package com.nidyran.rolebasedspringsecurity.dao.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Lob
    @Column(nullable = false)
    private String image;
}