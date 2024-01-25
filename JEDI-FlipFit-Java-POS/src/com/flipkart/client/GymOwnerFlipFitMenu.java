package com.flipkart.client;

import java.util.Scanner;

import static com.flipkart.constants.Constants.*;

public class GymOwnerFlipFitMenu {

    public void showGymOwnerFlipMenu()
    {
        int loopFlag=0;
        while(loopFlag==0)
        {
        System.out.println("1. " + YELLOW_COLOR + "Request Gym Onboarding\n" + RESET_COLOR + "2. " + YELLOW_COLOR + "Gym Details Update\n" + RESET_COLOR + "3. " + YELLOW_COLOR + "Request Slot Update\n" + RESET_COLOR + "4. " + YELLOW_COLOR + "Edit Profile\n" + RESET_COLOR + "5. " + YELLOW_COLOR +"Log out\n" + RESET_COLOR + "6. " + YELLOW_COLOR + "Back" + RESET_COLOR);
        Scanner sc=new Scanner(System.in);
        int opt=sc.nextInt();

            switch(opt)
            {
                case 1:
                    System.out.println(BLUE_COLOR + "Request for Gym onboarding sent." + RESET_COLOR);
                    break;
                case 2:
                    System.out.println(GREEN_COLOR + "Gym Details updated." + RESET_COLOR);
                    break;
                case 3:
                    System.out.println(BLUE_COLOR + "Request for Slot update sent." + RESET_COLOR);
                    break;
                case 4:
                    System.out.println(GREEN_COLOR + "Profile Edited successfully." + RESET_COLOR);
                    break;
                case 5:
                    System.out.println(GREEN_COLOR + "Logged Out Successfully." + RESET_COLOR);
                    loopFlag=1;
                    break;
                case 6:
                    loopFlag=1;
                    break;
                default:
                    throw new IllegalStateException(RED_COLOR + "Unexpected value: " + opt + RESET_COLOR);

            }
        }


    }
}
