package com.flipkart.service;

import com.flipkart.dao.GymOwnerDAO;
import com.flipkart.dao.UserDAO;

public class GymOwnerService implements GymOwnerServiceInterface {
    GymOwnerDAO gymOwnerDAO = GymOwnerDAO.getInstance();
    UserDAO userDAO = UserDAO.getInstance();

    public void register(String username, String password, String email, String contact) {
        gymOwnerDAO.registerGymOwner(username, password,email,contact);
        userDAO.addUser(username, password, "GYM_OWNER");
    }
}
