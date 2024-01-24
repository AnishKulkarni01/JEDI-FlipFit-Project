package com.flipkart.dao;

import com.flipkart.bean.Customer;

public class CustomerDAO {
    public boolean registerCustomer(String customerId){

        return true;
    }

    public Customer getCustomer(String customerId){

        return new Customer();
    }

    public boolean updateCustomerDetails(String customerId){

        return true;
    }

    public boolean deleteCustomer(String customerId){

        return true;
    }
}
