package com.nidyran.rolebasedspringsecurity.Exeption;

public class CommandeNotFoundException extends RuntimeException {
    public CommandeNotFoundException(Long id) {
        super("Could not find restaurant with id " + id);
    }
}