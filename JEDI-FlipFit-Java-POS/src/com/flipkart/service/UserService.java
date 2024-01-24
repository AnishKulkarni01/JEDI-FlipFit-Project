package com.flipkart.service;

import com.flipkart.dao.CustomerDAO;
import com.flipkart.dao.UserDAO;

public class UserService {
    UserDAO ud= UserDAO.getInstance();

    public boolean authenticate(String username,String password)
    {
        if(ud.check(username,password))return true;
        return false;
    }

}
