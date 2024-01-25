package com.flipkart.dao;

import com.flipkart.bean.Gym;

/**
 * This interface defines the contract for interacting with Gym entities in the data access layer.
 * Implementing classes, such as GymDAO, must adhere to the methods specified here.
 */
public interface GymDAOInterface {

    /**
     * On-boards a new Gym with the provided details.
     *
     * @param gymName The name of the Gym.
     * @param gstin   The GST identification number of the Gym.
     * @param city    The city where the Gym is located.
     * @param seats   The number of seats in the Gym.
     * @return True if the Gym is successfully on-boarded, false otherwise.
     */
    boolean onBoardGym(String gymName, String gstin, String city, int seats);

    /**
     * Deletes a Gym with the specified Gym ID.
     *
     * @param gymId The ID of the Gym to be deleted.
     * @return True if the Gym is successfully deleted, false if the Gym with the given ID is not found.
     */
    boolean deleteGym(int gymId);
}
