package com.flipkart.dao;

import com.flipkart.bean.Customer;
import com.flipkart.bean.Slot;

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
    public void registerCustomer(String username, String password,String email,String contact){
        Customer customer = new Customer();
        customer.setCustomerID(id++);
        customer.setName(username);
        customer.setContact(contact);
        customer.setEmail(email);
        customer.setPassword(password);
        customerList.add(customer); //first check if in list
        userDao.addUser(username,password,"GYM_CUSTOMER");
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


    public void updateCustomerDetails(String updatedVal,String attr,int customerId)
    {
        for(Customer c:customerList)
        {
            if(c.getCustomerID()==customerId)
            {
                if(attr.equals("email"))
                {
                    c.setEmail(updatedVal);
                };
                if(attr.equals("contact")){
                    c.setContact(updatedVal);
                };
            }
        }
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

    public int getIdFromName(String username){
        for(Customer cust : customerList){
            if(cust.getName().equals(username)) return cust.getCustomerID();
        }

        return -1;
    }
}
