package com.flipkart.dao;

import java.util.List;

public interface UserDAOInterface {
    /**
     *
     * @param username
     * @param password
     * @return
     */
    public boolean check(String username,String password);
    public void addUser(String username,String password,String role);
    public void setCurrentUser(String username);
    public List<String> getCurrentUser();
}
