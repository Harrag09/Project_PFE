package com.nidyran.rolebasedspringsecurity.Exeption;

public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException(long categoryId) {
        super("Category not found with id: " + categoryId);
    }
}