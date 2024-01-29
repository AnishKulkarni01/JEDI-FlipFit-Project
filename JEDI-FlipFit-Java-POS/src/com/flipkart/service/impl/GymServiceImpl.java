package com.flipkart.service.impl;

import com.flipkart.bean.Gym;
import com.flipkart.dao.GymDAO;
import com.flipkart.service.GymServiceInterface;

import java.util.List;

import static com.flipkart.constants.Constants.PURPLE_COLOR;
import static com.flipkart.constants.Constants.RESET_COLOR;

public class GymServiceImpl implements GymServiceInterface {

    GymDAO gymDao= GymDAO.getInstance();

    public void viewGymRequests() {
        List<Gym> requestDetails = gymDao.viewRequests();

        String[] headers = {PURPLE_COLOR + "  GymId  " + RESET_COLOR, PURPLE_COLOR + "  Name   " + RESET_COLOR, PURPLE_COLOR + "  City   " + RESET_COLOR, PURPLE_COLOR + "  GSTIN  " + RESET_COLOR, PURPLE_COLOR + "  Seats   " + RESET_COLOR};
        System.out.println("| " + String.join(" | ", headers) + " |");

        for(Gym gym : requestDetails){
            String output = gym.toString();

            String[] keyValuePairs = output
                    .replace("{", "")
                    .replace("}", "")
                    .split(",\\s*");

            for (String pair : keyValuePairs) {
                String[] entry = pair.split("=");
                String value = entry[1].trim();
                String formatSpecifier = "%-10s";
                System.out.printf("| " + formatSpecifier, value);
            }
            System.out.println(" |");
        }

    }

    public void onBoardGym(String gymId){
        gymDao.onBoardGym(String.valueOf(gymId));
    }

    public void deleteGymRequest(String gymId){
        gymDao.deleteGymRequest(String.valueOf(gymId));
    }
}