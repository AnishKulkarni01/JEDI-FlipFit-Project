package com.flipkart.client;

import com.flipkart.dao.UserDAO;
import com.flipkart.service.CustomerService;
import com.flipkart.service.GymOwnerService;
import com.flipkart.service.UserService;

import java.util.Scanner;

import static com.flipkart.constants.Constants.*;

public class FlipFitApplication {
    public static void main(String[] args) {
        CustomerService customerService = new CustomerService();
        UserService userService = new UserService();
        GymOwnerService gymOwnerService = new GymOwnerService();

        int loopFlag=0;
        while(loopFlag==0)
        {
            String WELCOME_MESSAGE = GREEN_COLOR + "<-----Welcome to FlipFit Application----->" + RESET_COLOR;
            System.out.println(WELCOME_MESSAGE);
            System.out.println("Choice Menu");
            System.out.println("1. " + GREEN_COLOR  +  "Login" + RESET_COLOR);
            System.out.println("2. " + BLUE_COLOR + "Registration of Gym Customer" + RESET_COLOR);
            System.out.println("3. " + BLUE_COLOR + "Registration of Gym Owner" + RESET_COLOR);
            System.out.println("4. " + BLUE_COLOR + "Update Password" + RESET_COLOR);
            System.out.println("5. " + RED_COLOR + "Exit" + RESET_COLOR);
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
                {
                    System.out.println("Enter role : \n" + "    1. "+ YELLOW_COLOR + "Gym Customer\n" + RESET_COLOR + "    2. " + YELLOW_COLOR + "Gym Owner\n" + RESET_COLOR + "    3. " + YELLOW_COLOR + "GymFlipFit Admin" + RESET_COLOR);
                    role = sc.nextInt();
                    switch(role)
                    {
                        case(1):
                            System.out.println(BLUE_COLOR + "<-Gym Customer Menu->" + RESET_COLOR);
                            GymCustomerFlipFitMenu customerMenu=new GymCustomerFlipFitMenu();
                            customerMenu.showCustomerMenu();
                            break;
                        case(2):
                            System.out.println(BLUE_COLOR + "<-Gym Owner Menu->" + RESET_COLOR);
                            GymOwnerFlipFitMenu ownerMenu=new GymOwnerFlipFitMenu();
                            ownerMenu.showGymOwnerFlipMenu();
                            break;
                        case(3):
                            System.out.println(BLUE_COLOR + "<-Gym Admin Menu->" + RESET_COLOR);
                            AdminFlipFitMenu adminMenu = new AdminFlipFitMenu();
                            adminMenu.showAdminFlipFitMenu();
                            break;
                        default:
                            throw new IllegalStateException(RED_COLOR + "Unexpected value: " + role + RESET_COLOR);
                    }

                    break;

                case 2:
                    System.out.println(GREEN_COLOR + "Registering Gym Customer..." + RESET_COLOR);

                    System.out.println("Enter customer name");
                    username = sc.next();
                    System.out.println("Enter customer passcode");
                    passcode = sc.next();

                    customerService.register(username, passcode);

                    break;

                case 3:
                    System.out.println(GREEN_COLOR + "Registering Gym Owner..." + RESET_COLOR);

                    System.out.println("Enter gym owner name");
                    username = sc.next();
                    System.out.println("Enter gym owner passcode");
                    passcode = sc.next();
                    gymOwnerService.register(username, passcode);

                    break;

                case 4:
                    System.out.println(GREEN_COLOR + "Updating Password..." + RESET_COLOR);
                    break;

                case 5:
                    loopFlag=1;
                    break;

                default:
                    throw new IllegalStateException(RED_COLOR + "Unexpected value: " + opt + RESET_COLOR);
            }

        }

    }
}