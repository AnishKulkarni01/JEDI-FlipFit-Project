package com.flipkart.exceptions;

import static com.flipkart.constants.Constants.RED_COLOR;
import static com.flipkart.constants.Constants.RESET_COLOR;

public class CustomerDetailsNotUpdatedException extends Exception {
    public CustomerDetailsNotUpdatedException(String customerId){
        super(RED_COLOR+"Details couldn't be updated for customer with ID " + customerId + RESET_COLOR);
    }
}



