package com.nidyran.rolebasedspringsecurity.Exeption;

public class PanierNotFoundException extends RuntimeException {
    public PanierNotFoundException() {
        super("Panier not exist.");
    }
}
