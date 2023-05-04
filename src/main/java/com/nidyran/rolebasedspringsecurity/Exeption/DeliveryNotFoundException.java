package com.nidyran.rolebasedspringsecurity.Exeption;

public class DeliveryNotFoundException extends RuntimeException {
    public DeliveryNotFoundException(long id) {
        super("Delivery with this name : " + id + " does not exist.");
    }

}
