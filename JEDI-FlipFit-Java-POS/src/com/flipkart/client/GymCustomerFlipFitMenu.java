package com.flipkart.client;
import com.flipkart.bean.Booking;
import com.flipkart.bean.Slot;
import com.flipkart.dao.BookingDAO;
import com.flipkart.dao.CustomerDAO;
import com.flipkart.dao.SlotDAO;
import com.flipkart.dao.UserDAO;
import com.flipkart.service.CustomerService;

import java.awt.print.Book;
import java.util.*;
public class GymCustomerFlipFitMenu
{
    CustomerService customerService=new CustomerService();
    public void showCustomerMenu(){
        UserDAO userDao= UserDAO.getInstance();
        CustomerDAO custDao = CustomerDAO.getInstance();
        BookingDAO b=BookingDAO.getInstance();
        String custId = Integer.toString(custDao.getIdFromName(userDao.getCurrentUser().get(0)));
        SlotDAO s=SlotDAO.getInstance();

        s.createSlot("1 Jan", "2", "2");
        s.createSlot("2 Jan", "4", "2");
        s.createSlot("3 Jan", "6", "1");
        s.createSlot("4 Jan", "8", "1");

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
                    System.out.println("cust: " + custId);
                    List<Booking> lb =  b.getBookingbyCustId(custId);
                    for(Booking b1 : lb){
                        Slot sl = s.getSlotsBySlotId(b1.getSlotId());
                        System.out.println("Gym : " + sl.getGymId() + " Time : " + sl.getStartTime());
                    }
                    break;

                case 4:
                    System.out.println("Book your Slot");
                    System.out.println("Select an area where you'd like to book a slot.");
                    System.out.println("1. Bellandur\n" +
                            "2. Marathahalli");

                    int gymOpt=in.nextInt();
                    List<Slot> l;


                    System.out.println("Choose a slot at from (GymName)" + gymOpt);
                    l=s.getSlotsByGymId(Integer.toString(gymOpt));
                    for(Slot slt:l)
                    {
                        System.out.println("Slot Id : " + slt.getSlotId() +" Date : "+slt.getDate()+"\n Time : "+slt.getStartTime());
                    }

                    String slotId = in.next();

                    b.addBooking(custId, slotId);
                    System.out.println("Booking added.");
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