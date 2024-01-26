package com.flipkart.service;

import com.flipkart.dao.GymOwnerDAO;

public class GymOwnerService {

    GymOwnerDAO gymOwnerDAO = GymOwnerDAO.getInstance();
    // Add gym request
    public void addGymRequest(String gymName, String location, int capacity) {
        // Implementation to add a gym request
    }

    // Update profile
    public void updateProfile(String newName, String newAddress, String newContact) {
        // Implementation to update the gym owner's profile
    }

    // Register
    public void register(String username, String password, String email, String contact) {
        // Implementation to register a new gym owner
        gymOwnerDAO.registerGymOwner(username, password,email,contact);
    }
}
