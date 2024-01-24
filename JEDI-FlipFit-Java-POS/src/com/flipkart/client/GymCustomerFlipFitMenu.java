package com.flipkart.client;
import java.util.*;
public class GymCustomerFlipFitMenu {
    public void showCustomerMenu(){

        int fl=0;
        while(fl==0)
        {
        System.out.println("1. Edit Profile\n2. View Profile\n3. View Bookings\n4. Book Slot\n5. Log out\n6. Back");
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
                    System.out.println("Book your Slot");
                    System.out.println("Select a Gym");
                    System.out.println("1. Bellandur\n2. Marathalli");
                    int gymOpt=in.nextInt();
                    switch (gymOpt)
                    {
                        case 1:
                            System.out.println("Listing Slots..");
                            break;
                        case 2:
                            System.out.println("Listing Slots..");
                            break;
                    }
                    break;
                case 5:
                    System.out.println("Function to Log out");
                    fl=1;
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