package com.flipkart.dao;

import com.flipkart.bean.Customer;
import com.flipkart.bean.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDAO {
    static UserDAO userDao=null;
     Map<String, List<String>> map = new HashMap<>();
    User currUser=new User();
    public static synchronized UserDAO getInstance()
    {
        if(userDao==null)
        {
            userDao=new UserDAO();
        }
        return userDao;
    }
    public boolean check(String username,String password){
        System.out.println("Map size"+map.size());
        System.out.println(map.containsKey(username));
        System.out.println(map.get(username));
        if(map.containsKey(username) && map.get(username).get(0).equals(password))
        {
            return true;
        }
        return false;
    }
    public void addUser(String username,String password,String role)
    {
        List<String> l=new ArrayList<>();
        l.add(password);
        l.add(role);
        map.put(username,l);
        System.out.println(map.containsKey(username));

    }



}
