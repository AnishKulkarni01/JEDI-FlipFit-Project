package com.flipkart.exceptions;

public class GymOwnerDneException extends Exception{
   public GymOwnerDneException()
   {
       super("GymOwner does not exist");

   }
}
