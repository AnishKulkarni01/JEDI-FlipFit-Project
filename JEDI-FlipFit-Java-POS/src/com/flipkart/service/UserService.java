package com.flipkart.service;

import com.flipkart.dao.CustomerDAO;
import com.flipkart.dao.UserDAO;

import java.util.List;
import java.util.Scanner;

public class UserService implements UserServiceInterface {
    UserDAO userDAO= UserDAO.getInstance();

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

        Scanner in = new Scanner(System.in);
        String newPassword, newPasswordConfirmation;

        System.out.println("Enter the new password");
        newPassword = in.nextLine();
        System.out.println("Confirm the new password");
        newPasswordConfirmation = in.nextLine();

        boolean passwordMatch = false;
        while(!passwordMatch){
            if(newPassword.equals(newPasswordConfirmation)){
                passwordMatch = true;
                userDAO.updatePassword(newPassword);
                System.out.println("Password has been successfully updated for the user " + currentUserDetails.get(0));
            }else{
                System.out.println("Password didn't match");
                System.out.println("Enter the new password");
                newPassword = in.nextLine();
                System.out.println("Confirm the new password");
                newPasswordConfirmation = in.nextLine();
            }
        }
    }
}
