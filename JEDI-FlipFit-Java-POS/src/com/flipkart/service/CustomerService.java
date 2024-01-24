
package com.flipkart.service;

import com.flipkart.bean.Slot;
import com.flipkart.dao.CustomerDAO;

import java.util.List;

public class CustomerService {
    //book slot
    CustomerDAO cd=CustomerDAO.getInstance();

//    public static void main(String[] args)
//    {
//        CustomerDAO cd1=CustomerDAO.getInstance();
//        System.out.println(cd.hashCode());
//        System.out.println(cd1.hashCode());
//    }

    public boolean bookSlot()
    {
        return false;
    }
    //view slot
     public List<Slot> viewSlots()
     {
         List<Slot>arr = null;
         return arr;
     }
    //get added to wait list
    public boolean joinWaitList()
    {
        return false;
    }
    //edit profile
    public void editProfile()
    {

    }
    //register
    public void register(String name,String password)
    {
        cd.registerCustomer(name,password);
    }
    public void viewProfile(String name,String password)
    {

    }
}
