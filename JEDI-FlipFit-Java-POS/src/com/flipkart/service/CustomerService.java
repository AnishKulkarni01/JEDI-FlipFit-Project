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
        System.out.println(PURPLE_COLOR + "Username : " + RESET_COLOR + userDetails.get(0) + "\n" + PURPLE_COLOR + "Role : " + RESET_COLOR + userDetails.get(1) + "\n" + PURPLE_COLOR + "Password : " + RESET_COLOR + userDetails.get(2) + "\n" + PURPLE_COLOR +"Contact : " + RESET_COLOR + userDetails.get(3) + "\n" + PURPLE_COLOR + "Email : " + RESET_COLOR + userDetails.get(4) + "\n");
    }
}
