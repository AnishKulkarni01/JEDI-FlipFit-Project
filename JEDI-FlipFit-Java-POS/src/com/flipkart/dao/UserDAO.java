package com.flipkart.dao;

import com.flipkart.utils.Utils;

import java.io.PushbackReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import static com.flipkart.constants.Constants.ROLE_GYM_OWNER;
import static com.flipkart.constants.SQLConstants.*;

public class UserDAO {
    static UserDAO userDAO = null;
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private String currentUsername;

    public static synchronized UserDAO getInstance() {
        if(userDAO == null) {
            userDAO = new UserDAO();
        }
        return userDAO;
    }

    public boolean check(String username, String password, String role){
        boolean isRegisteredUser = true;
        GymOwnerDAO gymOwnerDAO = GymOwnerDAO.getInstance();

        if(Objects.equals(role, ROLE_GYM_OWNER) && Objects.equals(gymOwnerDAO.getIdFromName(username).get(1), "false")){
            return false;
        }

        try{
            conn = Utils.connect();
            stmt = conn.prepareStatement(AUTHENTICATE_USER);

            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, role);

            ResultSet rs = stmt.executeQuery();
            if(!rs.next()) isRegisteredUser = false;

            stmt.close();
        } catch(Exception e){
            e.printStackTrace();
        }

        return isRegisteredUser;
    }

    public void addUser(String username, String password, String role) {
        try{
            conn = Utils.connect();
            stmt = conn.prepareStatement(ADD_USER);

            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, role);

            stmt.executeUpdate();
            stmt.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setCurrentUser(String username){
        this.currentUsername = username;
    }

    public List<String> getCurrentUser() {
        if(currentUsername == null) {
            System.out.println("Please login to the system");
            return null;
        } else{
            List<String> userDetailList = new ArrayList<>();

            try{
                conn = Utils.connect();
                stmt = conn.prepareStatement(GET_USER);

                stmt.setString(1, this.currentUsername);

                ResultSet rs = stmt.executeQuery();
                rs.next();
                String role=rs.getString("role");
                userDetailList.add(rs.getString("username"));
                userDetailList.add(rs.getString("role"));
                if(role.equals("GYM_CUSTOMER")) {
                    stmt = conn.prepareStatement(GET_CUSTOMER_BY_USERNAME);
                }
                else if(role.equals("GYM_OWNER"))
                {
                    stmt = conn.prepareStatement(GET_GYM_OWNER_BY_USERNAME);
                }
                stmt.setString(1, this.currentUsername);
                rs=stmt.executeQuery();
                rs.next();
                userDetailList.add(rs.getString("password"));
                userDetailList.add(rs.getString("contact"));
                userDetailList.add(rs.getString("email"));
            } catch(Exception e){
                e.printStackTrace();;
            }

            return userDetailList;
        }
    }

    public void updatePassword(String newPassword){
        try {
            conn = Utils.connect();
            stmt = conn.prepareStatement(UPDATE_USER_PASSWORD);

            stmt.setString(1, newPassword);
            stmt.setString(2, this.currentUsername);

            stmt.executeUpdate();
            stmt.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
