package com.flipkart.client;

import com.flipkart.bean.Gym;
import com.flipkart.bean.GymOwner;
import com.flipkart.dao.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.flipkart.constants.Constants.*;

public class AdminFlipFitMenu {
    GymDAO gymDao= GymDAO.getInstance();
    GymOwnerDAO gymOwnerDAO = GymOwnerDAO.getInstance();
    Scanner scanner = new Scanner(System.in);

    public void showMenuOptions(){
        System.out.println("1. " + YELLOW_COLOR + "View Pending Requests.\n" + RESET_COLOR + "2. " + YELLOW_COLOR + "Approve Gym Onboarding Requests.\n" + RESET_COLOR + "3. " + YELLOW_COLOR + "Decline Gym Onboarding Requests.\n" + RESET_COLOR + "4. " + YELLOW_COLOR + "Approve Gym Owner Requests.\n" + RESET_COLOR + "5. " + YELLOW_COLOR + "Decline Gym Owner Requests.\n" + RESET_COLOR + "6. " + YELLOW_COLOR +"Log Out\n" + RESET_COLOR + "7. " + YELLOW_COLOR + "Back" + RESET_COLOR);
    }

    private void printGymRequests(){
        List<Gym> requestDetails = gymDao.viewRequests();
        String[] headers = {PURPLE_COLOR + "  GymId  " + RESET_COLOR, PURPLE_COLOR + "  Name   " + RESET_COLOR, PURPLE_COLOR + "  City   " + RESET_COLOR, PURPLE_COLOR + "  GSTIN  " + RESET_COLOR, PURPLE_COLOR + "  Seats   " + RESET_COLOR};
        System.out.println("| " + String.join(" | ", headers) + " |");
        for(Gym gym : requestDetails){
            String output = gym.toString();
//            System.out.println(output);
            String[] keyValuePairs = output
                    .replace("{", "")
                    .replace("}", "")
                    .split(",\\s*");

            for (String pair : keyValuePairs) {
                String[] entry = pair.split("=");
                String value = entry[1].trim();
                String formatSpecifier = "%-10s";
                System.out.printf("| " + formatSpecifier, value);
            }
            System.out.println(" |");
        }
    }

    private void printGymOwnerRequests(){
        List<GymOwner> gymOwnerList = gymOwnerDAO.getPendingGymOwners();
        String[] headers = {PURPLE_COLOR + " Gym Owner Id " + RESET_COLOR, PURPLE_COLOR + " Gym Owner Name " + RESET_COLOR, PURPLE_COLOR + " Gym Owner Contact " + RESET_COLOR, PURPLE_COLOR + " Gym Owner Email " + RESET_COLOR};
        System.out.println("| " + String.join(" | ", headers) + " |");
        for(GymOwner gymOwner : gymOwnerList){
            ArrayList<String> details = new ArrayList<>();
            details.add(gymOwner.getGymOwnerId());
            details.add(gymOwner.getName());
            details.add(gymOwner.getContact());
            details.add(gymOwner.getEmail());
            String formatSpecifier;
            for(int i=0; i< details.size(); i++){
                if(i==0){
                    formatSpecifier = "%-15s";
                }
                else if(i==1){
                    formatSpecifier = "%-17s";
                }
                else if(i==2){
                    formatSpecifier = "%-20s";
                }
                else{
                    formatSpecifier = "%-17s";
                }
                System.out.printf("| " + formatSpecifier, details.get(i));
            }
            System.out.println(" |");
        }
    }

    public void showAdminFlipFitMenu(){
        while(true) {
            showMenuOptions();

            int action = scanner.nextInt();
            switch(action){
                case 1:
                    System.out.println(BLUE_COLOR + "Viewing Pending Requests" + RESET_COLOR);
                    printGymRequests();
                    break;
                case 2:
                    printGymRequests();
                    System.out.println(BLUE_COLOR + "Select GymId to Approve Request - " + RESET_COLOR);
                    int approvedGymId= scanner.nextInt();
                    gymDao.onBoardGym(String.valueOf(approvedGymId));
                    break;
                case 3:
                    printGymRequests();
                    System.out.println(BLUE_COLOR + "Select GymId to Reject Request - " + RESET_COLOR);
                    int rejectGymId= scanner.nextInt();
                    gymDao.deleteGymRequest(String.valueOf(rejectGymId));
                    break;
                case 4:
                    printGymOwnerRequests();
                    System.out.println(BLUE_COLOR + "Select GymOwnerId to approve request - " + RESET_COLOR);
                    int approveGymOwnerId = scanner.nextInt();
                    gymOwnerDAO.approveGymOwner(String.valueOf(approveGymOwnerId));
                    break;
                case 5:
                    printGymOwnerRequests();
                    System.out.println(BLUE_COLOR + "Select GymOwnerId to reject request - " + RESET_COLOR);
                    int rejectGymOwnerId = scanner.nextInt();
                    gymOwnerDAO.rejectGymOwner(String.valueOf(rejectGymOwnerId));
                    break;
                case 6:
                    System.out.println(GREEN_COLOR + "Logged out successfully." + RESET_COLOR);
                    return;
                case 7:
                    return;
                default:
                    System.out.println(RED_COLOR + "Please select an appropriate option from the menu." + RESET_COLOR);
                    break;
            }
        }
    }
}
