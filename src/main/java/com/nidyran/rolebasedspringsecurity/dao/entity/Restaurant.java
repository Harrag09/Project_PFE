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

    @Lob
    @Column(nullable = false)
    private String log2;

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="UserId")
    private User user;

    private String latitude;
    private String longitude;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Category> categories;

}
