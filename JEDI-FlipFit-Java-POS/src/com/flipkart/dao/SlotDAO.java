
package com.flipkart.dao;

import com.flipkart.bean.Slot;
import com.flipkart.utils.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.flipkart.constants.Constants.*;
import static com.flipkart.constants.SQLConstants.*;

public class SlotDAO {
    static SlotDAO slotDAO = null;

    public static synchronized SlotDAO getInstance() {
        if(slotDAO == null) {
            slotDAO = new SlotDAO();
        }
        return slotDAO;
    }

    public void createSlot(String date, String startTime, String gymId) {
        try {
            Connection conn = Utils.connect();
            PreparedStatement stmt = conn.prepareStatement(ADD_SLOT);

            stmt.setString(1, gymId);
            stmt.setString(2, date);
            stmt.setString(3, startTime);

            stmt.executeUpdate();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Slot> getSlotsByGymId(String gymId) {
        List<Slot> slotList = new ArrayList<>();

        try {
            Connection conn = Utils.connect();
            PreparedStatement ps = conn.prepareStatement(FETCH_SLOT_BY_GYMID);

            ps.setString(1, gymId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Slot slt = new Slot();

                slt.setGymId(gymId);
                slt.setSlotId(rs.getString("slotId"));
                slt.setDate(rs.getString("date"));
                slt.setStartTime(rs.getString("startTime"));

                slotList.add(slt);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return slotList;
    }
    public Set<String> getSlotsByCustomerId(String customerId) {
        Set<String> slotIdList = new HashSet<>();

        try {
            Connection conn = Utils.connect();
            PreparedStatement ps = conn.prepareStatement(GET_BOOKING_BY_CUSTOMER_ID);

            ps.setString(1, customerId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                slotIdList.add(rs.getString("slotId"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return slotIdList;
    }

    public Slot getSlotBySlotId(String slotId) {
        Slot slot = new Slot();
        try{
            Connection conn = Utils.connect();
            PreparedStatement ps = conn.prepareStatement(FETCH_SLOT_BY_ID);

            ps.setString(1, slotId);

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                boolean stat = rs.getString("availabilityStatus").equals("true");

                slot.setDate(rs.getString("date"));
                slot.setStartTime(rs.getString("startTime"));
                slot.setGymId(rs.getString("gymId"));
                slot.setAvailabilityStatus(Boolean.parseBoolean(rs.getString("availabilityStatus")));

                return slot;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return slot;
    }

    public void updateSlot(String updatedVal, String attr, String slotId) {
        try{
            Connection conn = Utils.connect();

            if(attr.equals("date")) {
                PreparedStatement stmt1 = conn.prepareStatement(UPDATE_SLOT_DETAILS_DATE);

                stmt1.setString(1, updatedVal);
                stmt1.setString(2, slotId);
                stmt1.executeUpdate();
                stmt1.close();

            }
            else if(attr.equals("startTime")){
                PreparedStatement stmt2 = conn.prepareStatement(UPDATE_SLOT_DETAILS_STARTTIME);
                stmt2.setString(1, updatedVal);
                stmt2.setString(2, slotId);
                stmt2.executeUpdate();
                stmt2.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void deleteSlotById(String slotId)
    {
        try {
            Connection conn = Utils.connect();
            PreparedStatement stmt = conn.prepareStatement(DELETE_SLOT_BY_ID);
            stmt.setString(1, slotId);
            stmt.executeUpdate();
            System.out.println(GREEN_COLOR + "Slot Deleted Successfully." + RESET_COLOR);
        }catch(SQLIntegrityConstraintViolationException e) {
            System.out.println(YELLOW_COLOR + "Slot already booked by Customers" +  RESET_COLOR);
            System.out.println(RED_COLOR + "Delete Failed" + RESET_COLOR);
        }
        catch(Exception e) {
            System.out.println(e);
            System.out.println(RED_COLOR + "Oops! An error occurred. Try again later." + RESET_COLOR);
        }
    }
}
