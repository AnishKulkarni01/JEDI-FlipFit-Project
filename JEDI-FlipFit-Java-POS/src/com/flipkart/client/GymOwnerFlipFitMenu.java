package com.flipkart.client;

import java.util.Scanner;

public class GymOwnerFlipFitMenu {
    public void showGymOwnerFlipMenu()
    {
        int loopFlag=0;
        while(loopFlag==0)
        {
        System.out.println("1. Request Gym Onboarding\n" +
                "2. Gym Details Update\n" +
                "3. Request Slot Update\n" +
                "4. Edit Profile\n" +
                "5. Log out\n" +
                "6. Back");
        Scanner sc=new Scanner(System.in);
        int opt=sc.nextInt();

            switch(opt)
            {
                case 1:
                    System.out.println("Request for Gym onboarding sent");
                    break;
                case 2:
                    System.out.println("Gym Details updated");
                    break;
                case 3:
                    System.out.println("Request for Slot update sent");
                    break;
                case 4:
                    System.out.println("Profile Edited");
                    break;
                case 5:
                    System.out.println("Logging out");
                    loopFlag=1;
                    break;
                case 6:
                    loopFlag=1;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + opt);

            }
        }


    }
}
