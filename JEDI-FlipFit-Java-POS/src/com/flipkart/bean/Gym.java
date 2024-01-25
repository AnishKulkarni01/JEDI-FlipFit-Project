package com.flipkart.bean;

public class Gym {
    private int GymId;

    public int getGymId() {
        return GymId;
    }

    public void setGymId(int gymId) {
        GymId = gymId;
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

    private String name;
    private String city;
    private String gstin;
    private int seats;

    @Override
    public String toString() {
        return "Gym{" +
                "GymId=" + GymId +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", gstin='" + gstin + '\'' +
                ", seats=" + seats +
                '}';
    }
}
