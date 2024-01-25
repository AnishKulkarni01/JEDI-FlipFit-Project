package com.flipkart.service;

import java.util.List;

/**
 * This interface defines the service operations related to admin functionality.
 * It includes methods for approving new gyms as well as viewing pending
 * approval requests.
 */
public interface AdminServiceInterface {

    /**
     * Approves or disapproves a request to add new gym fetched using requestId
     *
     * @param requestId The ID of the approval request
     *
     */
    void approveRequest(String requestId);


    /**
     * Retrieves a list of pending gym approval requests awaiting approval.
     *
     * @return List of pending approval request IDs
     */
    List<String> viewPendingRequests();

    /**
     * Retrieves a list of all gym approval requests.
     *
     * @return List of all request IDs
     */
    List<String> viewAllRequests();
    /**
     * Registers a new admin.
     * @param username String The username of new admin
     * @param password String The password of new admin
     * @param email String The email of new admin
     *
     */
    void register(String username, String password, String email);


}

