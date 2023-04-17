package com.nidyran.rolebasedspringsecurity.Exeption;

public class MealNotFoundException extends RuntimeException {
    public MealNotFoundException(Long id) {
        super("Meal with ID " + id + " does not exist.");
    }
}
