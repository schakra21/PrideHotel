package com.repository;

import java.text.ParseException;
import java.util.List;

import com.model.Booking;
import com.model.Room;

public interface BookingCustomRepository {
	List<Booking> getBookingsByRoomId(Integer roomid);

	List<Booking> getBookingsByCustomerId(Integer custid);

	List<Booking> getBookingsByDate(String startDate, String endDate, Integer room) throws ParseException;

	double getTotalCostOfCustomer(Integer custid);

}
