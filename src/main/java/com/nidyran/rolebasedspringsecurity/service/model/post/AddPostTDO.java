package com.nidyran.rolebasedspringsecurity.service.model.post;

import com.nidyran.rolebasedspringsecurity.dao.entity.Restaurant;
import com.nidyran.rolebasedspringsecurity.dao.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class AddPostTDO {


    private String image;
    private String desc;
    private long restaurantId;
    private LocalDateTime createdAt;


}
