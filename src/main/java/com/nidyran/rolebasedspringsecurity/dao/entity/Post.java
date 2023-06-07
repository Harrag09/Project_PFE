package com.nidyran.rolebasedspringsecurity.dao.entity;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Lob
    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String desc;

    private int nbLike;


    @ManyToOne(fetch=FetchType.EAGER, optional=false)
    @JoinColumn(name="Restaurant_ID")
    private Restaurant restaurant;

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="UserId")
    private User user;

    @ElementCollection
    private Set<Long> likedBy;

    private LocalDateTime createdAt;

}
