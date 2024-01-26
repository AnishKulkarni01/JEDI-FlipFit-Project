
package com.flipkart.dao;

import com.flipkart.bean.Customer;
import com.flipkart.bean.Gym;
import com.flipkart.bean.Slot;

import java.util.ArrayList;
import java.util.List;

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
    public boolean createSlot(String date,String startTime,String gymId) {
        //Registers a new slot in the database
        Slot slt=new Slot();
        slt.setDate(date);
        slt.setGymId(gymId);
        slt.setStartTime(startTime);
        slt.setSlotId(id++);
        slt.setAvailabilityStatus(true);
        slotList.add(slt);
        return true;
    }

    public List<Slot> getSlotsByGymId(String gymId) {

        List<Slot> l=new ArrayList<>();
        for(Slot slt:slotList)
        {
            if(slt.getGymId().equals(gymId) && slt.isAvailabilityStatus())l.add(slt);
        }
        return l;
    }
    public Slot getSlotbySlotId(int slotId)
    {
        for(Slot slot:slotList)
        {
            if(slot.getSlotId()==slotId)return slot;
        }
        return new Slot();
    }

    public Slot getSlotsBySlotId(String slotId) {

        Slot s = new Slot();
        for(Slot slt:slotList)
        {
            if(Integer.toString(slt.getSlotId()).equals(slotId)) s=slt;
        }
        return s;
    }

    public void updateSlot(String updatedVal,String attr,int slotId)
    {
        for(Slot s:slotList)
        {
            if(s.getSlotId()==slotId)
            {
                if(attr.equals("date"))
                {
                    s.setDate(updatedVal);
                };
                if(attr.equals("startTime")){
                    s.setStartTime(updatedVal);
                };
            }
        }
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
