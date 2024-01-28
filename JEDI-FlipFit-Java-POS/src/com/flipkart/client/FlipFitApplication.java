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
        String WELCOME_MESSAGE = BOLD + GREEN_COLOR + "<-----Welcome to FlipFit Application----->" + RESET_COLOR;
        System.out.println(WELCOME_MESSAGE);
        System.out.println(PURPLE_COLOR + "<-Choice Menu->" + RESET_COLOR);
        System.out.println("1. " + GREEN_COLOR  +  "Login" + RESET_COLOR);
        System.out.println("2. " + BLUE_COLOR + "Registration of Gym Customer" + RESET_COLOR);
        System.out.println("3. " + BLUE_COLOR + "Registration of Gym Owner" + RESET_COLOR);
        System.out.println("4. " + BLUE_COLOR + "Update Password" + RESET_COLOR);
        System.out.println("5. " + RED_COLOR + "Exit" + RESET_COLOR);
    }

    private static String chooseRole(){
        System.out.println("Choose role : \n" + " 1. "+ YELLOW_COLOR + "Gym Customer\n" + RESET_COLOR + " 2. " + YELLOW_COLOR + "Gym Owner\n" + RESET_COLOR + " 3. " + YELLOW_COLOR + "Gym Admin" + RESET_COLOR);
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
                System.out.println(RED_COLOR + "Please select a valid role." + RESET_COLOR);
                return chooseRole();
        }
    }

    private static void showClientMenu(String role){
        switch(role) {
            case ROLE_GYM_CUSTOMER:
                System.out.println(BLUE_COLOR + "<-Gym Customer Menu->" + RESET_COLOR);
                GymCustomerFlipFitMenu customerMenu = new GymCustomerFlipFitMenu();
                customerMenu.showCustomerMenu();
                break;
            case ROLE_GYM_OWNER:
                System.out.println(BLUE_COLOR + "<-Gym Owner Menu->" + RESET_COLOR);
                GymOwnerFlipFitMenu ownerMenu = new GymOwnerFlipFitMenu();
                ownerMenu.showGymOwnerFlipMenu();
                break;
            case ROLE_ADMIN:
                System.out.println(BLUE_COLOR + "<-Gym Admin Menu->" + RESET_COLOR);
                AdminFlipFitMenu adminMenu = new AdminFlipFitMenu();
                adminMenu.showAdminFlipFitMenu();
                break;
            default:
                throw new IllegalStateException(RED_COLOR + "Unexpected value: " + role + RESET_COLOR);
        }
    }

    private static void registerGymCustomer(){
        String username, passcode, email, contact;
        System.out.println(GREEN_COLOR + "Registering Gym Customer." + RESET_COLOR);

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
        System.out.println(GREEN_COLOR + "Registering Gym Owner." + RESET_COLOR);

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
                        System.out.println(RED_COLOR + "Wrong credentials" + RESET_COLOR);
                        break;
                    } else {
                        System.out.println(GREEN_COLOR + "Successfully logged in" + RESET_COLOR);
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
                    throw new IllegalStateException(RED_COLOR + "Unexpected value: " + option + RESET_COLOR);
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