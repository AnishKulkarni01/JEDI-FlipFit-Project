package com.flipkart.service;

import com.flipkart.dao.GymOwnerDAO;
import com.flipkart.dao.UserDAO;

import java.util.List;

public class GymOwnerService implements GymOwnerServiceInterface {
    GymOwnerDAO gymOwnerDAO = GymOwnerDAO.getInstance();
    UserDAO userDAO = UserDAO.getInstance();

    public void register(String username, String password, String email, String contact) {
        gymOwnerDAO.registerGymOwner(username, password,email,contact);
        userDAO.addUser(username, password, "GYM_OWNER");
    }
    public void viewProfile() {
        List<String> userDetails = userDAO.getCurrentUser();
        System.out.println("Username : " + userDetails.get(0) + "\nRole : " + userDetails.get(1)+"\nPassword : "+userDetails.get(2)+"\nContact : "+userDetails.get(3)+"\nEmail : "+userDetails.get(4));
    }
    public void updateGymOwnerDetails(String updatedVal,String attr,String customerId) {
        gymOwnerDAO.updateGymOwnerDetails(updatedVal, attr, customerId);
    }
}
