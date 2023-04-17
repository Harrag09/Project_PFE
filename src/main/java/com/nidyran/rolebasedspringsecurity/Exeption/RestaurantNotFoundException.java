package com.nidyran.rolebasedspringsecurity.Exeption;

public class RestaurantNotFoundException extends RuntimeException {
    public RestaurantNotFoundException(Long id) {
        super("Could not find restaurant with id " + id);
    }
}
