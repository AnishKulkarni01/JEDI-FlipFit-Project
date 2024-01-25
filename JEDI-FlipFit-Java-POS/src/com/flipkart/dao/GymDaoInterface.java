package com.flipkart.dao;

public interface GymDaoInterface {
    /**
     *
     * @param gymName
     * @param gstin
     * @param city
     * @param seats
     * @return
     */
    public boolean onBoardGym(String gymName, String gstin, String city, int seats);
    public boolean deleteGym(int gymId);
}
