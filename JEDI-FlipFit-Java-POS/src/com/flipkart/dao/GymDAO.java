package com.flipkart.dao;

import com.flipkart.bean.Customer;
import com.flipkart.bean.Gym;

import java.util.*;

import static com.flipkart.dao.CustomerDAO.custDao;


public class GymDAO {

    static GymDAO gymdao = null;
    List<Gym> gymList = new ArrayList<Gym>();
    List<Gym>reqList=new ArrayList<>();

    private int id = 1;


    public static synchronized GymDAO getInstance() {
        if (gymdao == null) {
            gymdao = new GymDAO();
        }
        return gymdao;
    }


    public boolean sendOnboardReq(String gymName, String gstin, String city, int seats,int gymOwnerId) {
        Gym gym = new Gym();
        gym.setGymId(id++);
        gym.setName(gymName);
        gym.setGstin(gstin);
        gym.setCity(city);
        gym.setSeats(seats);
        gym.setGymOwnerId(gymOwnerId);
        reqList.add(gym);

        return true;
    }
    public void onBoardGym(int gymId)
    {
        for(int i=0;i<reqList.size();i++)
        {
            if(reqList.get(i).getGymId()==gymId)
            {
                gymList.add(reqList.get(i));
                reqList.remove(i);
                return;
            }
        }
    }
    public void deleteGymRequest(int gymId)
    {
        for(int i=0;i<reqList.size();i++)
        {
            if(reqList.get(i).getGymId()==gymId)
            {
                reqList.remove(i);
                return;
            }
        }
    }
    public List<Gym> viewPendingRequests(int gymOwnerId)
    {
      List<Gym> pr=new ArrayList<>();
      for(Gym g:reqList)
      {
          if(g.getGymOwnerId()==gymOwnerId)
          {
              pr.add(g);
          }
      }
      return pr;
    }

    public boolean deleteGym(int gymId) {
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
    public List<Gym> getGymsByOwner(int gymOwnerId) {

        List<Gym> gyms = new ArrayList<>();

        for (Gym gym : gymList)
            if (gym.getGymOwnerId()==gymOwnerId)
                gyms.add(gym);

        return gyms;

    }
    public List<Gym> viewRequests() {

        return reqList;

    }
    public void updateGym(String updatedVal,String attr,int gymId)
    {
        for(Gym g:gymList)
        {
            if(g.getGymId()==gymId)
            {
                if(attr.equals("name"))
                {
                    g.setName(updatedVal);
                };
                if(attr.equals("gstin")){
                    g.setGstin(updatedVal);
                };
                if(attr.equals("city")){
                    g.setCity(updatedVal);
                };
                if(attr.equals("seats")){
                    g.setSeats(Integer.parseInt(updatedVal));
                }
            }
        }
    }



}