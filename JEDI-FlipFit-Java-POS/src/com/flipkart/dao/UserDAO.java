package com.flipkart.dao;

import com.flipkart.bean.Customer;
import com.flipkart.bean.User;

import java.util.*;

public class UserDAO {
    static UserDAO userDao = null;

    public static synchronized UserDAO getInstance() {
        if(userDao == null) userDao = new UserDAO();
        return userDao;
    }

    Map<String, List<String>> map = new HashMap<>();
    private String currentUsername;

    public boolean check(String username,String password){
        return map.containsKey(username) && map.get(username).get(0).equals(password);
    }

    public void addUser(String username,String password,String role) {
        map.put(username, Arrays.asList(password, role));
    }

    public void setCurrentUser(String username){
        this.currentUsername = username;
    }

    public List<String> getCurrentUser(){
        return Arrays.asList(this.currentUsername, map.get(this.currentUsername).get(1));
    }
}
