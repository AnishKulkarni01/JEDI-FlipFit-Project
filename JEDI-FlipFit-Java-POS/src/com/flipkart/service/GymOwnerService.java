package com.flipkart.service;

import com.flipkart.dao.GymOwnerDAO;
import com.flipkart.dao.UserDAO;

import java.util.List;

import static com.flipkart.constants.Constants.PURPLE_COLOR;
import static com.flipkart.constants.Constants.RESET_COLOR;

public class GymOwnerService implements GymOwnerServiceInterface {
    GymOwnerDAO gymOwnerDAO = GymOwnerDAO.getInstance();
    UserDAO userDAO = UserDAO.getInstance();

    public void register(String username, String password, String email, String contact) {
        gymOwnerDAO.registerGymOwner(username, password,email,contact);
        userDAO.addUser(username, password, "GYM_OWNER");
    }
    public void viewProfile() {
        List<String> userDetails = userDAO.getCurrentUser();
        System.out.println(PURPLE_COLOR + "Username : " + RESET_COLOR + userDetails.get(0) + "\n" + PURPLE_COLOR + "Role : " + RESET_COLOR + userDetails.get(1) + "\n" + PURPLE_COLOR + "Password : " + RESET_COLOR + userDetails.get(2) + "\n" + PURPLE_COLOR +"Contact : " + RESET_COLOR + userDetails.get(3) + "\n" + PURPLE_COLOR + "Email : " + RESET_COLOR + userDetails.get(4) + "\n");
    }
    public void updateGymOwnerDetails(String updatedVal,String attr,String customerId) {
        gymOwnerDAO.updateGymOwnerDetails(updatedVal, attr, customerId);
    }
}
