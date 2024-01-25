package com.flipkart.service;

public interface UserServiceInterface {
    /**
     * Authenticates User
     *
     * @param username Username of user
     * @param password Password of user
     * @return True if user was authenticated
     */
    boolean authenticate(String username, String password);

    /**
     * Logins the user.
     *
     * @param username
     */
    void login(String username);
}
