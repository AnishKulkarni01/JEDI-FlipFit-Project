package com.flipkart.dao;

import com.flipkart.bean.Booking;
import com.flipkart.bean.Slot;
import com.flipkart.utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.flipkart.constants.Constants.*;

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
        try{
            Connection conn = Utils.connect();
            PreparedStatement stmt = conn.prepareStatement(ADD_BOOKING);
            stmt.setString(1,customerId);
            stmt.setString(2,slotId);
            stmt.executeUpdate();
            stmt.close();

        }
        catch (Exception exp) {
            exp.printStackTrace();
            System.out.println("Oops! An error occurred. Try again later.");
        }
//        Booking b=new Booking();
//        b.setCustomerId(customerId);
//        b.setSlotId(slotId);
//        b.setBookingId(Integer.toString(id++));
//        bookingList.add(b);
    }
    public List<Booking> getBookingbyCustId(String customerId)
    {
        List<Booking> l=new ArrayList<>();
        try {
            Connection conn = Utils.connect();
            PreparedStatement stmt = conn.prepareStatement(GET_BOOKING_BY_CUSTOMER_ID);
            stmt.setString(1, customerId);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                Booking booking = new Booking();
                booking.setBookingId(rs.getString("bookingId"));
                rs.getString("userID");
                rs.getString("slotId");
                l.add(booking);
            }
        } catch(Exception e) {
            System.out.println("Oops! An error occurred. Try again later.");
        }
        return l;
//        for(Booking b:bookingList)
//        {
//            if(b.getCustomerId().equals(customerId))
//            {
//                l.add(b);
//            }
//        }
//        return l;
    }
    public boolean deleteBookingId(String bookingId)
    {
        try {
            Connection conn = Utils.connect();
            PreparedStatement stmt = conn.prepareStatement(CANCEL_BOOKING_BY_ID);
            stmt.setString(1, bookingId);
            stmt.executeUpdate();
        return true;}
             catch(Exception e) {
            System.out.println("Oops! An error occurred. Try again later.");
        }
//        for(int i=0;i<bookingList.size();i++)
//        {
//            if(bookingList.get(i).getBookingId().equals(bookingId))
//            {
//                bookingList.remove(i);
//                return true;
//            }
//        }
//        System.out.println("Booking DNE");
        return false;
    }
}
