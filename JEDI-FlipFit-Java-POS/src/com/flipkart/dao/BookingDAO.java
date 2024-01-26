package com.flipkart.dao;

import com.flipkart.bean.Booking;
import com.flipkart.bean.Slot;

import java.util.ArrayList;
import java.util.List;

public class BookingDAO {
    static BookingDAO bookingDao=null;
    UserDAO userDao=UserDAO.getInstance();

    List<Booking> bookingList=new ArrayList<>();

    private int id= 1;
    public static synchronized BookingDAO getInstance()
    {
        if(bookingDao==null)
        {
            bookingDao=new BookingDAO();
            Booking b=new Booking();

        }
        return bookingDao;
    }
    public void addBooking(String customerId,String slotId)
    {
        Booking b=new Booking();
        b.setCustomerId(customerId);
        b.setSlotId(slotId);
        b.setBookingId(Integer.toString(id++));
        bookingList.add(b);
    }
    public List<Booking> getBookingbyCustId(String customerId)
    {
        List<Booking> l=new ArrayList<>();
        for(Booking b:bookingList)
        {
            if(b.getCustomerId().equals(customerId))
            {
                l.add(b);
            }
        }
        return l;
    }
    public boolean deleteBookingId(String bookingId)
    {
        for(int i=0;i<bookingList.size();i++)
        {
            if(bookingList.get(i).getBookingId()==bookingId)
            {
                bookingList.remove(i);
                return true;
            }
        }
        System.out.println("Booking DNE");
        return false;
    }
}
