package com.flipkart.client;

import com.flipkart.bean.Gym;
import com.flipkart.bean.GymOwner;
import com.flipkart.dao.*;

import java.util.Scanner;

public class AdminFlipFitMenu {
    GymDAO gymDao= GymDAO.getInstance();
    GymOwnerDAO gymOwnerDAO = GymOwnerDAO.getInstance();
    Scanner scanner = new Scanner(System.in);

    public void showMenuOptions(){
        System.out.println("1. View Pending Requests\n"+
                "2. Approve Gym Onboarding Requests\n" +
                "3. Decline Gym Onboarding Requests\n" +
                "4. Approve Gym Owner Requests\n" +
                "5. Decline Gym Owner Requests\n" +
                "6. Log out\n" +
                "7. Back");
    }

    private void printGymRequests(){
        for(Gym gym : gymDao.viewRequests()){
            System.out.println(gym.toString());
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
                    System.out.println("Viewing Requests");
                    printGymRequests();
                    break;

                case 2:
                    System.out.println("Select GymId to Approve Request");
                    printGymRequests();

                    int approvedGymId= scanner.nextInt();
                    gymDao.onBoardGym(String.valueOf(approvedGymId));

                    break;

                case 3:
                    System.out.println("Select GymId to Reject Request");
                    printGymRequests();

                    int rejectGymId= scanner.nextInt();
                    gymDao.deleteGymRequest(String.valueOf(rejectGymId));

                    break;

                case 4:
                    System.out.println("Select GymOwnerId to approve request");
                    printGymOwnerRequests();

                    int approveGymOwnerId = scanner.nextInt();
                    gymOwnerDAO.approveGymOwner(String.valueOf(approveGymOwnerId));

                    break;

                case 5:
                    System.out.println("Select GymOwnerId to approve request");
                    printGymOwnerRequests();

                    int rejectGymOwnerId = scanner.nextInt();
                    gymOwnerDAO.rejectGymOwner(String.valueOf(rejectGymOwnerId));

                    break;

                case 6:
                    System.out.println("Logging Out");
                    return;

                case 7:
                    return;

                default:
                    throw new IllegalStateException("Unexpected value: " + action);
            }
        }
    }
}
