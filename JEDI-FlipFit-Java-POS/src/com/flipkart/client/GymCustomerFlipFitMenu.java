package com.flipkart.client;
import java.util.*;
public class GymCustomerFlipFitMenu {
    public void showCustomerMenu(){

        int fl=0;
        while(fl==0)
        {
        System.out.println("1. Edit Profile\n2. View Bookings\n3. Book Slot\n4. Log out\n5. Exit");
        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();

            switch(choice){
                case 1:
                    System.out.println("Function to edit profile");
                    break;
                case 2:
                    System.out.println("Function to View profile");
                    break;
                case 3:
                    System.out.println("Function to View Booking");
                    break;
                case 4:
                    System.out.println("Function to Book Slots");
                    break;
                case 5:
                    System.out.println("Function to Log out");
                    break;
                case 6:
                    fl=1;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + choice);
            }
        }

    }
}