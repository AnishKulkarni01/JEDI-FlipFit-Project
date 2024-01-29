package com.flipkart.exceptions;

public class GymAreaDneException extends Exception {
    public GymAreaDneException()
    {
        super("No gyms in the area");
    }
}
