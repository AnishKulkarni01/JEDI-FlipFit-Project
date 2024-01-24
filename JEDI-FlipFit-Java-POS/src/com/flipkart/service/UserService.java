package com.flipkart.service;

import com.flipkart.dao.CustomerDAO;
import com.flipkart.dao.UserDAO;

public class UserService {
    UserDAO userDAO= UserDAO.getInstance();

    public boolean authenticate(String username, String password) {
        return userDAO.check(username, password);
    }

    public void login(String username){
        userDAO.setCurrentUser(username);
    }
}
