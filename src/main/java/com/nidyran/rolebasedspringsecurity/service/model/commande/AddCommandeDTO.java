package com.nidyran.rolebasedspringsecurity.service.model.commande;

import com.nidyran.rolebasedspringsecurity.enmus.CommandeStatus;
import com.nidyran.rolebasedspringsecurity.enmus.PaymentMethod;
import com.nidyran.rolebasedspringsecurity.service.model.restaurant.RestaurantDto;
import com.nidyran.rolebasedspringsecurity.service.model.user.UserDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class AddCommandeDTO {
    private Long panierId;
    private String address;
    private String firstName;
    private String lastName;
    private String tel;
    private PaymentMethod paymentMethod;
}
