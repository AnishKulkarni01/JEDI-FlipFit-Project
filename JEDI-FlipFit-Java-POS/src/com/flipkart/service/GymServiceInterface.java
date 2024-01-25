package com.flipkart.service;

import com.flipkart.bean.Gym;

public interface GymServiceInterface {
    /**
     * Modifies gym details
     * @param gym Gym
     * @param newName new name of the gym
     * @param newCity new City of the gym
     * @param newGstin new Gstin of the gym
     * @param newSeats new Seats of the gym
     */
    void modifyGymDetails(Gym gym, String newName, String newCity, String newGstin, int newSeats);
}
