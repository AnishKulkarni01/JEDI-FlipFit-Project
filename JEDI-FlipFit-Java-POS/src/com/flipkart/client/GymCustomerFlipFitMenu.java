package com.flipkart.client;
import com.flipkart.service.CustomerService;

import java.util.*;
import static com.flipkart.constants.Constants.*;

public class GymCustomerFlipFitMenu
{
    CustomerService customerService=new CustomerService();
    public void showCustomerMenu(){

        int loopFlag = 0;
        while(loopFlag == 0) {
            System.out.println("1. " + YELLOW_COLOR + "Edit Profile\n" + RESET_COLOR + "2. " + YELLOW_COLOR + "View Profile\n" + RESET_COLOR + "3. " + YELLOW_COLOR + "View Bookings\n" + RESET_COLOR +  "4. " + YELLOW_COLOR + "Book Slot\n" + RESET_COLOR + "5. " + YELLOW_COLOR +"Log out\n" + RESET_COLOR + "6. " +YELLOW_COLOR + "Back" + RESET_COLOR);

            Scanner in = new Scanner(System.in);
            int choice = in.nextInt();

            switch(choice){
                case 1:
                    System.out.println(GREEN_COLOR + "Profile edited successfully." + RESET_COLOR);
                    break;

                case 2:
                    System.out.println(BLUE_COLOR+ "Function to View profile." + RESET_COLOR);
                    customerService.viewProfile();
                    break;

                case 3:
                    System.out.println(BLUE_COLOR+ "Function to View Booking." + RESET_COLOR);
                    break;

                case 4:
//                    System.out.println("Book your Slot");
                    System.out.println("Select an area where you'd like to book a slot.");
                    System.out.println("    1. " +YELLOW_COLOR+ "Bellandur\n" + RESET_COLOR + "    2. "+ YELLOW_COLOR + "Marathalli" + RESET_COLOR);

                    int gymOpt=in.nextInt();

                    switch (gymOpt) {
                        case 1:
                            System.out.println(BLUE_COLOR + "Gyms list at Bellandur.." + RESET_COLOR);
                            break;

                        case 2:
                            System.out.println(BLUE_COLOR + "Gyms list at Marathalli.." + RESET_COLOR);
                            break;
                    }
                    break;

                case 5:
                    System.out.println(GREEN_COLOR + "Logged Out Successfully." + RESET_COLOR);
                    loopFlag=1;
                    break;

                case 6:
                    loopFlag = 1;
                    break;

                default:
                    throw new IllegalStateException(RED_COLOR+ "Unexpected value: " + choice + RESET_COLOR);
            }
        }

    }
}