package com.flipkart.client;

import java.util.Scanner;

public class AdminFlipFitMenu {
    public void showAdminFlipFitMenu(){
        int loopFlag=0;
        while(loopFlag==0)
        {
        System.out.println("1. Approve Gym Onboarding Requests.\n" +
                "2. Decline Gym Onboarding Requests\n" +
                "3. Approve Gym Slot Update Requests.\n" +
                "4.Reject Gym Slot Update\n" +
                "5. Log out\n" +
                "6. Back");
        Scanner sc = new Scanner(System.in);
        int action = sc.nextInt();

            switch(action){
                case 1:
                    System.out.println("Approving Onboarding Requests...");
                    break;
                case 2:
                    System.out.println("Rejecting Onboarding Requests...");
                    break;
                case 3:
                    System.out.println("Approving Slot Update Requests...");
                    break;
                case 4:
                    System.out.println("Rejecting Slot Update Requests...");
                    break;
                case 5:
                    System.out.println("Logging Out");
                    loopFlag=1;
                    break;
                case 6:
                    loopFlag=1;
                    break;

                default:
                    throw new IllegalStateException("Unexpected value: " + action);        }
        }

    }
}
