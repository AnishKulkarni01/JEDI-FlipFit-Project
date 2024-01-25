package com.flipkart.dao;

public interface AdminDaoInterface {
    /**
     *
     * @param adminID
     * @return
     */
    public boolean makeAdmin(String adminID);
    public boolean removeAdmin(String adminID);
}
