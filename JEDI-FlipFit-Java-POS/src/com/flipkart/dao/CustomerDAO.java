package com.flipkart.dao;

import com.flipkart.bean.Customer;
import com.flipkart.bean.User;

import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    static CustomerDAO custDao=null;
     UserDAO userDao=UserDAO.getInstance();

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
        userDao.addUser(username,password,"customer");
        for(Customer c:customerList)
        System.out.println(c.getName());
        return true;
    }

    public Customer getCustomer(int customerId){
        for(Customer c:customerList)
        {
            if(c.getCustomerID()==customerId)
            {
                return c;
            }
        }
        System.out.println("Customer DNE");
        return null;
    }

    public boolean updateCustomerDetails(int customerId,String password){
        for(Customer c:customerList)
        {
            if(c.getCustomerID()==customerId)
            {
                c.setPassword(password);
                return true;
            }
        }
        return false;
    }

    public boolean deleteCustomer(int customerId){
        for(int i=0;i<customerList.size();i++)
        {
            if(customerList.get(i).getCustomerID()==customerId)
            {
                customerList.remove(i);
                return true;
            }
        }
        System.out.println("Customer DNE");
        return false;
    }
}
