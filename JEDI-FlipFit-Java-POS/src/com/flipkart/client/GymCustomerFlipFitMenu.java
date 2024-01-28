package com.flipkart.client;
import com.flipkart.bean.Booking;
import com.flipkart.bean.Gym;
import com.flipkart.bean.Slot;
import com.flipkart.dao.*;
import com.flipkart.service.CustomerService;

import java.util.*;

import static com.flipkart.constants.Constants.*;

public class GymCustomerFlipFitMenu {
    CustomerService customerService=new CustomerService();UserDAO userDao= UserDAO.getInstance();
    CustomerDAO customerDAO = CustomerDAO.getInstance();
    BookingDAO bookingDAO =BookingDAO.getInstance();
    String customerId = customerDAO.getIdFromName(userDao.getCurrentUser().get(0));
    SlotDAO slotDAO =SlotDAO.getInstance();
    GymDAO gymDao= GymDAO.getInstance();
    Scanner scanner = new Scanner(System.in);

    private void showMenuOptions() {
        System.out.println("1. " + YELLOW_COLOR + "Edit Profile\n" + RESET_COLOR + "2. " + YELLOW_COLOR + "View Profile\n" + RESET_COLOR + "3. " + YELLOW_COLOR + "View Bookings\n" + RESET_COLOR + "4. " + YELLOW_COLOR + "Book Slot\n" + RESET_COLOR + "5. " + YELLOW_COLOR + "Cancel Booking\n" + RESET_COLOR + "6. " + YELLOW_COLOR + "Log Out\n" + RESET_COLOR + "7. " + YELLOW_COLOR + "Back" + RESET_COLOR);
    }

    private void listSlots(){
        List<String> areas = gymDao.getAllAreas();
        for(String area : areas){
            System.out.println("    " + areas.indexOf(area)+1 + ". " + area);
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
        Set<String>st=slotDAO.getSlotsByCustomerId(customerId);

        System.out.println("Choose a slot at " + selectedArea);
        List<Slot> slotList = slotDAO.getSlotsByGymId(gymId);
        for(Slot slot: slotList) {
            if(!st.contains(slot.getSlotId())) System.out.println("Slot Id : " + slot.getSlotId() +" Date : "+slot.getDate()+" Time : "+slot.getStartTime());
        }
    }

    private void viewBookings(){
        System.out.println(BLUE_COLOR + "The bookings are as follows - " + RESET_COLOR);

        //booking.toString() later
        for(Booking booking : bookingDAO.getBookingbyCustId(customerId)){
            Slot slot = slotDAO.getSlotBySlotId(booking.getSlotId());
            System.out.println("BookingId : "+booking.getBookingId()+" Gym : " + gymDao.getGymById(slot.getGymId()).getName() + " Time : " + slot.getStartTime()+" Date : "+slot.getDate());
        }
    }


    private void bookSlot(){
        System.out.println(BLUE_COLOR + "Book your Slot :)" + RESET_COLOR);
        System.out.println("Select an area where you'd like to book a slot.");

        listSlots();

        String slotId = scanner.next();

        bookingDAO.addBooking(customerId, slotId);
        System.out.println(GREEN_COLOR + "Booking successfully added." + RESET_COLOR);
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
        System.out.println("Profile details are as follows - ");
        customerService.viewProfile();
    }

    private void editProfile() {
        while(true) {
            System.out.println("Choose an appropriate option to update the value - ");
            System.out.println("1. " + YELLOW_COLOR + "Email\n" + RESET_COLOR +
                    "2. " + YELLOW_COLOR + "Contact" + RESET_COLOR);
            int updateColumn = scanner.nextInt();

            switch (updateColumn) {
                case 1:
                    System.out.println("Enter the updated email - ");
                    String newValue = scanner.next();
                    customerService.updateCustomerDetails(newValue, "email", customerId);
                    System.out.println(GREEN_COLOR + "Email has been updated successfully." + RESET_COLOR);
                    return;
                case 2:
                    System.out.println("Enter the updated contact number - ");
                    String newValue1 = scanner.next();
                    customerService.updateCustomerDetails(newValue1, "contact", customerId);
                    System.out.println(GREEN_COLOR + "Contact number has been updated successfully." + RESET_COLOR);
                    return;
                default:
                    System.out.println(RED_COLOR + "Please select a valid option" + RESET_COLOR);
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
                    System.out.println(GREEN_COLOR + "Logged out successfully" + RESET_COLOR);
                    return;
                case 7:
                    return;
                default:
                    throw new IllegalStateException(RED_COLOR + "Unexpected value: " + choice + RESET_COLOR);
            }
        }
    }
}