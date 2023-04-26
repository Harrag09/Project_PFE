package com.nidyran.rolebasedspringsecurity.service;

import com.nidyran.rolebasedspringsecurity.dao.entity.Panier;
import com.nidyran.rolebasedspringsecurity.dao.entity.User;
import com.nidyran.rolebasedspringsecurity.dao.repository.PanierRepository;
import com.nidyran.rolebasedspringsecurity.dao.repository.UserRepository;
import com.nidyran.rolebasedspringsecurity.enmus.AuthorityEnum;
import com.nidyran.rolebasedspringsecurity.service.model.panier.AddPanierDTO;
import com.nidyran.rolebasedspringsecurity.service.model.panier.PanierDTO;
import com.nidyran.rolebasedspringsecurity.service.model.restaurant.AddRestaurantDto;
import com.nidyran.rolebasedspringsecurity.service.model.user.LoginRequestDto;
import com.nidyran.rolebasedspringsecurity.service.model.user.LoginResponseDto;
import com.nidyran.rolebasedspringsecurity.service.model.user.RegisterRequestDto;
import com.nidyran.rolebasedspringsecurity.service.model.user.RegisterResponseDto;
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
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword()));
        return LoginResponseDto.builder()
                .token(BackendUtils.generateToken(loginRequestDto.getUsername(), secret, validity))
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
            CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> { register(registerRequestDto.getUsername(), registerRequestDto.getPassword(), AuthorityEnum.RESTAURANT_AUTHORITY, false);
            }).thenRun(() -> {
            AddRestaurantDto addRestaurantDto= new AddRestaurantDto();
            addRestaurantDto.setUserId(registerRequestDto.getId());
            addRestaurantDto.setName("Change My name");
            addRestaurantDto.setAddress("change my address");
            addRestaurantDto.setLog("photo here");
            restaurantService.createRestaurant(addRestaurantDto);
            });
            return null;
        }
        CompletableFuture<PanierDTO> future2 = CompletableFuture.supplyAsync(() -> {
            register(registerRequestDto.getUsername(), registerRequestDto.getPassword(), AuthorityEnum.CUSTOMER_AUTHORITY, true);
            AddPanierDTO panier = new AddPanierDTO();
            panier.setUserId(registerRequestDto.getId());
            panier.setTotal(0);
            PanierDTO createdPanierDTO = panierService.createPanier(panier);
            return createdPanierDTO;
        });

        return null;

    }

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

}
