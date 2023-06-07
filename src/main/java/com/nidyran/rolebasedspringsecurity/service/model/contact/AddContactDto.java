package com.nidyran.rolebasedspringsecurity.service.model.contact;

import com.nidyran.rolebasedspringsecurity.dao.entity.Restaurant;
import com.nidyran.rolebasedspringsecurity.dao.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@RequiredArgsConstructor
public class AddContactDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String num;
    private String  message;
    private long userId;
    private long restaurantId;

}
