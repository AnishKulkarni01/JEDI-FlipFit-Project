package com.flipkart.dao;

import com.flipkart.bean.Customer;
import com.flipkart.bean.Gym;
import com.flipkart.utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import static com.flipkart.constants.Constants.*;


import static com.flipkart.dao.CustomerDAO.custDao;


public class GymDAO {

    static GymDAO gymdao = null;
    List<Gym> gymList = new ArrayList<Gym>();
    List<Gym>reqList=new ArrayList<>();

    private int id = 1;
    private Connection conn = null;
    private PreparedStatement stmt = null;

    public static synchronized GymDAO getInstance() {
        if (gymdao == null) {
            gymdao = new GymDAO();
        }
        return gymdao;
    }


    public boolean sendOnboardReq(String name, String gstin, String city, int seats,int gymOwnerId) {

        try{
             conn= Utils.connect();
             stmt = conn.prepareStatement(SEND_GYM_ONBOARD_REQUEST);
            stmt.setString(1, name);
            stmt.setString(2, city);
            stmt.setString(3, gstin);
            stmt.setString(4, Integer.toString(seats));
            stmt.setString(5, Integer.toString(gymOwnerId));

            stmt.executeUpdate();
            stmt.close();

        }catch(SQLException e)
        {
            System.out.println(e);
        }
//        Gym gym = new Gym();
//        gym.setGymId(id++);
//        gym.setName(name);
//        gym.setGstin(gstin);
//        gym.setCity(city);
//        gym.setSeats(seats);
//        gym.setGymOwnerId(gymOwnerId);
//        reqList.add(gym);

        return true;
    }
    public void onBoardGym(int gymId)
    {
        try {
            conn = Utils.connect();
            //System.out.println("Fetching gyms centres..");

            stmt = conn.prepareStatement(APPROVE_GYM_BY_ID);
            stmt.setString(1, "true");
            stmt.setString(2, Integer.toString(gymId));
            stmt.executeUpdate();
//            System.out.println("The gym centre has been approved!");
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        }
//        for(int i=0;i<reqList.size();i++)
//        {
//            if(reqList.get(i).getGymId()==gymId)
//            {
//                gymList.add(reqList.get(i));
//                reqList.remove(i);
//                return;
//            }
//        }
    }
    public void deleteGymRequest(int gymId)
    {
        for(int i=0;i<reqList.size();i++)
        {
            if(reqList.get(i).getGymId()==gymId)
            {
                reqList.remove(i);
                return;
            }
        }
    }
    public List<Gym> viewPendingRequests(int gymOwnerId)
    {
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
                        g.setGymId(Integer.parseInt(rs.getString("gymId")));
                        g.setIsApproved(rs.getString("isApproved"));
                pendingList.add(g);
            }
            //conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pendingList;
//      List<Gym> pr=new ArrayList<>();
//      for(Gym g:reqList)
//      {
//          if(g.getGymOwnerId()==gymOwnerId)
//          {
//              pr.add(g);
//          }
//      }
//      return pr;
    }

    public boolean deleteGym(int gymId) {
        for (int i = 0; i < gymList.size(); i++) {
            if (gymList.get(i).getGymId() == gymId) {
                gymList.remove(i);
            }
        }
        return true;
    }

    public List<String> getAllAreas()
    {
        Set<String> areas = new HashSet<>();
        for (Gym gym : gymList)
            areas.add(gym.getCity());

        return new ArrayList<String>(areas);
    }

    public Gym getGymById(int id)
    {
        for (Gym gym : gymList)
            if (gym.getGymId() == id)
                return gym;
        return null;
    }

    public List<Gym> getGymsByArea(String area) {

        List<Gym> gyms = new ArrayList<>();

        for (Gym gym : gymList)
            if (gym.getCity().equals(area))
                gyms.add(gym);

        return gyms;

    }
    public List<Gym> getGymsByOwner(int gymOwnerId) {

        List<Gym> gyms = new ArrayList<>();

        for (Gym gym : gymList)
            if (gym.getGymOwnerId()==gymOwnerId)
                gyms.add(gym);

        return gyms;

    }
    public List<Gym> viewRequests() {
        List<Gym> pendingList = new ArrayList<>();
        try {
            conn = Utils.connect();
            System.out.println("Fetching gym centres..");

            stmt = conn.prepareStatement(FETCH_ALL_PENDING_GYM_REQUESTS);

            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                Gym g=new Gym();
                g.setName(rs.getString("name"));
                g.setCity(rs.getString("city"));
                g.setSeats(Integer.parseInt(rs.getString("seats")));
                g.setGstin(rs.getString("gstin"));
                g.setGymId(Integer.parseInt(rs.getString("gymId")));
                g.setIsApproved(rs.getString("isApproved"));
                pendingList.add(g);
            }
            //conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pendingList;
        //return reqList;

    }
    public void updateGym(String updatedVal,String attr,int gymId)
    {
        try{
             conn = Utils.connect();
             stmt = conn.prepareStatement(UPDATE_GYM_DETAILS);
            stmt.setString(1, attr);
            stmt.setString(2, updatedVal);
            stmt.setString(3, Integer.toString(gymId));

            ResultSet rs = stmt.executeQuery();
            rs.next();

            stmt.close();
        }catch (SQLException e)
        {
            System.out.println(e);
        }
//        for(Gym g:gymList)
//        {
//            if(g.getGymId()==gymId)
//            {
//                if(attr.equals("name"))
//                {
//                    g.setName(updatedVal);
//                };
//                if(attr.equals("gstin")){
//                    g.setGstin(updatedVal);
//                };
//                if(attr.equals("city")){
//                    g.setCity(updatedVal);
//                };
//                if(attr.equals("seats")){
//                    g.setSeats(Integer.parseInt(updatedVal));
//                }
//            }
//        }
    }



}