package com.flipkart.client;

import com.flipkart.bean.Gym;
import com.flipkart.bean.Slot;
import com.flipkart.dao.*;
import com.flipkart.service.GymOwnerService;
import com.flipkart.service.GymOwnerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GymOwnerFlipFitMenu {
    UserDAO userDao = UserDAO.getInstance();
    SlotDAO slotDAO = SlotDAO.getInstance();
    GymDAO gymDao = GymDAO.getInstance();
    GymOwnerDAO ownDao = GymOwnerDAO.getInstance();
    String gymOwnerId = ownDao.getIdFromName(userDao.getCurrentUser().get(0));
    Scanner scanner= new Scanner(System.in);
    GymOwnerService gymOwnerService=new GymOwnerService();

    private void showMenuOptions(){
        System.out.println("1. Request Gym Onboarding\n" +
                "2. Gym Details Update\n" +
                "3. Add Slot\n" +
                "4. Update Slot\n"+
                "5. View Slots\n"+
                        "6. View Profile\n"+
                "7. Edit Profile\n" +
                "8. View Gyms\n"+
                "9. View Pending Requests\n"+
                "10. Log out\n" +
                "11. Back");
    }

    private void sendOnboardingRequest(){
        System.out.println("Enter Gym Details");

        System.out.println("Enter Gym Name : ");
        String gymName = scanner.next();
        System.out.println("Enter GSTIN : ");
        String gstin = scanner.next();
        System.out.println("Enter Gym City : ");
        String city = scanner.next();
        System.out.println("Enter Seats : ");
        int seats = scanner.nextInt();

        gymDao.sendOnboardReq(gymName, gstin, city, seats, gymOwnerId);
        System.out.println("Request Sent Successfully");
    }

    private void updateGym(String gymId){
        while(true) {
            System.out.println("Select Option to Update");
            System.out.println("1. Name\n2. City\n3. Seats\n4. GSTIN");
            int updateColumn = scanner.nextInt();

            System.out.println("Enter new value");
            String newValue = scanner.next();

            switch (updateColumn) {
                case 1:
                    gymDao.updateGym(newValue, "name", gymId);
                    return;

                case 2:
                    gymDao.updateGym(newValue, "city", gymId);
                    return;

                case 3:
                    gymDao.updateGym(newValue, "seats", gymId);
                    return;

                case 4:
                    gymDao.updateGym(newValue, "gstin", gymId);
                    return;

                default:
                    System.out.println("Please select a valid option");
            }
        }
    }
    private void viewProfile(){
        System.out.println("Function to View profile");
        gymOwnerService.viewProfile();
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
                    gymOwnerService.updateGymOwnerDetails(newValue, "email", gymOwnerId);
                    return;

                case 2:
                    gymOwnerService.updateGymOwnerDetails(newValue, "contact", gymOwnerId);
                    return;

                default:
                    System.out.println("Please select a valid option");
            }
        }
    }
    private void addSlot(){
        System.out.println("Add Slots");

        System.out.println("Enter the following details : ");
        System.out.println("GymId : ");
        String gymId = scanner.next();
        System.out.println("Date : ");
        String date = scanner.next();
        System.out.println("StartTime : ");
        String time = scanner.next();

        slotDAO.createSlot(date, time, gymId);
    }

    private void updateSlot(){
        while(true) {
            System.out.println("Slot Details Update");
            System.out.println("Enter SlotId");
            String updateSlotId = scanner.next();

            System.out.println("Select Option to Update");
            System.out.println("1. Date\n2. Start Time");
            int updateSlotColumn = scanner.nextInt();

            System.out.println("Enter new value");
            String newValue = scanner.next();

            switch(updateSlotColumn){
                case 1:
                    slotDAO.updateSlot(newValue, "date", updateSlotId);
                    return;

                case 2:
                    slotDAO.updateSlot(newValue, "startTime", updateSlotId);
                    return;

                default:
                    System.out.println("Please select valid options.");
            }
        }
    }

    private void viewSlots(){
        System.out.println("View Slots");
        System.out.println("Enter GymId");
        int viewSlotGymId= scanner.nextInt();

        //need slot.toString()
        List<Slot> slotList=slotDAO.getSlotsByGymId(Integer.toString(viewSlotGymId));
        if(slotList.isEmpty())
        {
            System.out.println("Enter correect gymId");
            return;
        }
        for(Slot slot : slotList) {
            System.out.println("SlotId : "+slot.getSlotId()+" StartTime : "+slot.getStartTime()+" Date : "+slot.getDate());
        }
    }

    private void viewGyms(){
        System.out.println("Viewing Gyms");
        for(Gym gym : gymDao.getGymsByOwner(gymOwnerId)){
            System.out.println(gym.toString());
        }
    }

    private void viewPendingRequests(){
        System.out.println("Showing Pending Requests");
        for(Gym gym : gymDao.viewPendingRequests(Integer.parseInt(gymOwnerId))){
            System.out.println(gym.toString());
        }
    }

    public void showGymOwnerFlipMenu() {
        while(true) {
            showMenuOptions();

            int opt=scanner.nextInt();
            switch(opt) {
                case 1:
                    sendOnboardingRequest();
                    break;

                case 2:
                    System.out.println("Gym Details Update");
                    System.out.println("Enter GymId");
                    String updateGymId = scanner.next();

                    updateGym(updateGymId);
                    break;

                case 3:
                    addSlot();
                    break;

                case 4:
                    updateSlot();
                    break;

                case 5:
                    viewSlots();
                    break;
                case 6:
                    viewProfile();
                    break;
                case 7:
                    editProfile();
                    break;

                case 8:
                    viewGyms();
                    break;

                case 9:
                    viewPendingRequests();
                    break;

                case 10:
                    System.out.println("Logging out");
                    return;

                case 11:
                    return;

                default:
                    throw new IllegalStateException("Unexpected value: " + opt);
            }
        }
    }
}
