package com.flipkart.dao;

import com.flipkart.bean.Booking;
import com.flipkart.exceptions.BookingDneException;
import com.flipkart.exceptions.CustomerDneException;
import com.flipkart.exceptions.SlotDneException;
import com.flipkart.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.flipkart.constants.SQLConstants.*;

public class BookingDAO {
    static BookingDAO bookingDAO = null;
    public static synchronized BookingDAO getInstance() {
        if(bookingDAO ==null) {
            bookingDAO =new BookingDAO();
        }

        return bookingDAO;
    }


    public void addBooking(String customerId,String slotId) throws SlotDneException {
        try{
            Connection conn = DBUtils.connect();
            PreparedStatement stmt = conn.prepareStatement(ADD_BOOKING);

            stmt.setString(1,customerId);
            stmt.setString(2,slotId);

            stmt.executeUpdate();
            stmt.close();
        } catch(SQLException e)
        {
            throw new SlotDneException();
            //System.out.println("SlotId does not exist");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Booking> getBookingbyCustId(String customerId) throws CustomerDneException {
        List<Booking> bookingList = new ArrayList<>();

        try {
            Connection conn = DBUtils.connect();
            PreparedStatement stmt = conn.prepareStatement(GET_BOOKING_BY_CUSTOMER_ID);

            stmt.setString(1, customerId);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                Booking booking = new Booking();

                booking.setBookingId(rs.getString("bookingId"));
                booking.setCustomerId(rs.getString("customerId"));
                booking.setSlotId(rs.getString("slotId"));

                bookingList.add(booking);
            }
        } catch(SQLException e)
        {
            throw new CustomerDneException();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookingList;
    }

    public void deleteBookingId(String bookingId) throws BookingDneException {
        try {
            Connection conn = DBUtils.connect();
            PreparedStatement stmt = conn.prepareStatement(CANCEL_BOOKING_BY_ID);

            stmt.setString(1, bookingId);

            stmt.executeUpdate();
        } catch(SQLException e)
        {
            throw new BookingDneException();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}