package com.flipkart.client;

import com.flipkart.bean.Gym;
import com.flipkart.bean.Slot;
import com.flipkart.dao.*;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GymOwnerFlipFitMenu {
    UserDAO userDao= UserDAO.getInstance();
    CustomerDAO custDao = CustomerDAO.getInstance();
    BookingDAO b=BookingDAO.getInstance();
    SlotDAO s=SlotDAO.getInstance();
    GymDAO gymDao= GymDAO.getInstance();
    GymOwnerDAO ownDao=GymOwnerDAO.getInstance();
    String ownId = Integer.toString(ownDao.getIdFromName(userDao.getCurrentUser().get(0)));


    public void showGymOwnerFlipMenu()
    {
        int loopFlag=0;
        while(loopFlag==0)
        {
        System.out.println("1. Request Gym Onboarding\n" +
                "2. Gym Details Update\n" +
                "3. Add Slot\n" +
                "4. Update Slot\n"+
                "5. ViewSlots\n"+
                "6. Edit Profile\n" +
                "7. View Gyms\n"+
                "8. View Pending Requests\n"+
                "9. Log out\n" +
                "10. Back");
        Scanner sc=new Scanner(System.in);
        int opt=sc.nextInt();

            switch(opt)
            {
                case 1:
                    //System.out.println("Request for Gym onboarding sent");
                    System.out.println("Enter Gym Details");
                    //public boolean onBoardGym(String gymName, String gstin, String city, int seats,int gymOwnerId) {
                    System.out.println("Enter Gym Name : ");
                    String gymName=sc.next();
                    System.out.println("Enter GSTIN : ");

                    String gstin=sc.next();
                    System.out.println("Enter Gym City : ");

                    String city=sc.next();
                    System.out.println("Enter Seats : ");

                    int seats=sc.nextInt();
                    gymDao.sendOnboardReq(gymName,gstin,city,seats, Integer.parseInt(ownId));
                    System.out.println("Request Sent Successfully");


                    break;
                case 2:
                    System.out.println("Gym Details Update");
                    System.out.println("Enter GymId");
                    int updGymId= sc.nextInt();
                    System.out.println("Select Option to Update");
                    System.out.println("1. Name\n2. City\n3. Seats\n4. GSTIN");
                    int updOpt=sc.nextInt();
                    System.out.println("Enter new value");
                    String newVal=sc.next();
                    if(updOpt==1)gymDao.updateGym(newVal,"name",updGymId);
                    if(updOpt==2)gymDao.updateGym(newVal,"city",updGymId);
                    if(updOpt==3)gymDao.updateGym(newVal,"seats",updGymId);
                    if(updOpt==4)gymDao.updateGym(newVal,"gstin",updGymId);


                    break;
                case 3:
                    System.out.println("Add Slots");

                    System.out.println("Enter the following details : ");
                    System.out.println("GymId : ");
                    String gymId= sc.next();
                    System.out.println("Date : ");
                    String date= sc.next();
                    System.out.println(" StartTime : ");
                    String time= sc.next();
                    s.createSlot(date,time,gymId);
                    break;
                case 4:
                    System.out.println("Slot Details Update");
                    System.out.println("Enter SlotId");
                    int updSlotId= sc.nextInt();
                    System.out.println("Select Option to Update");
                    System.out.println("1. Date\n2. Start Time");
                    int updSlotOpt=sc.nextInt();
                    System.out.println("Enter new value");
                    String newSlotVal=sc.next();
                    if(updSlotOpt==1)s.updateSlot(newSlotVal,"date",updSlotId);
                    if(updSlotOpt==2)s.updateSlot(newSlotVal,"startTime",updSlotId);
                    break;

                case 5:
                    System.out.println("View Slots");
                    System.out.println("Enter GymId");
                    int viewSlotGymId= sc.nextInt();
                    List<Slot>sltl=new ArrayList<>();
                    sltl=s.getSlotsByGymId(Integer.toString(viewSlotGymId));
                    for(Slot s:sltl)
                    {
                        System.out.println("SlotId : "+s.getSlotId()+" StartTime : "+s.getStartTime()+" Date : "+s.getDate());
                    }
                    break;

                case 6:
                    System.out.println("Profile Edited");
                    break;
                case 7:
                    System.out.println("Viewing Gyms");
                    List<Gym> lg=new ArrayList<>();
                    lg=gymDao.getGymsByOwner(Integer.parseInt(ownId));
                    for(Gym g:lg)
                    {
                        System.out.println("GymId : "+g.getGymId()+",GymName : "+g.getName()+",GSTIN : "+g.getGstin()+",City : "+g.getCity()+",Seats : "+g.getSeats());
                    }
                    break;
                case 8:
                    System.out.println("Showing Pending Requests");
                    List<Gym> pr=new ArrayList<>();
                    pr=gymDao.viewPendingRequests(Integer.parseInt(ownId));
                    for(Gym g:pr) System.out.println("GymId : "+g.getGymId()+",GymName : "+g.getName()+",GSTIN : "+g.getGstin()+",City : "+g.getCity()+",Seats : "+g.getSeats());
                    break;
                case 9:
                    System.out.println("Logging out");
                    loopFlag=1;
                    break;
                case 10:
                    loopFlag=1;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + opt);

            }
        }


    }
}
