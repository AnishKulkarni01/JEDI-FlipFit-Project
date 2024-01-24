package com.flipkart.client;

import java.sql.SQLOutput;
import java.util.Scanner;

public class FlipFitApplication {
    public static void main(String[] args)
    {
        System.out.println("<-----Welcome to FlipFit Application----->");
        System.out.println("Choice Menu");
        System.out.println("1. Login");
        System.out.println("2. Registration of Gym Customer");
        System.out.println("3. Update Password");
        System.out.println("4. Exit");
        Scanner sc=new Scanner(System.in);
        int opt=sc.nextInt();
        String username;
        String passcode;
        int role;
        switch(opt)
        {
            case 1:
                System.out.println("Enter Username : ");
                username=sc.next();
                System.out.println("Enter Passcode : ");
                passcode=sc.next();
                System.out.println("Enter role : \n1. Gym Customer\n2. Gym Owner\n3. GymFlipFit Admin");
                role=sc.nextInt();
                switch(role)
                {
                    case(1):
                        System.out.println("Gym Customer Menu");
                        break;
                    case(2):
                        System.out.println("Gym Owner Menu");
                        break;
                    case(3):
                        System.out.println("Gym Admin Menu");
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + role);
                }
                break;
            case 2:
            default:
                throw new IllegalStateException("Unexpected value: " + opt);
        }

    }
}
