package com.flipkart.dao;

import com.flipkart.bean.GymOwner;
import com.flipkart.utils.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.flipkart.constants.SQLConstants.*;

public class GymOwnerDAO {
    private static GymOwnerDAO gymOwnerDAO = null;

    public static synchronized GymOwnerDAO getInstance() {
        if (gymOwnerDAO == null) {
            gymOwnerDAO = new GymOwnerDAO();
        }
        return gymOwnerDAO;
    }

    public void registerGymOwner(String name, String password,String email,String contact) {
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
            e.printStackTrace();
        }
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
            gymOwner.setGymOwnerId(rs.getString("gymOwnerId"));
            gymOwner.setPassword(rs.getString("password"));
            gymOwner.setName(rs.getString("username"));
            gymOwner.setContact(rs.getString("contact"));

            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return gymOwner;
    }

    public void updateGymOwnerDetails(String newValue, String updateColumn, String gymOwnerId) {
        try{
            Connection conn = Utils.connect();

            if(updateColumn.equals("email")) {
                PreparedStatement stmt1 = conn.prepareStatement(UPDATE_GYM_OWNER_DETAILS_EMAIL);

                stmt1.setString(1, newValue);
                stmt1.setString(2, gymOwnerId);

                stmt1.executeUpdate();
                stmt1.close();

            } else if(updateColumn.equals("contact")){
                PreparedStatement stmt2 = conn.prepareStatement(UPDATE_GYM_OWNER_DETAILS_CONTACT);

                stmt2.setString(1, newValue);
                stmt2.setString(2, gymOwnerId);

                stmt2.executeUpdate();
                stmt2.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> getIdFromName(String username){
        List<String> gymOwnerDetails = new ArrayList<>();

        try {
            Connection conn = Utils.connect();
            PreparedStatement stmt = conn.prepareStatement(GET_GYM_OWNER_BY_USERNAME);

            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();
            rs.next();

            gymOwnerDetails.add(rs.getString("gymOwnerId"));
            gymOwnerDetails.add(rs.getString("isApproved"));

            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return gymOwnerDetails;
    }

    public List<GymOwner> getPendingGymOwners(){
        List<GymOwner> gymOwnerList = new ArrayList<>();

        try{
            System.out.println("Fetching gym owner requests..");

            Connection conn = Utils.connect();
            PreparedStatement stmt = conn.prepareStatement(FETCH_ALL_PENDING_GYM_OWNERS_QUERY);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                GymOwner gymOwner = new GymOwner();

                gymOwner.setGymOwnerId(rs.getString("gymOwnerId"));
                gymOwner.setName(rs.getString("username"));
                gymOwner.setContact(rs.getString("contact"));
                gymOwner.setEmail(rs.getString("email"));

                gymOwnerList.add(gymOwner);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return gymOwnerList;
    }

    public void approveGymOwner(String gymOwnerId){
        try{
            System.out.println("Approving...");

            Connection conn = Utils.connect();
            PreparedStatement stmt = conn.prepareStatement(SQL_APPROVE_GYM_OWNER_BY_ID_QUERY);

            stmt.setString(1, gymOwnerId);

            stmt.executeUpdate();
            stmt.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void rejectGymOwner(String gymOwnerId){
        try{
            System.out.println("Rejecting...");

            Connection conn = Utils.connect();
            PreparedStatement stmt = conn.prepareStatement(SQL_REJECT_GYM_OWNER_BY_ID_QUERY);

            stmt.setString(1, gymOwnerId);

            stmt.executeUpdate();
            stmt.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}