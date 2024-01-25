
package com.flipkart.service;

import com.flipkart.bean.Slot;
import com.flipkart.dao.CustomerDAO;
import com.flipkart.dao.UserDAO;

import java.util.ArrayList;
import java.util.List;

public class CustomerService implements CustomerServiceInterface {
    CustomerDAO customerDAO =CustomerDAO.getInstance();
    UserDAO userDAO = UserDAO.getInstance();

    @Override
    public boolean bookSlot() {
        return false;
    }

    //view slot
     @Override
     public List<Slot> viewSlots() {
         return new ArrayList<>();
     }

    //get added to wait list
    @Override
    public boolean joinWaitList() {
        return false;
    }

    //edit profile
    @Override
    public void editProfile(int customerId) {
        return;
    }

    //register
    @Override
    public void register(String name, String password) {
        customerDAO.registerCustomer(name, password);
    }

    @Override
    public void viewProfile() {
        List<String> userDetails = userDAO.getCurrentUser();
        System.out.println("Current user is " + userDetails.get(0) + " as a " + userDetails.get(1));
    }
}
