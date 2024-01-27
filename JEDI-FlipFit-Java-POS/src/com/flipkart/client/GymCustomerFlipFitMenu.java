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




            int loopFlag = 0;
        while(loopFlag == 0) {
            System.out.println("1. Edit Profile\n" +
                    "2. View Profile\n" +
                "3. View Bookings\n" +
                "4. Book Slot\n"+
                "5. Cancel Booking\n"+
                    "6. Log out\n" +
                "7. Back");

            Scanner in = new Scanner(System.in);
            int choice = in.nextInt();

            switch(choice){
                case 1:
                    System.out.println("Function to edit profile");
                    System.out.println("1. Email\n2. Contact");
                    int updOpt=in.nextInt();
                    System.out.println("Enter updated value");
                    String newVal=in.next();
                    if(updOpt==1)
                        customerService.updateCustomerDetails(newVal,"email", Integer.parseInt(custId));
                    if(updOpt==2)
                        customerService.updateCustomerDetails(newVal,"contact", Integer.parseInt(custId));


                    break;

                case 2:
                    System.out.println("Function to View profile");
                    customerService.viewProfile();
                    break;

                case 3:
                    System.out.println("Function to View Booking");
                    //System.out.println("cust: " + custId);
                    List<Booking> lb =  b.getBookingbyCustId(custId);
                    for(Booking b1 : lb){
                        Slot sl = s.getSlotBySlotId(b1.getSlotId());
                        System.out.println("BookingId : "+b1.getBookingId()+" Gym : " + sl.getGymId() + " Time : " + sl.getStartTime()+" Date : "+sl.getDate());
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
                    System.out.println("Cancel Booking");
                    List<Booking>canBl=b.getBookingbyCustId(custId);
                    for(Booking book:canBl)
                    {
                        Slot sb=s.getSlotBySlotId(book.getSlotId());
                        System.out.println("BookingId : "+book.getBookingId()+" Date : "+sb.getDate()+" Time : "+sb.getStartTime());
                    }
                    System.out.println("Enter BookingId");
                    String delBookId=in.next();
                    b.deleteBookingId(delBookId);
                case 6:
                    System.out.println("Function to Log out");
                    loopFlag = 1;
                    break;

                case 7:
                    loopFlag = 1;
                    break;

                default:
                    throw new IllegalStateException("Unexpected value: " + choice);
            }
        }

    }
}