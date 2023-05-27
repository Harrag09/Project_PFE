package com.nidyran.rolebasedspringsecurity.service;

import com.nidyran.rolebasedspringsecurity.Exeption.CommandeNotFoundException;
import com.nidyran.rolebasedspringsecurity.dao.entity.Commande;
import com.nidyran.rolebasedspringsecurity.dao.entity.Panier;
import com.nidyran.rolebasedspringsecurity.dao.entity.User;
import com.nidyran.rolebasedspringsecurity.dao.repository.PanierRepository;
import com.nidyran.rolebasedspringsecurity.dao.repository.UserRepository;
import com.nidyran.rolebasedspringsecurity.enmus.AuthorityEnum;
import com.nidyran.rolebasedspringsecurity.enmus.CommandeStatus;
import com.nidyran.rolebasedspringsecurity.service.model.commande.CommandeDTO;
import com.nidyran.rolebasedspringsecurity.service.model.panier.AddPanierDTO;
import com.nidyran.rolebasedspringsecurity.service.model.panier.PanierDTO;
import com.nidyran.rolebasedspringsecurity.service.model.restaurant.AddRestaurantDto;
import com.nidyran.rolebasedspringsecurity.service.model.user.*;
import com.nidyran.rolebasedspringsecurity.utils.BackendUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;


@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final PanierRepository panierRepository;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PanierService panierService;
    private final RestaurantService restaurantService;

    @Value("${jwt.validity}")
    private Long validity;

    @Value("${jwt.secret}")
    private String secret;

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        User user1=userRepository.findByUsername(loginRequestDto.getUsername());
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword()));
        return LoginResponseDto.builder()
                .token(BackendUtils.generateToken(loginRequestDto.getUsername(), secret, validity))
                .id(user1.getId())
                .build();
    }

    public RegisterResponseDto register(RegisterRequestDto registerRequestDto)
    {
        if (registerRequestDto.getAuthority().equals(AuthorityEnum.ADMIN_AUTHORITY))
        {
            log.warn("Admin registration is not allowed");
            return null;
        }
        if(AuthorityEnum.RESTAURANT_AUTHORITY.equals(registerRequestDto.getAuthority()))
        {
            register(registerRequestDto.getUsername(), registerRequestDto.getPassword(), AuthorityEnum.RESTAURANT_AUTHORITY, false);
            return register(registerRequestDto.getUsername(), registerRequestDto.getPassword(), AuthorityEnum.RESTAURANT_AUTHORITY, false);
        }
        if (AuthorityEnum.CUSTOMER_AUTHORITY.equals(registerRequestDto.getAuthority())) {
            register(registerRequestDto.getUsername(), registerRequestDto.getPassword(), AuthorityEnum.CUSTOMER_AUTHORITY, true);

             User user= userRepository.findByUsername(registerRequestDto.getUsername());

            Panier panier = new Panier();
            panier.setTotal(0);
            panier.setUser(user);

            Panier savedPanier = panierRepository.save(panier);
            // Return the RegisterResponseDto
            return new RegisterResponseDto();
        }

        return null; }

    public RegisterResponseDto register(String username, String password, AuthorityEnum authorityEnum, boolean active) {
        User user = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .authority(authorityEnum)
                .enabled(active)
                .build();
        user = userRepository.save(user);
        return RegisterResponseDto.builder().id(user.getId()).username(user.getUsername()).build();

    }

    public UserDto updateAuthByUserName(long id, boolean Enable) {
        User user = userRepository.findById(id);
        user.setEnabled(Enable);
        User updatedEnable = userRepository.save(user);
        return modelMapper.map(updatedEnable, UserDto.class);
    }




}
