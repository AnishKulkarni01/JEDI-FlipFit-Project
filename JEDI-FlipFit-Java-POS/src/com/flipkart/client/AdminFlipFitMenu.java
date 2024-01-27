package com.flipkart.client;

import com.flipkart.bean.Gym;
import com.flipkart.dao.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminFlipFitMenu {
    UserDAO userDao= UserDAO.getInstance();
    CustomerDAO custDao = CustomerDAO.getInstance();
    BookingDAO b=BookingDAO.getInstance();
    SlotDAO s=SlotDAO.getInstance();
    GymDAO gymDao= GymDAO.getInstance();
    GymOwnerDAO ownDao=GymOwnerDAO.getInstance();
    //String ownId = Integer.toString(ownDao.getIdFromName(userDao.getCurrentUser().get(0)));
    public void showAdminFlipFitMenu(){
        int loopFlag=0;
        while(loopFlag==0)
        {
        System.out.println("1. View Pending Requests\n"+
                "2. Approve Gym Onboarding Requests\n" +
                "3. Decline Gym Onboarding Requests\n" +
                "4. Log out\n" +
                "5. Back");
        Scanner sc = new Scanner(System.in);
        int action = sc.nextInt();

            switch(action){
                case 1:
                    System.out.println("Viewing Requests");
                    List<Gym>r=new ArrayList<>();
                    r=gymDao.viewRequests();
                    for(Gym g:r)
                    {
                        System.out.println("GymId : "+g.getGymId()+",GymName : "+g.getName()+",GSTIN : "+g.getGstin()+",City : "+g.getCity()+",Seats : "+g.getSeats()+", GymOwnerId : "+g.getGymOwnerId());
                    }
                    break;
                case 2:
                    //System.out.println("Approving Onboarding Requests...");
                    System.out.println("Select GymId to Approve Request");
                    List<Gym> lg=new ArrayList<>();
                    lg=gymDao.viewRequests();
                    for(Gym g:lg)
                    {
                        System.out.println("GymId : "+g.getGymId()+",GymName : "+g.getName()+",GSTIN : "+g.getGstin()+",City : "+g.getCity()+",Seats : "+g.getSeats());
                    }
                    int approvedGymId=sc.nextInt();
                    gymDao.onBoardGym(approvedGymId);

                    break;
                case 3:
                    //System.out.println("Rejecting Onboarding Requests...");
                    System.out.println("Select GymId to Reject Request");
                    List<Gym> req=new ArrayList<>();
                    req=gymDao.viewRequests();
                    for(Gym g:req)
                    {
                        System.out.println("GymId : "+g.getGymId()+",GymName : "+g.getName()+",GSTIN : "+g.getGstin()+",City : "+g.getCity()+",Seats : "+g.getSeats());
                    }
                    int rejectGymId=sc.nextInt();
                    gymDao.deleteGymRequest(rejectGymId);

                    break;
                case 4:
                    System.out.println("Logging Out");
                    loopFlag=1;
                    break;
                case 5:
                    loopFlag=1;
                    break;

                default:
                    throw new IllegalStateException("Unexpected value: " + action);        }
        }

    }
}
