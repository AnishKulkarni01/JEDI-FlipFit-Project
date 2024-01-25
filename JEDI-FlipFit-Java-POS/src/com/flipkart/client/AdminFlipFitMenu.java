package com.flipkart.client;

import java.util.Scanner;

import static com.flipkart.constants.Constants.*;

public class AdminFlipFitMenu {
    public void showAdminFlipFitMenu(){
        int loopFlag=0;
        while(loopFlag==0)
        {
            System.out.println("1. " + YELLOW_COLOR + "Approve Gym Onboarding Requests.\n" + RESET_COLOR + "2. " + YELLOW_COLOR + "Decline Gym Onboarding Requests.\n" + RESET_COLOR + "3. " + YELLOW_COLOR + "Approve Gym Slot Update Requests.\n" + RESET_COLOR + "4. " + YELLOW_COLOR + "Reject Gym Slot Update\n" + RESET_COLOR + "5. " + YELLOW_COLOR +"Log Out\n" + RESET_COLOR + "6. " + YELLOW_COLOR + "Back" + RESET_COLOR);
            Scanner sc = new Scanner(System.in);
            int action = sc.nextInt();
            switch(action){
                case 1:
                    System.out.println(GREEN_COLOR + "Approving Onboarding Requests..." + RESET_COLOR);
                    break;
                case 2:
                    System.out.println(RED_COLOR + "Rejecting Onboarding Requests..." + RESET_COLOR);
                    break;
                case 3:
                    System.out.println(GREEN_COLOR + "Approving Slot Update Requests..." + RESET_COLOR);
                    break;
                case 4:
                    System.out.println(RED_COLOR + "Rejecting Slot Update Requests..." + RESET_COLOR);
                    break;
                case 5:
                    System.out.println(GREEN_COLOR + "Logged Out Successfully." + RESET_COLOR);
                    loopFlag=1;
                    break;
                case 6:
                    loopFlag=1;
                    break;

                default:
                    throw new IllegalStateException(RED_COLOR + "Unexpected value: " + action + RESET_COLOR);        }
        }

    }
}
