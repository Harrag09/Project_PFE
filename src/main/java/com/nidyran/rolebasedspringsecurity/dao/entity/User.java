package com.nidyran.rolebasedspringsecurity.dao.entity;

import com.nidyran.rolebasedspringsecurity.enmus.AuthorityEnum;
import lombok.*;
import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true)
    private String username;

    @Column(nullable = false)
    private String password;



    @Enumerated(EnumType.STRING)
    private AuthorityEnum authority;
}
