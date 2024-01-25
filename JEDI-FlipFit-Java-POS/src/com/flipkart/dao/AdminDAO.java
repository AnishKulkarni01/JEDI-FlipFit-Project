package com.flipkart.dao;

public class AdminDAO implements AdminDaoInterface
{
    @Override
    public boolean makeAdmin(String adminID)
    {
        //Provides admin privileges to a user
        return false;
    }

    @Override
    public boolean removeAdmin(String adminID)
    {
        //Revokes admin privileges from a user
        return false;
    }
    
}
