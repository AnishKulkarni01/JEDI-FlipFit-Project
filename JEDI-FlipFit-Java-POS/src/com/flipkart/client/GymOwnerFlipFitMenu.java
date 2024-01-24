package com.flipkart.client;

import java.util.Scanner;

public class GymOwnerFlipFitMenu {
    public void showGymOwnerFlipMenu()
    {
        int fl=0;
        while(fl==0)
        {
        System.out.println("1. Request Gym Onboarding\n2. Gym Details Update\n3. Request Slot Update\n4. Edit Profile\n5. Log out\n6. Back");
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
                    break;
                case 6:
                    fl=1;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + opt);

            }
        }


    }
}
