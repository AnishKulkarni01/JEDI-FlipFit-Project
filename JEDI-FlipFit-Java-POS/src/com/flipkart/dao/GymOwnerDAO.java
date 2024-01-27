package com.flipkart.dao;

import com.flipkart.bean.GymOwner;
import com.flipkart.utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.flipkart.constants.Constants.*;

public class GymOwnerDAO {
    private static GymOwnerDAO gymOwnerDAO = null;
    private final List<GymOwner> gymOwnerList = new ArrayList<>();
    private int id = 1; // Starting ID for GymOwners

    private GymOwnerDAO() {
        // Private constructor to restrict instantiation
    }

    public static synchronized GymOwnerDAO getInstance() {
        if (gymOwnerDAO == null) {
            gymOwnerDAO = new GymOwnerDAO();
        }
        return gymOwnerDAO;
    }

    public boolean registerGymOwner(String name, String password,String email,String contact) {

        //GymOwner gymOwner = new GymOwner();

        try {
            Connection conn = Utils.connect();
            PreparedStatement stmt = conn.prepareStatement(ADD_NEW_GYM_OWNER);
            stmt.setString(1, name);
            stmt.setString(2, password);
            stmt.setString(3, email);
            stmt.setString(4, contact);
            stmt.executeUpdate();
            stmt.close();
        }  catch (Exception e) {
            System.out.println(e);
            System.out.println("Oops! An error occurred. Try again later.");
        }
//        GymOwner gymOwner = new GymOwner();
//        gymOwner.setGymOwnerId(id++);
//        gymOwner.setName(name);
//        gymOwner.setPassword(password);
//        gymOwner.setContact(contact);
//        gymOwner.setEmail(email);
//        gymOwnerList.add(gymOwner);
//        for (GymOwner owner : gymOwnerList) {
//            System.out.println(owner.getName());
//        }

        UserDAO userDAO = UserDAO.getInstance();
        userDAO.addUser(name, password, "GYM_OWNER");
        return true;
    }

    public GymOwner getGymOwner(String gymOwnerId) {
        GymOwner gymOwner = new GymOwner();
        try {
            Connection conn = Utils.connect();
            PreparedStatement stmt = conn.prepareStatement(GET_GYM_OWNER_BY_ID);
            stmt.setString(1, (gymOwnerId));
            ResultSet rs = stmt.executeQuery();
            rs.next();
            gymOwner.setEmail(rs.getString("email"));
            gymOwner.setGymOwnerId(Integer.parseInt(rs.getString("gymOwnerId")));
            gymOwner.setPassword(rs.getString("password"));
            gymOwner.setName(rs.getString("username"));
            gymOwner.setContact(rs.getString("contact"));

            stmt.close();
        } catch (SQLException exp) {
            exp.printStackTrace();
        }

        return gymOwner;
//        for (GymOwner owner : gymOwnerList) {
//            if (String.valueOf(owner.getGymOwnerId()).equals(gymOwnerId)) {
//                return owner;
//            }
//        }
//        return null; // Return null if GymOwner with given ID is not found
    }

    public void updateGymOwnerDetails(String updatedVal,String attr,int gymOwnerId)
    {
        try{
            Connection conn = Utils.connect();
            PreparedStatement stmt1 = conn.prepareStatement(UPDATE_GYM_OWNER_DETAILS_EMAIL);

            PreparedStatement stmt2 = conn.prepareStatement(UPDATE_GYM_OWNER_DETAILS_CONTACT);


            //stmt.setString(1, attr);
            if(attr.equals("email")) {
                stmt1.setString(1, updatedVal);
                stmt1.setString(2, Integer.toString(gymOwnerId));
                stmt1.executeUpdate();
                stmt1.close();

            }
            else if(attr.equals("contact")){
                stmt2.setString(1, updatedVal);
                stmt2.setString(2, Integer.toString(gymOwnerId));
                stmt2.executeUpdate();
                stmt2.close();
            }

        }catch (SQLException e)
        {
            System.out.println(e);
        }
//        for(GymOwner c:gymOwnerList)
//        {
//            if(c.getGymOwnerID()==gymOwnerId)
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

    public boolean deleteGymOwner(String gymOwnerId) {
        Iterator<GymOwner> iterator = gymOwnerList.iterator();
        while (iterator.hasNext()) {
            GymOwner owner = iterator.next();
            if (String.valueOf(owner.getGymOwnerId()).equals(gymOwnerId)) {
                iterator.remove();
                return true;
            }
        }
        return false; // Return false if GymOwner with given ID is not found
    }
    public int getIdFromName(String username){
        int ans=0;
        try {
            Connection conn = Utils.connect();
            PreparedStatement stmt = conn.prepareStatement(GET_GYM_OWNER_BY_USERNAME);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            rs.next();

            ans= Integer.parseInt(rs.getString("gymOwnerId"));
            stmt.close();
        } catch (SQLException exp) {
            exp.printStackTrace();
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return ans;

//        for(GymOwner cust : gymOwnerList){
//            if(cust.getName().equals(username)) return cust.getGymOwnerId();
//        }
//
//        return -1;
    }
}