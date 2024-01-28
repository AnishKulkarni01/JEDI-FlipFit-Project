package com.flipkart.client;
import com.flipkart.bean.Booking;
import com.flipkart.bean.Gym;
import com.flipkart.bean.Slot;
import com.flipkart.dao.*;
import com.flipkart.service.CustomerService;

import java.util.*;

public class GymCustomerFlipFitMenu {
    CustomerService customerService=new CustomerService();UserDAO userDao= UserDAO.getInstance();
    CustomerDAO customerDAO = CustomerDAO.getInstance();
    BookingDAO bookingDAO =BookingDAO.getInstance();
    String customerId = customerDAO.getIdFromName(userDao.getCurrentUser().get(0));
    SlotDAO slotDAO =SlotDAO.getInstance();
    GymDAO gymDao= GymDAO.getInstance();
    Scanner scanner = new Scanner(System.in);

    private void showMenuOptions() {
        System.out.println("1. Edit Profile\n" +
                "2. View Profile\n" +
                "3. View Bookings\n" +
                "4. Book Slot\n" +
                "5. Cancel Booking\n" +
                "6. Log out\n" +
                "7. Back");
    }

    private void listSlots(){
        List<String> areas = gymDao.getAllAreas();
        for(String area : areas){
            System.out.println(areas.indexOf(area)+1 + ". " + area);
        }
        int areaOption = scanner.nextInt();
        String selectedArea = areas.get(areaOption-1);
        List<Gym>gymList=gymDao.getGymsByArea(selectedArea);
        System.out.println("Choose GymId : ");
        for(int i=0;i<gymList.size();i++)
        {
            System.out.println("GymId : "+gymList.get(i).getGymId()+",GymName : "+gymList.get(i).getName());
        }
        System.out.println("Enter GymId");
        String gymId=scanner.next();

        System.out.println("Choose a slot at " + selectedArea);
        List<Slot> slotList = slotDAO.getSlotsByGymId(gymId);
        for(Slot slot: slotList) {
            System.out.println("Slot Id : " + slot.getSlotId() +" Date : "+slot.getDate()+"\n Time : "+slot.getStartTime());
        }
    }

    private void viewBookings(){
        System.out.println("Function to View Booking");

        //booking.toString() later
        for(Booking booking : bookingDAO.getBookingbyCustId(customerId)){
            Slot slot = slotDAO.getSlotBySlotId(booking.getSlotId());
            System.out.println("BookingId : "+booking.getBookingId()+" Gym : " + gymDao.getGymById(slot.getGymId()).getName() + " Time : " + slot.getStartTime()+" Date : "+slot.getDate());
        }
    }

    private void bookSlot(){
        System.out.println("Book your Slot");
        System.out.println("Select an area where you'd like to book a slot.");

        listSlots();

        String slotId = scanner.next();

        bookingDAO.addBooking(customerId, slotId);
        System.out.println("Booking added.");
    }

    private void cancelSlot(){
        for(Booking booking : bookingDAO.getBookingbyCustId(customerId)) {
            Slot slot = slotDAO.getSlotBySlotId(booking.getSlotId());
            System.out.println("BookingId : "+booking.getBookingId()+" Date : "+slot.getDate()+" Time : "+slot.getStartTime());
        }

        System.out.println("Enter BookingId");
        String deleteBookingId = scanner.next();
        bookingDAO.deleteBookingId(deleteBookingId);
    }

    private void viewProfile(){
        System.out.println("Function to View profile");
        customerService.viewProfile();
    }

    private void editProfile() {
        while(true) {
            System.out.println("Function to edit profile");
            System.out.println("1. Email\n2. Contact");
            int updateColumn = scanner.nextInt();

            System.out.println("Enter updated value");
            String newValue = scanner.next();

            switch (updateColumn) {
                case 1:
                    customerService.updateCustomerDetails(newValue, "email", customerId);
                    return;

                case 2:
                    customerService.updateCustomerDetails(newValue, "contact", customerId);
                    return;

                default:
                    System.out.println("Please select a valid option");
            }
        }
    }

    public void showCustomerMenu(){
        while(true){
            showMenuOptions();

            int choice = scanner.nextInt();

            switch(choice){
                case 1:
                    editProfile();
                    break;

                case 2:
                    viewProfile();
                    break;

                case 3:
                    viewBookings();
                    break;

                case 4:
                    bookSlot();
                    break;

                case 5:
                    cancelSlot();
                    break;

                case 6:
                    System.out.println("Function to Log out");
                    return;

                case 7:
                    return;

                default:
                    throw new IllegalStateException("Unexpected value: " + choice);
            }
        }
    }
}