package com.flipkart.exceptions;


import static com.flipkart.constants.Constants.RED_COLOR;
import static com.flipkart.constants.Constants.RESET_COLOR;
public class GymOwnerNotFoundException extends Exception {
    public GymOwnerNotFoundException(){
        super(RED_COLOR+"Gym Owner with this ID does not exist."+RESET_COLOR);
    }
}
