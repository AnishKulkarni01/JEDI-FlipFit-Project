package com.flipkart.exceptions;

import static com.flipkart.constants.Constants.RED_COLOR;
import static com.flipkart.constants.Constants.RESET_COLOR;

public class CustomerNotFoundException extends Exception {
    public CustomerNotFoundException(String customerName){
        super(RED_COLOR+"Customer not found!" +RESET_COLOR);
    }
}



