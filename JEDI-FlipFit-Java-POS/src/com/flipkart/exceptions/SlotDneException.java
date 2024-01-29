package com.flipkart.exceptions;

public class SlotDneException extends Exception{
    public SlotDneException()
    {
        super("SlotId does not exist");
    }
}
