package com.nidyran.rolebasedspringsecurity.dao.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Lob
    @Column(nullable = false)
    private String log;

    @OneToMany(mappedBy="restaurant", orphanRemoval=true)
    private List<Category> categories;

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="UserId")
    private User user;



}
