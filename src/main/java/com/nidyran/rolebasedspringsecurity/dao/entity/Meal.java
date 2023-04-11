package com.nidyran.rolebasedspringsecurity.dao.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String name;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private String desc;

    @ManyToOne(fetch=FetchType.EAGER, optional=false)
    @JoinColumn(name="CATEGORY_ID")
    private Category category;
}