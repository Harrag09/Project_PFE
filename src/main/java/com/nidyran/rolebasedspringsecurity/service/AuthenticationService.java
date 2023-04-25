package com.nidyran.rolebasedspringsecurity.service;

import com.nidyran.rolebasedspringsecurity.dao.entity.Panier;
import com.nidyran.rolebasedspringsecurity.dao.entity.User;
import com.nidyran.rolebasedspringsecurity.dao.repository.UserRepository;
import com.nidyran.rolebasedspringsecurity.enmus.AuthorityEnum;
import com.nidyran.rolebasedspringsecurity.service.model.panier.AddPanierDTO;
import com.nidyran.rolebasedspringsecurity.service.model.restaurant.AddRestaurantDto;
import com.nidyran.rolebasedspringsecurity.service.model.user.LoginRequestDto;
import com.nidyran.rolebasedspringsecurity.service.model.user.LoginResponseDto;
import com.nidyran.rolebasedspringsecurity.service.model.user.RegisterRequestDto;
import com.nidyran.rolebasedspringsecurity.service.model.user.RegisterResponseDto;
import com.nidyran.rolebasedspringsecurity.utils.BackendUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {

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

    public RegisterResponseDto register(RegisterRequestDto registerRequestDto) {
        if (registerRequestDto.getAuthority().equals(AuthorityEnum.ADMIN_AUTHORITY)) {
            log.warn("Admin registration is not allowed");
            return null;
        }
        else if(AuthorityEnum.RESTAURANT_AUTHORITY.equals(registerRequestDto.getAuthority())){

          register(registerRequestDto.getUsername(), registerRequestDto.getPassword(), AuthorityEnum.RESTAURANT_AUTHORITY, false);


            return null;
        }
        else{
        register(registerRequestDto.getUsername(), registerRequestDto.getPassword(), AuthorityEnum.CUSTOMER_AUTHORITY, true);

        AddPanierDTO panier = new AddPanierDTO();
        panier.setUserId(registerRequestDto.getId());
        panierService.createPanier(panier);
            AddRestaurantDto restaurantDto = new AddRestaurantDto();
            restaurantDto.setUserId(registerRequestDto.getId());
            restaurantDto.setLog("azerty");
            restaurantDto.setAddress("azerty");
            restaurantDto.setName(registerRequestDto.getUsername()+" RESTO");
            restaurantService.createRestaurant(restaurantDto);

        return null;}
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
