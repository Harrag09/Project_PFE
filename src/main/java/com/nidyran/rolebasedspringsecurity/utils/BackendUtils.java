package com.nidyran.rolebasedspringsecurity.utils;

import org.springframework.security.core.context.SecurityContextHolder;

public class BackendUtils {
    private BackendUtils() {
    }

    public static boolean isEmptyOrNull(String string){
        return string == null || string.length() == 0;
    }

    public static String getCurrentUsername(){
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
    }

}
