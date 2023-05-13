package com.nidyran.rolebasedspringsecurity.service;

import com.nidyran.rolebasedspringsecurity.dao.entity.Category;
import com.nidyran.rolebasedspringsecurity.dao.entity.Panier;
import com.nidyran.rolebasedspringsecurity.dao.entity.Post;
import com.nidyran.rolebasedspringsecurity.dao.entity.User;
import com.nidyran.rolebasedspringsecurity.dao.repository.PanierRepository;
import com.nidyran.rolebasedspringsecurity.dao.repository.UserRepository;
import com.nidyran.rolebasedspringsecurity.enmus.AuthorityEnum;
import com.nidyran.rolebasedspringsecurity.service.model.category.CategoryDto;
import com.nidyran.rolebasedspringsecurity.service.model.panier.AddPanierDTO;
import com.nidyran.rolebasedspringsecurity.service.model.panier.PanierDTO;
import com.nidyran.rolebasedspringsecurity.service.model.post.PostTDO;
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

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;


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
            register(registerRequestDto.getUsername(), registerRequestDto.getPassword(), AuthorityEnum.RESTAURANT_AUTHORITY, true);
            return register(registerRequestDto.getUsername(), registerRequestDto.getPassword(), AuthorityEnum.RESTAURANT_AUTHORITY, true);
        }
        return register(registerRequestDto.getUsername(), registerRequestDto.getPassword(), AuthorityEnum.CUSTOMER_AUTHORITY, true);
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
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
    }
}
