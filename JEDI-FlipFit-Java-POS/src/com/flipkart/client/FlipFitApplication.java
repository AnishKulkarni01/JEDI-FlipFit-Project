package com.flipkart.client;

import com.flipkart.dao.*;
import com.flipkart.service.CustomerService;
import com.flipkart.service.GymOwnerService;
import com.flipkart.service.UserService;

import java.util.Scanner;

public class FlipFitApplication {
    public static void main(String[] args) {
        SlotDAO s=SlotDAO.getInstance();
        GymDAO gymDao= GymDAO.getInstance();
        CustomerService customerService = new CustomerService();
        UserService userService = new UserService();
        GymOwnerService gymOwnerService = new GymOwnerService();
        AdminDAO adminDAO=AdminDAO.getInstance();

        adminDAO.registerAdmin("abc","123");

//        s.createSlot("1 Jan", "2", "2");
//        s.createSlot("2 Jan", "4", "2");
//        s.createSlot("3 Jan", "6", "1");
//        s.createSlot("4 Jan", "8", "1");
//        s.createSlot("5 Jan", "9", "3");
//        s.createSlot("5 Jan", "10", "3");
//        gymDao.sendOnboardReq("Gym1", "123", "Marathalli", 10,1) ;
//        gymDao.sendOnboardReq("Gym2", "456", "Bellandur", 10,1) ;
//        gymDao.sendOnboardReq("Gym3", "789", "Whitefield", 10,1) ;

        int loopFlag=0;
        while(loopFlag==0){
            System.out.println("<-----Welcome to FlipFit Application----->");
            System.out.println("Choice Menu");
            System.out.println("1. Login");
            System.out.println("2. Registration of Gym Customer");
            System.out.println("3. Registration of Gym Owner");
            System.out.println("4. Update Password");
            System.out.println("5. Exit");

            Scanner sc = new Scanner(System.in);
            int role, option = sc.nextInt();
            String username, passcode;

            switch(option) {
                case 1:
                    System.out.println("Enter role : \n" +
                            "1. Gym Customer\n" +
                            "2. Gym Owner\n" +
                            "3. GymFlipFit Admin");
                    role = sc.nextInt();
                    String role_str;
                    if(role==1)role_str="GYM_CUSTOMER";
                    else if(role==2)role_str="GYM_OWNER";
                    else role_str="ADMIN";
                    System.out.println("Enter Username : ");
                    username = sc.next();
                    System.out.println("Enter Passcode : ");
                    passcode = sc.next();

                    if(!userService.authenticate(username, passcode,role_str)) {
                        System.out.println("Wrong credentials");
                        break;
                    }
                    else{
                        System.out.println("Successfully logged in");
                    }

                    userService.login(username);



                    switch(role) {
                        case 1:
                            System.out.println("Gym Customer Menu");
                            GymCustomerFlipFitMenu customerMenu = new GymCustomerFlipFitMenu();
                            customerMenu.showCustomerMenu();
                            break;

                        case 2:
                            System.out.println("Gym Owner Menu");
                            GymOwnerFlipFitMenu ownerMenu = new GymOwnerFlipFitMenu();
                            ownerMenu.showGymOwnerFlipMenu();
                            break;

                        case 3:
                            System.out.println("Gym Admin Menu\n");
                            AdminFlipFitMenu adminMenu = new AdminFlipFitMenu();
                            adminMenu.showAdminFlipFitMenu();
                            break;

                        default:
                            throw new IllegalStateException("Unexpected value: " + role);
                    }

                    break;

                case 2:
                    System.out.println("Registering Gym Customer");

                    System.out.println("Enter customer name");
                    username = sc.next();
                    System.out.println("Enter customer passcode");
                    passcode = sc.next();
                    System.out.println("Enter email");
                    String email=sc.next();
                    System.out.println("Enter contact");
                    String contact=sc.next();

                    customerService.register(username, passcode,email,contact);

                    break;

                case 3:
                    System.out.println("Registering Gym Owner");

                    System.out.println("Enter gym owner name");
                    username = sc.next();
                    System.out.println("Enter gym owner passcode");
                    passcode = sc.next();
                    System.out.println("Enter email");
                    String emailO=sc.next();
                    System.out.println("Enter contact");
                    String contactO=sc.next();
                    System.out.println("Enter address");
                    String addressO=sc.next();
                    gymOwnerService.register(username, passcode,emailO,contactO,addressO);

                    break;

                case 4:
                    System.out.println("Updating Password");
                    userService.updatePassword();
                    break;

                case 5:
                    loopFlag=1;
                    break;

                default:
                    throw new IllegalStateException("Unexpected value: " + option);

            }

        }

    }
}