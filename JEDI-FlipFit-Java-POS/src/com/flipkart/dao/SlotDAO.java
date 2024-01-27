
package com.flipkart.dao;

import com.flipkart.bean.Customer;
import com.flipkart.bean.Gym;
import com.flipkart.bean.Slot;
import com.flipkart.utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.flipkart.constants.Constants.*;

public class SlotDAO {
    static SlotDAO slotDao=null;
    UserDAO userDao=UserDAO.getInstance();

    List<Slot> slotList=new ArrayList<>();

    private int id= 1;
    public static synchronized SlotDAO getInstance()
    {
        if(slotDao==null)
        {
            slotDao=new SlotDAO();
        }
        return slotDao;
    }
    public void createSlot(String date,String startTime,String gymId) {
        //Registers a new slot in the database
        //Slot slt=new Slot();
        try {
            Connection conn = Utils.connect();
            PreparedStatement stmt = conn.prepareStatement(ADD_SLOT);

            stmt.setString(1, gymId);
            stmt.setString(2, date);
            stmt.setString(3, startTime);

            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException exp) {
            exp.printStackTrace();
        } catch (Exception exp) {
            exp.printStackTrace();
        }

//        Slot slt=new Slot();
//        slt.setDate(date);
//        slt.setGymId(gymId);
//        slt.setStartTime(startTime);
//        slt.setSlotId(id++);
//        slt.setAvailabilityStatus(true);
//        slotList.add(slt);
//        return true;
    }

    public List<Slot> getSlotsByGymId(String gymId) {

        List<Slot> slotList = new ArrayList<>();
        try{
            Connection conn = Utils.connect();
            PreparedStatement ps = conn.prepareStatement(FETCH_SLOT_BY_GYMID);
            ps.setString(1,gymId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Slot slt=new Slot();
                String slotId = rs.getString("slotId");
                String date = rs.getString("date");
                String startTime = rs.getString("startTime");
                slt.setGymId(gymId);
                slt.setSlotId(Integer.parseInt(slotId));
                slt.setDate(date);
                slt.setStartTime(startTime);
                slotList.add(slt);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return slotList;
//        List<Slot> l=new ArrayList<>();
//        for(Slot slt:slotList)
//        {
//            if(slt.getGymId().equals(gymId) && slt.isAvailabilityStatus())l.add(slt);
//        }
//        return l;
    }
//    public Slot getSlotbySlotId(int slotId)
//    {
//        for(Slot slot:slotList)
//        {
//            if(slot.getSlotId()==slotId)return slot;
//        }
//        return new Slot();
//    }

    public Slot getSlotBySlotId(String slotId) {
        Slot slot = new Slot();
        try{
            Connection conn = Utils.connect();
            PreparedStatement ps = conn.prepareStatement(FETCH_SLOT_BY_ID);
            ps.setString(1,slotId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String date=rs.getString("date");
                String gymId = rs.getString("gymId");
                String startTime = rs.getString("startTime");
                String availabilityStatus=rs.getString("availabilityStatus");
                slot.setDate(date);
                slot.setStartTime(startTime);
                slot.setGymId(gymId);
                boolean stat=availabilityStatus.equals("true")?true:false;
                slot.setAvailabilityStatus(stat);
                return slot;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return slot;
//        Slot s = new Slot();
//        for(Slot slt:slotList)
//        {
//            if(Integer.toString(slt.getSlotId()).equals(slotId)) s=slt;
//        }
//        return s;
    }

    public void updateSlot(String updatedVal,String attr,int slotId)
    {
        try{
            Connection conn = Utils.connect();
            PreparedStatement stmt = conn.prepareStatement(UPDATE_SLOT_DETAILS);
            stmt.setString(1, attr);
            stmt.setString(2, updatedVal);
            stmt.setString(3, Integer.toString(slotId));

          stmt.executeUpdate();

            stmt.close();
        }catch (SQLException e)
        {
            System.out.println(e);
        }
//        for(Slot s:slotList)
//        {
//            if(s.getSlotId()==slotId)
//            {
//                if(attr.equals("date"))
//                {
//                    s.setDate(updatedVal);
//                };
//                if(attr.equals("startTime")){
//                    s.setStartTime(updatedVal);
//                };
//            }
//        }
    }

    public boolean deleteSlot(String slotId) {
        for(int i=0;i<slotList.size();i++)
        {
            if(slotList.get(i).getSlotId()==Integer.parseInt(slotId))
            {
                slotList.remove(i);
                return true;
            }
        }
        System.out.println("Slot DNE");
        return false;
    }

}
