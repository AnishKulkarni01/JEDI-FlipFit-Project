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

    public boolean check(String username,String password,String role){
        return map.containsKey(username) && map.get(username).get(0).equals(password) && map.get(username).get(1).equals(role);
    }

    public void addUser(String username,String password,String role) {
        map.put(username, Arrays.asList(password, role));
    }

    public void setCurrentUser(String username){
        this.currentUsername = username;
    }

    public List<String> getCurrentUser(){
        if(currentUsername == null) {
            System.out.println("Please login to the system");
            return null;
        }
        return Arrays.asList(currentUsername, map.get(currentUsername).get(1));
    }

    public void updatePassword(String newPassword){
        map.put(currentUsername, Arrays.asList(newPassword, map.get(currentUsername).get(1)));
    }
}
