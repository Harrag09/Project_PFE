package com.nidyran.rolebasedspringsecurity.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BackendUtils {
    private BackendUtils() {
    }

    public static boolean isEmptyOrNull(String string){
        return string == null || string.length() == 0;
    }

    public static String getCurrentUsername(){
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
    }

    public static String generateToken(String username, String secret, long validity){
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + validity * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }
}
