package com.flipkart.dao;

import com.flipkart.bean.Slot;

public interface SlotDAOInterface {
    /**
     *
     * @return
     */
    public boolean createSlot();
    public Slot getSlotById(String slotId);
    public boolean updateSlot(String slotId);
    public boolean deleteSlot(String slotId);
}
