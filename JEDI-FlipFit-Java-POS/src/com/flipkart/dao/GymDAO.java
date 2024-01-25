package com.flipkart.dao;

import com.flipkart.bean.Customer;
import com.flipkart.bean.Gym;

import java.util.*;

import static com.flipkart.dao.CustomerDAO.custDao;

<<<<<<< HEAD
public class GymDAO implements GymDaoInterface {
=======

public class GymDAO {
>>>>>>> 7674efe987d3c25454ede47f179dc8cb84eea1b3

    static GymDAO gymdao = null;
    List<Gym> gymList = new ArrayList<Gym>();
    private int id = 0;


    public static synchronized GymDAO getInstance() {
        if (gymdao == null) {
            gymdao = new GymDAO();
        }
        return gymdao;
    }

<<<<<<< HEAD
    @Override
    public boolean onBoardGym(String gymName, String gstin, String city, int seats){
        Gym gym=new Gym();
=======

    public boolean onBoardGym(String gymName, String gstin, String city, int seats) {
        Gym gym = new Gym();
>>>>>>> 7674efe987d3c25454ede47f179dc8cb84eea1b3
        gym.setGymId(id++);
        gym.setName(gymName);
        gym.setGstin(gstin);
        gym.setCity(city);
        gym.setSeats(seats);
        gymList.add(gym);

        //for (Gym gym1 : gymList) {
        //    System.out.println(gym1.getName());
        //}

        return true;
    }

<<<<<<< HEAD

    @Override
    public boolean deleteGym(int gymId){
=======
    public boolean deleteGym(int gymId) {
>>>>>>> 7674efe987d3c25454ede47f179dc8cb84eea1b3
        for (int i = 0; i < gymList.size(); i++) {
            if (gymList.get(i).getGymId() == gymId) {
                gymList.remove(i);
            }
        }
        return true;
    }

    public List<String> getAllAreas()
    {
        Set<String> areas = new HashSet<>();
        for (Gym gym : gymList)
            areas.add(gym.getCity());

        return new ArrayList<String>(areas);
    }

    public Gym getGymById(int id)
    {
        for (Gym gym : gymList)
            if (gym.getGymId() == id)
                return gym;
        return null;
    }

    public List<Gym> getGymsByArea(String area) {

        List<Gym> gyms = new ArrayList<>();

        for (Gym gym : gymList)
            if (gym.getCity().equals(area))
                gyms.add(gym);

        return gyms;

    }



}