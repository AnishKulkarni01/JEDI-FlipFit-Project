package com.flipkart.service;

import java.util.Arrays;
import java.util.List;

public class AdminService  implements AdminServiceInterface{
    // View pending requests
    @Override
    public List<String> viewPendingRequests() {
        // Implementation to view pending requests
        return Arrays.asList("abc", "cde");
    }

    // Approve request
    @Override
    public void approveRequest(String requestId) {
        // Implementation to approve a specific request
    }

    // View all requests
    @Override
    public List<String> viewAllRequests() {
        // Implementation to view all requests
        return Arrays.asList("abc", "cde");
    }

    // Register
    @Override
    public void register(String username, String password, String email) {
        // Implementation to register a new admin
    }
}
