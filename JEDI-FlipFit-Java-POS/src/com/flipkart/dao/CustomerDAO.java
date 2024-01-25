package com.flipkart.dao;

import com.flipkart.bean.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    static CustomerDAO custDao=null;
     UserDAO userDao=UserDAO.getInstance();

    List<Customer> customerList=new ArrayList<Customer>();
    private int id= 1;
    public static synchronized CustomerDAO getInstance()
    {
        if(custDao==null)
        {
            custDao=new CustomerDAO();
        }
        return custDao;
    }
    public void registerCustomer(String username, String password){
        Customer customer = new Customer();
        customer.setCustomerID(id++);
        customer.setName(username);
        customer.setPassword(password);
        customerList.add(customer); //first check if in list
        userDao.addUser(username,password,"customer");
        for(Customer cust : customerList)
            System.out.println(cust.getName());
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
