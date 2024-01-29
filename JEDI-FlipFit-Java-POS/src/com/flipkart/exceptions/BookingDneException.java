package com.flipkart.exceptions;

public class BookingDneException extends Exception{
    public BookingDneException()
    {
        super("Booking does not exist");

    }
}