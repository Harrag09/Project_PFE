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

    @Column(unique = true, name = "nameMeal")
    private String name;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private String desc;

    @Lob
    @Column(nullable = false)
    private String photo;



    @ManyToOne(fetch=FetchType.EAGER, optional=false)
    @JoinColumn(name="CATEGORY_ID")
    private Category category;




}