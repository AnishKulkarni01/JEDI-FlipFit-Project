package com.flipkart.service.impl;

import com.flipkart.dao.UserDAO;
import com.flipkart.service.UserServiceInterface;

import java.util.List;
import java.util.Scanner;

import static com.flipkart.constants.Constants.*;

public class UserServiceImpl implements UserServiceInterface {
    UserDAO userDAO = UserDAO.getInstance();
    Scanner scanner = new Scanner(System.in);

    public boolean authenticate(String username, String password,String role) {
        return userDAO.check(username, password,role);
    }

    public void login(String username){
        userDAO.setCurrentUser(username);
    }

    public String getCurrentUsername(){
        return userDAO.getCurrentUser().get(0);
    }

    public void updatePassword(){
        List<String> currentUserDetails = userDAO.getCurrentUser();

        if(currentUserDetails == null){
            return;
        }

        String newPassword, newPasswordConfirmation;

        System.out.println(BLUE_COLOR + "Updating Password" + RESET_COLOR);
        System.out.println("Enter the new password");
        newPassword = scanner.nextLine();

        System.out.println("Confirm the new password");
        newPasswordConfirmation = scanner.nextLine();

        while(true){
            if(newPassword.equals(newPasswordConfirmation)){
                userDAO.updatePassword(newPassword);
                System.out.println(GREEN_COLOR + "Password has been successfully updated for the user " + currentUserDetails.get(0) + RESET_COLOR);

                return;
            } else {
                System.out.println(RED_COLOR + "Password didn't match" + RESET_COLOR);
                System.out.println("Enter the new password");
                newPassword = scanner.nextLine();
                
                System.out.println("Confirm the new password");
                newPasswordConfirmation = scanner.nextLine();
            }
        }
    }
}
