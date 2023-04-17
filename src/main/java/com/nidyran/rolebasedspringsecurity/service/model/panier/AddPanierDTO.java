package com.nidyran.rolebasedspringsecurity.service.model.panier;

import com.nidyran.rolebasedspringsecurity.dao.entity.User;
import com.nidyran.rolebasedspringsecurity.service.model.user.UserDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@RequiredArgsConstructor
public class AddPanierDTO {
    private Long id;
    private User user;
    private double total;
    public Long getUserId() {
        return user.getId();
    }
}
