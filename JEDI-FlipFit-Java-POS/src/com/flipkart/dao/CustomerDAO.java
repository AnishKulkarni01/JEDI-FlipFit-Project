package com.flipkart.dao;

import com.flipkart.bean.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    static CustomerDAO custDao=null;
     List<Customer> customerList=new ArrayList<Customer>();
    int id= customerList.size();
    public static synchronized CustomerDAO getInstance()
    {
        if(custDao==null)
        {
            custDao=new CustomerDAO();
        }
        return custDao;
    }
    public boolean registerCustomer(String username,String password){
        Customer cust=new Customer();
        cust.setCustomerID(id);
        cust.setName(username);
        cust.setPassword(password);
        customerList.add(cust);
        for(Customer c:customerList)
        System.out.println(c.getName());
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
