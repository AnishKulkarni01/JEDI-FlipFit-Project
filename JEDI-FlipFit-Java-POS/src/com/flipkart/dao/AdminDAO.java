package com.flipkart.dao;

import com.flipkart.bean.Admin;

import java.util.ArrayList;
import java.util.List;

public class AdminDAO {
    static AdminDAO adminDAO = null;
    UserDAO userDAO = UserDAO.getInstance();
    List<Admin> adminList=new ArrayList<>();
    private int id = 1;

    public static synchronized AdminDAO getInstance() {
        if(adminDAO == null) {
            adminDAO =new AdminDAO();
        }

        return adminDAO;
    }

    /**
     *
     * @param username
     * @param password
     */
    public void registerAdmin(String username, String password){
        Admin ad = new Admin();

        ad.setAdminID(id++);
        ad.setUsername(username);
        ad.setPassword(password);

        adminList.add(ad); //first check if in list
        userDAO.addUser(username,password,"ADMIN");
    }
}
