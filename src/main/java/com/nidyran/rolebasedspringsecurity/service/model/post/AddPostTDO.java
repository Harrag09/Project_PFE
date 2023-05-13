package com.nidyran.rolebasedspringsecurity.service.model.post;

import com.nidyran.rolebasedspringsecurity.dao.entity.Restaurant;
import com.nidyran.rolebasedspringsecurity.dao.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@RequiredArgsConstructor
public class AddPostTDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String image;
    private String desc;
    private long restaurantId;


}
