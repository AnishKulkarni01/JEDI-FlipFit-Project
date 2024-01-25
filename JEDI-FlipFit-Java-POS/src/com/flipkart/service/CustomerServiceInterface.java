package com.flipkart.service;

import com.flipkart.bean.Slot;

import java.util.List;

public interface CustomerServiceInterface {
    /**
     * Displays customer profile
     */
    void viewProfile();

    /**
     * Registers new customer
     * @param name Username of new customer
     * @param password Password of new customer
     */
    void register(String name, String password);

    /**
     * Edits profile of customer
     * @param customerId  CustomerId of the customer
     */
    void editProfile(int customerId);

    /**
     * Enqueues customer in the waiting list
     * @return True if the customer successfully joined waitlist
     */
    boolean joinWaitList();

    /**
     * View slots available for booking
     * @return List of available slots.
     */
    List<Slot> viewSlots();

    /**
     * Books a slot
     * @return True if the slot was successfully booked for customer
     */
    boolean bookSlot();

}
