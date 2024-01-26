package com.flipkart.dao;

import com.flipkart.bean.Admin;

import java.util.ArrayList;
import java.util.List;

public class AdminDAO {
    static AdminDAO adminDao=null;
    UserDAO userDao=UserDAO.getInstance();

    List<Admin> adminList=new ArrayList<Admin>();
    private int id= 1;
    public static synchronized AdminDAO getInstance()
    {
        if(adminDao==null)
        {
            adminDao=new AdminDAO();
        }
        return adminDao;
    }
    public void registerAdmin(String username, String password){
        Admin ad = new Admin();
        ad.setAdminID(id++);
        ad.setUsername(username);
        ad.setPassword(password);
        adminList.add(ad); //first check if in list
        userDao.addUser(username,password,"ADMIN");

    }
}
