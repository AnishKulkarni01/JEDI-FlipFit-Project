package com.flipkart.dao;

import com.flipkart.bean.Customer;

public interface CustomerDaoInterface {
    /**
     *
     * @param username
     * @param password
     */
    public void registerCustomer(String username, String password);
    public Customer getCustomer(int customerId);
    public boolean updateCustomerDetails(int customerId,String password);
}
