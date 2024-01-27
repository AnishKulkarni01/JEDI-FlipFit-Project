
package com.flipkart.dao;

import com.flipkart.bean.Slot;
import com.flipkart.utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
                slot.setStartTime(rs.getString("gymId"));
                slot.setGymId(rs.getString("availabilityStatus"));
                slot.setAvailabilityStatus(stat);

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
}
