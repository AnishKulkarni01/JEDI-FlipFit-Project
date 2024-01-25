package com.flipkart.dao;

import com.flipkart.bean.GymOwner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GymOwnerDAO {
    private static GymOwnerDAO gymOwnerDAO = null;

    private List<GymOwner> gymOwnerList = new ArrayList<>();

    private int id = 1; // Starting ID for GymOwners

    private GymOwnerDAO() {
        // Private constructor to restrict instantiation
    }

    public static synchronized GymOwnerDAO getInstance() {
        if (gymOwnerDAO == null) {
            gymOwnerDAO = new GymOwnerDAO();
        }
        return gymOwnerDAO;
    }

    public boolean registerGymOwner(String name, String password) {
        GymOwner gymOwner = new GymOwner();
        gymOwner.setGymOwnerID(id++);
        gymOwner.setName(name);
        gymOwner.setPassword(password);
        gymOwnerList.add(gymOwner);
        for (GymOwner owner : gymOwnerList) {
            System.out.println(owner.getName());
        }

        UserDAO userDAO = UserDAO.getInstance();
        userDAO.addUser(name, password, "GYM_OWNER");
        return true;
    }

    public GymOwner getGymOwner(String gymOwnerId) {
        for (GymOwner owner : gymOwnerList) {
            if (String.valueOf(owner.getGymOwnerID()).equals(gymOwnerId)) {
                return owner;
            }
        }
        return null; // Return null if GymOwner with given ID is not found
<<<<<<< HEAD
    }

    public boolean updateGymOwnerDetails(String gymOwnerId, String newName, String newPassword, String newEmail, String newContact) {
        for (GymOwner owner : gymOwnerList) {
            if (String.valueOf(owner.getGymOwnerID()).equals(gymOwnerId)) {
                owner.setName(newName);
                owner.setPassword(newPassword);
                owner.setEmail(newEmail);
                owner.setContact(newContact);
                return true;
            }
        }
        return false; // Return false if GymOwner with given ID is not found
    }

    public boolean deleteGymOwner(String gymOwnerId) {
        Iterator<GymOwner> iterator = gymOwnerList.iterator();
        while (iterator.hasNext()) {
            GymOwner owner = iterator.next();
            if (String.valueOf(owner.getGymOwnerID()).equals(gymOwnerId)) {
                iterator.remove();
                return true;
            }
        }
        return false; // Return false if GymOwner with given ID is not found
=======
>>>>>>> 9c12d1eb947e1858f3ae36f668d76e4112e75a95
    }

    public boolean updateGymOwnerDetails(String gymOwnerId, String newName, String newPassword, String newEmail, String newContact) {
        for (GymOwner owner : gymOwnerList) {
            if (String.valueOf(owner.getGymOwnerID()).equals(gymOwnerId)) {
                owner.setName(newName);
                owner.setPassword(newPassword);
                return true;
            }
        }
        return false; // Return false if GymOwner with given ID is not found
    }

    public boolean deleteGymOwner(String gymOwnerId) {
        Iterator<GymOwner> iterator = gymOwnerList.iterator();
        while (iterator.hasNext()) {
            GymOwner owner = iterator.next();
            if (String.valueOf(owner.getGymOwnerID()).equals(gymOwnerId)) {
                iterator.remove();
                return true;
            }
        }
        return false; // Return false if GymOwner with given ID is not found
    }
}