package com.flipkart.dao;

import com.flipkart.bean.Gym;
import com.flipkart.utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import static com.flipkart.constants.SQLConstants.*;


public class GymDAO {
    List<Gym> reqList = new ArrayList<>();
    private Connection conn = null;
    private PreparedStatement stmt = null;
    static GymDAO gymDAO = null;

    public static synchronized GymDAO getInstance() {
        if (gymDAO == null) {
            gymDAO = new GymDAO();
        }
        return gymDAO;
    }


    public void sendOnboardReq(String name, String gstin, String city, int seats,String gymOwnerId) {
        try{
            conn= Utils.connect();
            stmt = conn.prepareStatement(SEND_GYM_ONBOARD_REQUEST);

            stmt.setString(1, name);
            stmt.setString(2, city);
            stmt.setString(3, gstin);
            stmt.setString(4, Integer.toString(seats));
            stmt.setString(5, gymOwnerId);

            stmt.executeUpdate();
            stmt.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void onBoardGym(String gymId) {
        try {
            conn = Utils.connect();
            stmt = conn.prepareStatement(APPROVE_GYM_BY_ID);

            stmt.setString(1, "true");
            stmt.setString(2, gymId);

            stmt.executeUpdate();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        }
    }

    public void deleteGymRequest(String gymId) {
        for(int i=0;i<reqList.size();i++) {
            if(Objects.equals(reqList.get(i).getGymId(), gymId)) {
                reqList.remove(i);
                return;
            }
        }

    }

    public List<Gym> viewPendingRequests(int gymOwnerId) {
        List<Gym> pendingList = new ArrayList<>();

        try {
            conn = Utils.connect();
            System.out.println("Fetching gym centres..");

            stmt = conn.prepareStatement(FETCH_ALL_PENDING_GYM_REQUESTS_BY_GYMOWNERID);
            stmt.setString(1, Integer.toString(gymOwnerId));

            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                Gym g=new Gym();
                     g.setName(rs.getString("name"));
                        g.setCity(rs.getString("city"));
                        g.setSeats(Integer.parseInt(rs.getString("seats")));
                        g.setGstin(rs.getString("gstin"));
                        g.setGymId(rs.getString("gymId"));
                        g.setIsApproved(rs.getString("isApproved"));
                pendingList.add(g);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pendingList;
    }

    public List<String> getAllAreas() {
        List<String> areas = new ArrayList<>();

        try{
            System.out.println(" fetching cities..");

            conn = Utils.connect();
            stmt = conn.prepareStatement(FETCH_ALL_AREAS);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                areas.add(rs.getString("city"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return areas;
    }

    public Gym getGymById(String gymId) {
        Gym gym = new Gym();

        try {
            conn = Utils.connect();
            System.out.println("Fetching gym centres..");

            stmt = conn.prepareStatement(FETCH_GYMS_BY_ID);
            stmt.setString(1, gymId);

            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                gym.setName(rs.getString("name"));
                gym.setCity(rs.getString("city"));
                gym.setSeats(Integer.parseInt(rs.getString("seats")));
                gym.setGstin(rs.getString("gstin"));
                gym.setGymId(rs.getString("gymId"));
                gym.setIsApproved(rs.getString("isApproved"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return gym;
    }

    public List<Gym> getGymsByArea(String area) {
        List<Gym> gymList = new ArrayList<>();

        try {
            System.out.println("Fetching gym centres..");

            conn = Utils.connect();
            stmt = conn.prepareStatement(FETCH_GYMS_BY_AREA);

            stmt.setString(1, area);

            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                Gym gym = new Gym();
                gym.setName(rs.getString("name"));
                gym.setCity(rs.getString("city"));
                gym.setSeats(Integer.parseInt(rs.getString("seats")));
                gym.setGstin(rs.getString("gstin"));
                gym.setGymId(rs.getString("gymId"));
                gym.setIsApproved(rs.getString("isApproved"));
                gymList.add(gym);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return gymList;
    }

    public List<Gym> getGymsByOwner(String gymOwnerId) {
        List<Gym> gymList = new ArrayList<>();

        try {
            System.out.println("Fetching gym centres..");

            conn = Utils.connect();
            stmt = conn.prepareStatement(FETCH_GYMS_BY_OWNER);

            stmt.setString(1, gymOwnerId);

            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                Gym gym = new Gym();
                gym.setName(rs.getString("name"));
                gym.setCity(rs.getString("city"));
                gym.setSeats(Integer.parseInt(rs.getString("seats")));
                gym.setGstin(rs.getString("gstin"));
                gym.setGymId(rs.getString("gymId"));
                gym.setIsApproved(rs.getString("isApproved"));
                gymList.add(gym);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return gymList;
    }

    public List<Gym> viewRequests() {
        List<Gym> pendingList = new ArrayList<>();

        try {
            System.out.println("Fetching gym centres..");

            conn = Utils.connect();
            stmt = conn.prepareStatement(FETCH_ALL_PENDING_GYM_REQUESTS);

            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                Gym gym=new Gym();
                gym.setName(rs.getString("name"));
                gym.setCity(rs.getString("city"));
                gym.setSeats(Integer.parseInt(rs.getString("seats")));
                gym.setGstin(rs.getString("gstin"));
                gym.setGymId(rs.getString("gymId"));
                gym.setIsApproved(rs.getString("isApproved"));
                pendingList.add(gym);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pendingList;
    }

    public void updateGym(String updatedVal, String attr, String gymId) {
        try{
            Connection conn = Utils.connect();

            if(attr.equals("name")) {
                PreparedStatement stmt1 = conn.prepareStatement(UPDATE_GYM_DETAILS_NAME);

                stmt1.setString(1, updatedVal);
                stmt1.setString(2, gymId);

                stmt1.executeUpdate();
                stmt1.close();
            }
            else if(attr.equals("city")){
                PreparedStatement stmt2 = conn.prepareStatement(UPDATE_GYM_DETAILS_CITY);

                stmt2.setString(1, updatedVal);
                stmt2.setString(2, gymId);

                stmt2.executeUpdate();
                stmt2.close();
            }
            else if(attr.equals("seats")){
                PreparedStatement stmt3 = conn.prepareStatement(UPDATE_GYM_DETAILS_SEATS);

                stmt3.setString(1, updatedVal);
                stmt3.setString(2, gymId);

                stmt3.executeUpdate();
                stmt3.close();
            }
            else if(attr.equals("gstin")){
                PreparedStatement stmt4 = conn.prepareStatement(UPDATE_GYM_DETAILS_GSTIN);

                stmt4.setString(1, updatedVal);
                stmt4.setString(2, gymId);

                stmt4.executeUpdate();
                stmt4.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}