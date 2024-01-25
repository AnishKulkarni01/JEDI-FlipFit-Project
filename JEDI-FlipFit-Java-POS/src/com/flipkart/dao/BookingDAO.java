package com.flipkart.dao;

<<<<<<< HEAD
public class BookingDAO implements BookingDaoInterface {
=======
import com.flipkart.bean.Booking;
import com.flipkart.bean.Slot;
>>>>>>> f8d8cd66afc8a8dff7fb690ef18c65e2215a813b

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
        return false;
    }
}
