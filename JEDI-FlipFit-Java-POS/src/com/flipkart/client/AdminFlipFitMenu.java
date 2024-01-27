package com.flipkart.client;

import com.flipkart.bean.Gym;
import com.flipkart.dao.*;

import java.util.Scanner;

public class AdminFlipFitMenu {
    GymDAO gymDao= GymDAO.getInstance();
    Scanner scanner = new Scanner(System.in);

    public void showMenuOptions(){
        System.out.println("1. View Pending Requests\n"+
                "2. Approve Gym Onboarding Requests\n" +
                "3. Decline Gym Onboarding Requests\n" +
                "4. Log out\n" +
                "5. Back");
    }

    private void printGymRequests(){
        for(Gym gym: gymDao.viewRequests()){
            System.out.println(gym.toString());
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
                    System.out.println("Logging Out");
                    return;

                case 5:
                    return;

                default:
                    throw new IllegalStateException("Unexpected value: " + action);
            }
        }
    }
}
