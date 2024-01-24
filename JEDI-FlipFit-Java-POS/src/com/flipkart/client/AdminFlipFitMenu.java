package com.flipkart.client;

import java.util.Scanner;

public class AdminFlipFitMenu {
    public void showAdminFlipFitMenu(){
        int fl=0;
        while(fl==0)
        {
        System.out.println("1. Approve Gym Onboarding Requests. \n2. Approve Gym Slot Update Requests.\n3. Log out\n4. Back");
        Scanner sc = new Scanner(System.in);
        int action = sc.nextInt();

            switch(action){
                case 1:
                    System.out.println("Approving Onboarding Requests...");
                    break;

                case 2:
                    System.out.println("Approving Slot Update Requests...");
                    break;
                case 3:
                    System.out.println("Logging Out");
                    fl=1;
                    break;
                case 4:
                    fl=1;
                    break;

                default:
                    throw new IllegalStateException("Unexpected value: " + action);        }
        }

    }
}
