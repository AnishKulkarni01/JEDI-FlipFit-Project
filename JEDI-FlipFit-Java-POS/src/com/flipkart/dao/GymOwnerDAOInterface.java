package com.flipkart.dao;

import com.flipkart.bean.GymOwner;

public interface GymOwnerDAOInterface {
    /**
     *
     * @param name
     * @param password
     * @param email
     * @param contact
     * @return
     */
    public boolean registerGymOwner(String name, String password, String email, String contact);
    public GymOwner getGymOwner(String gymOwnerId);
    public boolean updateGymOwnerDetails(String gymOwnerId, String newName, String newPassword, String newEmail, String newContact);
    public boolean deleteGymOwner(String gymOwnerId);
}
