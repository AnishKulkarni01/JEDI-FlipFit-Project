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

    public String getIdFromName(String username){
        String gymOwnerId = "";

        try {
            Connection conn = Utils.connect();
            PreparedStatement stmt = conn.prepareStatement(GET_GYM_OWNER_BY_USERNAME);

            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();
            rs.next();

            gymOwnerId = rs.getString("gymOwnerId");

            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gymOwnerId;
    }
}