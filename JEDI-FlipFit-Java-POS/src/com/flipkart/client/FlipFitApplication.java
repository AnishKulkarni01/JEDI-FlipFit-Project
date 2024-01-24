package com.flipkart.client;

import java.sql.SQLOutput;
import java.util.Scanner;

public class FlipFitApplication {
    public static void main(String[] args)
    {
        int fl=0;
        while(fl==0)
        {
        System.out.println("<-----Welcome to FlipFit Application----->");
        System.out.println("Choice Menu");
        System.out.println("1. Login");
        System.out.println("2. Registration of Gym Customer");
        System.out.println("3. Registration of Gym Owner");
        System.out.println("4. Update Password");
        System.out.println("5. Exit");

            Scanner sc = new Scanner(System.in);
            int opt = sc.nextInt();

            String username;
            String passcode;
            int role;

            switch(opt)
            {
                case 1:
                    System.out.println("Enter Username : ");
                    username = sc.next();
                    System.out.println("Enter Passcode : ");
                    passcode = sc.next();
                    //int flag=0;
                    //while(flag==0)
                {
                    System.out.println("Enter role : \n" +
                            "1. Gym Customer\n" +
                            "2. Gym Owner\n" +
                            "3. GymFlipFit Admin");
                    role=sc.nextInt();

                    switch(role)
                    {
                        case(1):
                            System.out.println("Gym Customer Menu");
                            GymCustomerFlipFitMenu customerMenu=new GymCustomerFlipFitMenu();
                            customerMenu.showCustomerMenu();
                            break;
                        case(2):
                            System.out.println("Gym Owner Menu");
                            GymOwnerFlipFitMenu ownerMenu=new GymOwnerFlipFitMenu();
                            ownerMenu.showGymOwnerFlipMenu();
                            break;
                        case(3):
                            System.out.println("Gym Admin Menu\n");
                            AdminFlipFitMenu adminMenu = new AdminFlipFitMenu();
                            adminMenu.showAdminFlipFitMenu();
                            break;
                        //case(4):
                        //flag=1;
                        //break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + role);
                    }
                }


                break;
                case 2:
                    System.out.println("Registering Gym Customer");
                    break;
                case 3:
                    System.out.println("Registering Gym Owner");
                    break;
                case 4:
                    System.out.println("Updating Password");
                    break;
                case 5:
                    fl=1;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + opt);

            }

        }

    }
}