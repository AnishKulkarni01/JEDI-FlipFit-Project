package com.flipkart.client;

import com.flipkart.dao.*;
import com.flipkart.service.CustomerService;
import com.flipkart.service.GymOwnerService;
import com.flipkart.service.UserService;

import java.util.Scanner;

import static com.flipkart.constants.Constants.*;

public class FlipFitApplication {

    private static final CustomerService customerService = new CustomerService();
    private static final UserService userService = new UserService();
    private static final GymOwnerService gymOwnerService = new GymOwnerService();
    private static final Scanner scanner = new Scanner(System.in);

    private static void showMenuOptions(){
        System.out.println("<-----Welcome to FlipFit Application----->");
        System.out.println("Choice Menu");
        System.out.println("1. Login");
        System.out.println("2. Registration of Gym Customer");
        System.out.println("3. Registration of Gym Owner");
        System.out.println("4. Update Password");
        System.out.println("5. Exit");
    }

    private static String chooseRole(){
        System.out.println("Enter role : \n" +
                "1. Gym Customer\n" +
                "2. Gym Owner\n" +
                "3. GymFlipFit Admin");

        int role;
        role = scanner.nextInt();

        switch(role){
            case 1:
                return ROLE_GYM_CUSTOMER;

            case 2:
                return ROLE_GYM_OWNER;

            case 3:
                return ROLE_ADMIN;

            default:
                System.out.println("Please select a valid role.");
                return chooseRole();
        }
    }

    private static void showClientMenu(String role){
        switch(role) {
            case ROLE_GYM_CUSTOMER:
                System.out.println("Gym Customer Menu");
                GymCustomerFlipFitMenu customerMenu = new GymCustomerFlipFitMenu();
                customerMenu.showCustomerMenu();
                break;

            case ROLE_GYM_OWNER:
                System.out.println("Gym Owner Menu");
                GymOwnerFlipFitMenu ownerMenu = new GymOwnerFlipFitMenu();
                ownerMenu.showGymOwnerFlipMenu();
                break;

            case ROLE_ADMIN:
                System.out.println("Gym Admin Menu\n");
                AdminFlipFitMenu adminMenu = new AdminFlipFitMenu();
                adminMenu.showAdminFlipFitMenu();
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + role);
        }
    }

    private static void registerGymCustomer(){
        String username, passcode, email, contact;
        System.out.println("Registering Gym Customer.");

        System.out.println("Enter customer name:");
        username = scanner.next();
        System.out.println("Enter customer passcode:");
        passcode = scanner.next();
        System.out.println("Enter email:");
        email = scanner.next();
        System.out.println("Enter contact:");
        contact = scanner.next();

        customerService.register(username, passcode, email, contact);
    }

    private static void registerGymOwner(){
        String username, passcode, email, contact;
        System.out.println("Registering Gym Owner.");

        System.out.println("Enter gym owner name: ");
        username = scanner.next();
        System.out.println("Enter gym owner passcode: ");
        passcode = scanner.next();
        System.out.println("Enter email: ");
        email = scanner.next();
        System.out.println("Enter contact: ");
        contact = scanner.next();

        gymOwnerService.register(username, passcode, email, contact);
    }

    private static void mainPage(){
        while(true){
            showMenuOptions();

            int option = scanner.nextInt();
            String username, passcode, role;

            switch(option) {
                case 1:
                    role = chooseRole();

                    System.out.println("Enter Username : ");
                    username = scanner.next();
                    System.out.println("Enter Passcode : ");
                    passcode = scanner.next();

                    if (!userService.authenticate(username, passcode, role)) {
                        System.out.println("Wrong credentials");
                        break;
                    } else {
                        System.out.println("Successfully logged in");
                    }

                    userService.login(username);
                    showClientMenu(role);

                    break;

                case 2:
                    registerGymCustomer();
                    break;

                case 3:
                    registerGymOwner();
                    break;

                case 4:
                    userService.updatePassword();
                    break;

                case 5:
                    return;

                default:
                    throw new IllegalStateException("Unexpected value: " + option);
            }
        }
    }

    public static void main(String[] args) {
        //Ideally remove this
        AdminDAO adminDAO = AdminDAO.getInstance();
        //adminDAO.registerAdmin("abc","123");

        mainPage();
    }
}