package com.flipkart.client;

import com.flipkart.bean.Gym;
import com.flipkart.bean.GymOwner;
import com.flipkart.dao.*;

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
        for(Gym gym : gymDao.viewRequests()){
            String output = gym.toString();
            System.out.println(output);
            String[] keyValuePairs = output
                    .replace("{", "")
                    .replace("}", "")
                    .split(",\\s*");

            String[] headers = {PURPLE_COLOR + "  GymId  " + RESET_COLOR, PURPLE_COLOR + "  Name   " + RESET_COLOR, PURPLE_COLOR + "  City   " + RESET_COLOR, PURPLE_COLOR + "  GSTIN  " + RESET_COLOR, PURPLE_COLOR + "  Seats   " + RESET_COLOR};
            System.out.println("| " + String.join(" | ", headers) + " |");
            for (String pair : keyValuePairs) {
                String[] entry = pair.split("=");
                String key = entry[0].trim();
                String value = entry[1].trim();
                String formatSpecifier = "%-10s";
                System.out.printf("| " + formatSpecifier, value);
            }
            System.out.println(" |");
        }
    }

    private void printGymOwnerRequests(){
        for(GymOwner gymOwner : gymOwnerDAO.getPendingGymOwners()){
            System.out.println("Gym Owner Id: " + gymOwner.getGymOwnerId() + "\n" +
                    "Gym Owner Name : " + gymOwner.getName() +"\n"+
                    "Gym Owner Contact : " + gymOwner.getContact() + "\n" +
                    "Gym Owner Email:" + gymOwner.getEmail() +"\n" );
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
                    System.out.println(BLUE_COLOR + "Select GymId to Approve Request - " + RESET_COLOR);
                    printGymRequests();

                    int approvedGymId= scanner.nextInt();
                    gymDao.onBoardGym(String.valueOf(approvedGymId));
                    break;
                case 3:
                    System.out.println(BLUE_COLOR + "Select GymId to Reject Request - " + RESET_COLOR);
                    printGymRequests();
                    int rejectGymId= scanner.nextInt();
                    gymDao.deleteGymRequest(String.valueOf(rejectGymId));
                    break;
                case 4:
                    System.out.println(BLUE_COLOR + "Select GymOwnerId to approve request - " + RESET_COLOR);
                    printGymOwnerRequests();
                    int approveGymOwnerId = scanner.nextInt();
                    gymOwnerDAO.approveGymOwner(String.valueOf(approveGymOwnerId));
                    break;
                case 5:
                    System.out.println(BLUE_COLOR + "Select GymOwnerId to reject request - " + RESET_COLOR);
                    printGymOwnerRequests();
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
