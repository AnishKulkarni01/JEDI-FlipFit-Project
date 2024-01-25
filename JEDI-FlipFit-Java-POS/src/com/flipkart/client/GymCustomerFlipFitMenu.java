package com.flipkart.client;
import com.flipkart.bean.Slot;
import com.flipkart.dao.SlotDAO;
import com.flipkart.service.CustomerService;

import java.util.*;
public class GymCustomerFlipFitMenu
{
    CustomerService customerService=new CustomerService();
    public void showCustomerMenu(){

        int loopFlag = 0;
        while(loopFlag == 0) {
            System.out.println("1. Edit Profile\n" +
                "2. View Profile\n" +
                "3. View Bookings\n" +
                "4. Book Slot\n" +
                "5. Log out\n" +
                "6. Back");

            Scanner in = new Scanner(System.in);
            int choice = in.nextInt();

            switch(choice){
                case 1:
                    System.out.println("Function to edit profile");

                    break;

                case 2:
                    System.out.println("Function to View profile");
                    customerService.viewProfile();
                    break;

                case 3:
                    System.out.println("Function to View Booking");
                    break;

                case 4:
                    System.out.println("Book your Slot");
                    System.out.println("Select an area where you'd like to book a slot.");
                    System.out.println("1. Bellandur\n" +
                            "2. Marathahalli");
                    //gymId


                    int gymOpt=in.nextInt();
                    SlotDAO s=SlotDAO.getInstance();
                    List<Slot> l=new ArrayList<>();
                    switch (gymOpt) {
                        //printavailableSlots
                        case 1:
                            System.out.println("Listing slots at Bellandur..");
                            l=s.getSlotsByGymId("1");
                            for(Slot slt:l)
                            {
                                System.out.println("Date : "+slt.getDate()+"\n Time : "+slt.getStartTime());
                            }
                            break;

                        case 2:
                            System.out.println("Listing slots at Marathahalli..");
                            l=s.getSlotsByGymId("2");
                            for(Slot slt:l)
                            {
                                System.out.println("Date : "+slt.getDate()+"\n Time : "+slt.getStartTime());
                            }
                            break;
                    }
                    break;

                case 5:
                    System.out.println("Function to Log out");
                    loopFlag = 1;
                    break;

                case 6:
                    loopFlag = 1;
                    break;

                default:
                    throw new IllegalStateException("Unexpected value: " + choice);
            }
        }

    }
}