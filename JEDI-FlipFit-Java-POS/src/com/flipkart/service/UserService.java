package com.flipkart.service;

import com.flipkart.dao.UserDAO;

import java.util.List;
import java.util.Scanner;

public class UserService implements UserServiceInterface {
    UserDAO userDAO = UserDAO.getInstance();
    Scanner scanner = new Scanner(System.in);

    public boolean authenticate(String username, String password,String role) {
        return userDAO.check(username, password,role);
    }

    public void login(String username){
        userDAO.setCurrentUser(username);
    }

    public void updatePassword(){
        List<String> currentUserDetails = userDAO.getCurrentUser();

        if(currentUserDetails == null){
            return;
        }

        String newPassword, newPasswordConfirmation;

        System.out.println("Updating Password");
        System.out.println("Enter the new password");
        newPassword = scanner.nextLine();

        System.out.println("Confirm the new password");
        newPasswordConfirmation = scanner.nextLine();

        while(true){
            if(newPassword.equals(newPasswordConfirmation)){
                userDAO.updatePassword(newPassword);
                System.out.println("Password has been successfully updated for the user " + currentUserDetails.get(0));

                return;
            } else {
                System.out.println("Password didn't match");
                System.out.println("Enter the new password");
                newPassword = scanner.nextLine();
                
                System.out.println("Confirm the new password");
                newPasswordConfirmation = scanner.nextLine();
            }
        }
    }
}
