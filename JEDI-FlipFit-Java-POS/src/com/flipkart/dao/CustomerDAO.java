package com.flipkart.dao;

import com.flipkart.bean.Customer;
import com.flipkart.bean.Slot;

import java.util.ArrayList;
import java.util.List;
import com.flipkart.utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.flipkart.constants.Constants.*;

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

        try {
            Connection conn = Utils.connect();
            PreparedStatement stmt = conn.prepareStatement(ADD_NEW_CUSTOMER);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, email);
            stmt.setString(4, contact);
            stmt.executeUpdate();
            stmt.close();
        }  catch (Exception e) {
            System.out.println(e);
            System.out.println("Oops! An error occurred. Try again later.");
        }
//        customer.setCustomerID(id++);
//        customer.setName(username);
//        customer.setContact(contact);
//        customer.setEmail(email);
//        customer.setPassword(password);
//        customerList.add(customer); //first check if in list
        userDao.addUser(username,password,"GYM_CUSTOMER");
        //for(Customer cust : customerList)
        //    System.out.println(cust.getName());
    }

    public Customer getCustomer(int customerId){
        Customer customer = new Customer();
        try {
            Connection conn = Utils.connect();
            PreparedStatement stmt = conn.prepareStatement(GET_CUSTOMER_BY_ID);
            stmt.setString(1, Integer.toString(customerId));
            ResultSet rs = stmt.executeQuery();
            rs.next();
            customer.setEmail(rs.getString("email"));
            customer.setCustomerID(Integer.parseInt(rs.getString("customerId")));
            customer.setPassword(rs.getString("password"));
            customer.setUsername(rs.getString("username"));
            customer.setContact(rs.getString("contact"));

            stmt.close();
        } catch (SQLException exp) {
            exp.printStackTrace();
        }

        return customer;
//        for(Customer c:customerList)
//        {
//            if(c.getCustomerID()==customerId)
//            {
//                return c;
//            }
//        }
//        System.out.println("Customer DNE");
//        return null;
    }


    public void updateCustomerDetails(String updatedVal,String attr,int customerId)
    {
        try{
            Connection conn = Utils.connect();
            PreparedStatement stmt1 = conn.prepareStatement(UPDATE_CUSTOMER_DETAILS_EMAIL);
            PreparedStatement stmt2 = conn.prepareStatement(UPDATE_CUSTOMER_DETAILS_CONTACT);

            //stmt.setString(1, attr);
            if(attr.equals("email")) {
                stmt1.setString(1, updatedVal);
                stmt1.setString(2, Integer.toString(customerId));
                stmt1.executeUpdate();
                stmt1.close();

            }
            else if(attr.equals("contact")){
                stmt2.setString(1, updatedVal);
                stmt2.setString(2, Integer.toString(customerId));
                stmt2.executeUpdate();
                stmt2.close();
            }

        }catch (SQLException e)
        {
            System.out.println(e);
        }
//        for(Customer c:customerList)
//        {
//            if(c.getCustomerID()==customerId)
//            {
//                if(attr.equals("email"))
//                {
//                    c.setEmail(updatedVal);
//                };
//                if(attr.equals("contact")){
//                    c.setContact(updatedVal);
//                };
//            }
//        }
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
        int ans=0;
        try {
            Connection conn = Utils.connect();
            PreparedStatement stmt = conn.prepareStatement(GET_CUSTOMER_BY_USERNAME);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            rs.next();

            ans= Integer.parseInt(rs.getString("customerId"));
            stmt.close();
        } catch (SQLException exp) {
            exp.printStackTrace();
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return ans;

//        for(Customer cust : customerList){
//            if(cust.getName().equals(username)) return cust.getCustomerID();
//        }
//
//        return -1;
    }
}
