package com.flipkart.bean;

public class Gym {
    private String GymId;
    private String GymOwnerId;
    private String name;
    private String city;
    private String gstin;
    private int seats;
    private String isApproved;


    public String getGymId() {
        return GymId;
    }

    public void setGymId(String gymId) {
        GymId = gymId;
    }

    public String getGymOwnerId() {
        return GymOwnerId;
    }

    public void setGymOwnerId(String gymOwnerId) {
        GymOwnerId = gymOwnerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getGstin() {
        return gstin;
    }

    public void setGstin(String gstin) {
        this.gstin = gstin;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(String isApproved) {
        this.isApproved = isApproved;
    }

    @Override
    public String toString() {
        return "Gym {" +
                "GymId=" + GymId + '\'' +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", gstin='" + gstin + '\'' +
                ", seats=" + seats +
                '}';
    }
}