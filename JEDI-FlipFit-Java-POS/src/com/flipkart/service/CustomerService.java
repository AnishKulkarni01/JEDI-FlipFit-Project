package com.flipkart.service;

import com.flipkart.dao.CustomerDAO;
import com.flipkart.dao.UserDAO;

import java.util.List;
import static com.flipkart.constants.Constants.*;

public class CustomerService implements CustomerServiceInterface{
    CustomerDAO customerDAO =CustomerDAO.getInstance();
    UserDAO userDAO = UserDAO.getInstance();

    public void updateCustomerDetails(String updatedVal,String attr,String customerId) {
        customerDAO.updateCustomerDetails(updatedVal, attr, customerId);
    }

    public void register(String name, String password, String email, String contact) {
        customerDAO.registerCustomer(name, password, email, contact);
        userDAO.addUser(name, password, ROLE_GYM_CUSTOMER);
    }

    public void viewProfile() {
        List<String> userDetails = userDAO.getCurrentUser();
        System.out.println("Current user is " + userDetails.get(0) + " as a " + userDetails.get(1));
    }
}
