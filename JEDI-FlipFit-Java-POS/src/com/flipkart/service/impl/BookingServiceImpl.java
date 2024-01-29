package com.flipkart.service.impl;

import com.flipkart.bean.Booking;
import com.flipkart.dao.BookingDAO;
import com.flipkart.service.BookingServiceInterface;

import java.util.List;

public class BookingServiceImpl implements BookingServiceInterface {
    BookingDAO bookingDAO = BookingDAO.getInstance();

    public List<Booking> getBookingByCustomerId (String customerId){
        return bookingDAO.getBookingbyCustId(customerId);
    }

    public void addBooking(String customerId, String slotId){
        bookingDAO.addBooking(customerId, slotId);
    }

    public void deleteBooking(String bookingId){
        bookingDAO.deleteBookingId(bookingId);
    }
}
