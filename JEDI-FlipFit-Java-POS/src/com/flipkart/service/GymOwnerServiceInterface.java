package com.flipkart.service;

import com.flipkart.exceptions.GymOwnerUpdateFailed;

import java.util.List;

public interface GymOwnerServiceInterface {
    void register(String username, String password, String email, String contact);
    List<String> getOwnerIdByUsername(String username);
    void viewProfile();
    void updateGymOwnerDetails(String updatedVal,String attr,String customerId) throws GymOwnerUpdateFailed;
    void viewGymOwnerRequests();
    void approveGymOwner(String gymOwnerId);
    void rejectGymOwner(String gymOwnerId);
}
