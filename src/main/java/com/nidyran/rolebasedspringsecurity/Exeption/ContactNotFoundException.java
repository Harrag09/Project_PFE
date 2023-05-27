package com.nidyran.rolebasedspringsecurity.Exeption;

public class ContactNotFoundException extends RuntimeException {
    public ContactNotFoundException(Long id) {
        super("Contact not found for restaurant with ID: " + id);
    }
}