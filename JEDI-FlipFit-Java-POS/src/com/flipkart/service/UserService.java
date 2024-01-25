package com.flipkart.service;

import com.flipkart.dao.CustomerDAO;
import com.flipkart.dao.UserDAO;

public class UserService implements UserServiceInterface {
    UserDAO userDAO= UserDAO.getInstance();

    @Override
    public boolean authenticate(String username, String password) {
        return userDAO.check(username, password);
    }

    @Override
    public void login(String username){
        userDAO.setCurrentUser(username);
    }
}
