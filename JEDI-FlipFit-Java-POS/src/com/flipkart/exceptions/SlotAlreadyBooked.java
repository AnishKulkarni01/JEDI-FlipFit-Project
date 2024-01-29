package com.flipkart.exceptions;

public class SlotAlreadyBooked extends Exception{
    public SlotAlreadyBooked()
    {
        super("Slot already booked by you.");
    }
}
