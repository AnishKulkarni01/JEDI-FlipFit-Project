package com.flipkart.service;

import com.flipkart.exceptions.CustomerRegistrationFailedException;

public interface CustomerServiceInterface {
    void updateCustomerDetails(String updatedVal,String attr,String customerId);

    void register(String name, String password, String email, String contact);

    void viewProfile();
    String getCustomerIdFromUsername(String username);
}
