package com.flipkart.client;

import com.flipkart.bean.Gym;
import com.flipkart.bean.Slot;
import com.flipkart.dao.*;
import com.flipkart.service.GymOwnerService;
import com.flipkart.service.GymOwnerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.flipkart.constants.Constants.*;

public class GymOwnerFlipFitMenu {
    UserDAO userDao = UserDAO.getInstance();
    SlotDAO slotDAO = SlotDAO.getInstance();
    GymDAO gymDao = GymDAO.getInstance();
    GymOwnerDAO ownDao = GymOwnerDAO.getInstance();
    String gymOwnerId = ownDao.getIdFromName(userDao.getCurrentUser().get(0)).get(0);
    Scanner scanner= new Scanner(System.in);
    GymOwnerService gymOwnerService=new GymOwnerService();

    private void showMenuOptions(){
        System.out.println("1. " + YELLOW_COLOR + "Request Gym Onboarding\n" + RESET_COLOR +
                "2. " + YELLOW_COLOR + "Gym Details Update\n" + RESET_COLOR  +
                "3. " + YELLOW_COLOR + "Add Slot\n" + RESET_COLOR +
                "4. " + YELLOW_COLOR + "Update Slot\n" + RESET_COLOR +
                "5. " + YELLOW_COLOR + "View Slots\n" + RESET_COLOR +
                "6. " + YELLOW_COLOR + "Delete Slot\n" + RESET_COLOR +
                "7. " + YELLOW_COLOR + "View Profile\n" + RESET_COLOR +
                "8. " + YELLOW_COLOR + "Edit Profile\n" + RESET_COLOR +
                "9. " + YELLOW_COLOR + "View Gyms\n" + RESET_COLOR +
                "10. " + YELLOW_COLOR + "View Pending Requests\n" + RESET_COLOR +
                "11. " + YELLOW_COLOR + "Log Out\n" + RESET_COLOR +
                "12. " + YELLOW_COLOR + "Back" + RESET_COLOR);
    }

    private void sendOnboardingRequest(){
        System.out.println("Enter Gym Details - ");

        System.out.println("Enter Gym Name : ");
        String gymName = scanner.next();
        System.out.println("Enter GSTIN : ");
        String gstin = scanner.next();
        System.out.println("Enter Gym City : ");
        String city = scanner.next();
        System.out.println("Enter Seats : ");
        int seats = scanner.nextInt();

        gymDao.sendOnboardReq(gymName, gstin, city, seats, gymOwnerId);
        System.out.println(GREEN_COLOR + "Request Sent Successfully" + RESET_COLOR);
    }

    private void updateGym(String gymId){
        while(true) {
            System.out.println("Select Option to Update - ");
            System.out.println("1. " + YELLOW_COLOR + "Name\n" + RESET_COLOR +
                    "2. " + YELLOW_COLOR + "City\n" + RESET_COLOR +
                    "3. " + YELLOW_COLOR + "Seats\n" + RESET_COLOR +
                    "4. " + YELLOW_COLOR + "GSTIN" + RESET_COLOR);
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
                    System.out.println(RED_COLOR + "Please select a valid option" + RESET_COLOR);
            }
        }
    }
    private void viewProfile(){
        System.out.println("Profile details are as follows - ");
        gymOwnerService.viewProfile();
    }
    private void editProfile() {
        while(true) {
            System.out.println("Choose an appropriate option to update the value - ");
            System.out.println("1. " + YELLOW_COLOR + "Email\n" + RESET_COLOR +
                    "2. " + YELLOW_COLOR + "Contact" + RESET_COLOR);
            int updateColumn = scanner.nextInt();

            switch (updateColumn) {
                case 1:
                    System.out.println("Enter the updated email");
                    String newValue = scanner.next();
                    gymOwnerService.updateGymOwnerDetails(newValue, "email", gymOwnerId);
                    System.out.println(GREEN_COLOR + "Email has been updated successfully." + RESET_COLOR);
                    return;
                case 2:
                    System.out.println("Enter the updated contact number - ");
                    String newValue1 = scanner.next();
                    gymOwnerService.updateGymOwnerDetails(newValue1, "contact", gymOwnerId);
                    System.out.println(GREEN_COLOR + "Contact number has been updated successfully." + RESET_COLOR);
                    return;
                default:
                    System.out.println(RED_COLOR + "Please select a valid option" + RESET_COLOR);
            }
        }
    }
    private void addSlot(){
        System.out.println("Add Slots");

        System.out.println(BLUE_COLOR + "Enter the following details - " + RESET_COLOR);
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

            System.out.println("Select Option to Update - ");
            System.out.println("1. " + YELLOW_COLOR + "Date\n" + RESET_COLOR +
                    "2. " + YELLOW_COLOR + "Start Time" + RESET_COLOR);
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
                    System.out.println(RED_COLOR + "Please select valid option." + RESET_COLOR);
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
            System.out.println("Enter correct gymId");
            return;
        }
        String[] headers = {PURPLE_COLOR + " Slot Id " + RESET_COLOR, PURPLE_COLOR + " Start Time " + RESET_COLOR, PURPLE_COLOR + " Date " + RESET_COLOR};
        System.out.println("| " + String.join(" | ", headers) + " |");
        for(Slot slot : slotList) {
            ArrayList<String> details = new ArrayList<>();
            details.add(slot.getSlotId());
            details.add(slot.getStartTime());
            details.add(slot.getDate());
            String formatSpecifier;
            for(int i=0; i< details.size(); i++){
                if(i==0){
                    formatSpecifier = "%-10s";
                }
                else if(i==1){
                    formatSpecifier = "%-13s";
                }
                else{
                    formatSpecifier = "%-6s";
                }
                System.out.printf("| " + formatSpecifier, details.get(i));
            }
            System.out.println(" |");
//            System.out.println("SlotId : "+slot.getSlotId()+" StartTime : "+slot.getStartTime()+" Date : "+slot.getDate());
        }
    }
    private void deleteSlot()
    {
        System.out.println("Enter SlotId : ");
        int delSlotId=scanner.nextInt();
        slotDAO.deleteSlotById(Integer.toString(delSlotId));
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
                    deleteSlot();
                    break;
                case 7:
                    viewProfile();
                    break;
                case 8:
                    editProfile();
                    break;
                case 9:
                    viewGyms();
                    break;
                case 10:
                    viewPendingRequests();
                    break;
                case 11:
                    System.out.println(GREEN_COLOR + "Logged out successfully." + RESET_COLOR);
                    return;
                case 12:
                    return;
                default:
                    throw new IllegalStateException(RED_COLOR + "Unexpected value: " + opt + RESET_COLOR);
            }
        }
    }
}
