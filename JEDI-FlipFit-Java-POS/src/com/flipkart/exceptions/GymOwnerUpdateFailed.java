package com.flipkart.exceptions;

import static com.flipkart.constants.Constants.RED_COLOR;
import static com.flipkart.constants.Constants.RESET_COLOR;
public class GymOwnerUpdateFailed extends Exception {
    public GymOwnerUpdateFailed()
    {
        super(RED_COLOR+"Gym Owner Update Falied !!"+RESET_COLOR);
    }

}
