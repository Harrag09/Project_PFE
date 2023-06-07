package com.nidyran.rolebasedspringsecurity.service.model.post;

import com.nidyran.rolebasedspringsecurity.dao.entity.Restaurant;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class PostTDO {

    private long id;
    private long restaurantId;
    private String image;
    private String desc;
    private int nbLike;
    private LocalDateTime createdAt;
    private long userId;
    private boolean likedByUser;
}
