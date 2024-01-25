package com.flipkart.service;

public interface GymOwnerServiceInterface {
    /**
     * Adds a new Gym approval request
     * @param gymName Name of gym
     * @param location Location of gym
     * @param capacity Capacity of gym
     */
    void addGymRequest(String gymName, String location, int capacity);

    /**
     * Updates profile of gym owner
     * @param newName new name of gym owner
     * @param newAddress new address of gym owner
     * @param newContact new contact of gym owner
     */
    void updateProfile(String newName, String newAddress, String newContact);

    /**
     * Registers new gym owner
     * @param username Username of gym owner
     * @param password Password of gym owner
     * @param email Email of gym owner
     */
    void register(String username, String password, String email);
}
