package com.flipkart.bean;

public class GymOwner {
    private int gymOwnerID;
    private String password;
    private String name;
    private String contact;
    private String email;

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private String address;

    public int getGymOwnerID() {
        return gymOwnerID;
    }

    public void setGymOwnerID(int gymOwnerID) {
        this.gymOwnerID = gymOwnerID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
