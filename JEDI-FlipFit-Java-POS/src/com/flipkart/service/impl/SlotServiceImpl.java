package com.flipkart.service.impl;

import com.flipkart.bean.Slot;
import com.flipkart.dao.SlotDAO;
import com.flipkart.service.SlotServiceInterface;

import java.util.List;
import java.util.Set;

public class SlotServiceImpl implements SlotServiceInterface {
    SlotDAO slotDAO = SlotDAO.getInstance();

    public Set<String> getSlotsByCustomerId(String customerId){
        return slotDAO.getSlotsByCustomerId(customerId);
    }

    public List<Slot> getSlotsByGymId(String gymId){
        return slotDAO.getSlotsByGymId(gymId);
    }

    public Slot getSlotBySlotId(String slotId){
        return slotDAO.getSlotBySlotId(slotId);
    }

    public void createSlot(String date, String startTime, String gymId){
        slotDAO.createSlot(date, startTime, gymId);
    }

    public void updateSlot(String newValue, String updateColumn, String slotId){
        slotDAO.updateSlot(newValue, updateColumn, slotId);
    }

    public void deleteSlotById(String slotId){
        slotDAO.deleteSlotById(slotId);
    }
}
