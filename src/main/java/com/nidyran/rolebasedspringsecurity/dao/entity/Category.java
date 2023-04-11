package com.nidyran.rolebasedspringsecurity.dao.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Lob
    @Column(nullable = false)
    private String image;

    @OneToMany(mappedBy="category", orphanRemoval=true)
    private List<Meal> plats;

    public Category(long id) {
        this.id = id;
    }

}