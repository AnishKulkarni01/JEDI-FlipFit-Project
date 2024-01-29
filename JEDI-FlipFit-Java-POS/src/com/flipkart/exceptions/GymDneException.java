package com.flipkart.exceptions;

public class GymDneException extends Exception{
    public GymDneException(){
        super("GymId does not exist.");
    }
}
