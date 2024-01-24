package com.flipkart.client;

import java.util.Scanner;

public class AdminFlipFitMenu {
    public void showAdminFlipFitMenu(){
        System.out.println("1. Approve Gym Onboarding Requests. \n2. Approve Gym Slot Update Requests.");

        Scanner sc = new Scanner(System.in);
        int action = sc.nextInt();

        switch(action){
            case 1:
                System.out.println("Approving Onboarding Requests...");
                break;

            case 2:
                System.out.println("Approving Slot Update Requests...");
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + action);        }
    }
}
