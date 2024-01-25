package com.flipkart.client;
import com.flipkart.bean.Booking;
import com.flipkart.bean.Slot;
import com.flipkart.dao.*;
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
        GymDAO gymDao= GymDAO.getInstance();

        s.createSlot("1 Jan", "2", "2");
        s.createSlot("2 Jan", "4", "2");
        s.createSlot("3 Jan", "6", "1");
        s.createSlot("4 Jan", "8", "1");
        s.createSlot("5 Jan", "9", "3");
        s.createSlot("5 Jan", "10", "3");
        gymDao.onBoardGym("Gym1", "123", "Marathalli", 10) ;
        gymDao.onBoardGym("Gym2", "456", "Bellandur", 10) ;
        gymDao.onBoardGym("Gym3", "789", "Whitefield", 10) ;


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
                    System.out.println("Enter new password");
                    String newPassword=in.next();
                    customerService.editProfile(Integer.parseInt(custId),newPassword);

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
                        System.out.println("Gym : " + sl.getGymId() + " Time : " + sl.getStartTime()+" Date : "+sl.getDate());
                    }
                    break;

                case 4:
                    System.out.println("Book your Slot");
                    System.out.println("Select an area where you'd like to book a slot.");
                    List<String> areas=new ArrayList<>();
                    areas=gymDao.getAllAreas();
                    int idx=1;
                    for(String area:areas)
                    {
                        System.out.println(idx+". "+area);
                        idx++;
                    }

                    int gymOpt=in.nextInt();
                    List<Slot> l;
                    String selectedArea=areas.get(gymOpt-1);

                    System.out.println("Choose a slot at " + selectedArea);
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