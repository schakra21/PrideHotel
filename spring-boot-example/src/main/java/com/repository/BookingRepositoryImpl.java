package com.repository;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.model.Booking;
import com.model.Room;

@Repository
@Transactional(readOnly = true)
public class BookingRepositoryImpl implements BookingCustomRepository {

	@PersistenceContext
	EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Booking> getBookingsByRoomId(Integer roomid) {
		// TODO Auto-generated method stub
		

		Query query = entityManager.createNativeQuery("SELECT * FROM BOOKING WHERE ROOM_ID LIKE ?", Booking.class);
		query.setParameter(1, roomid + "%");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Booking> getBookingsByCustomerId(Integer custid) {
		// TODO Auto-generated method stub
		Query query = entityManager.createNativeQuery("SELECT * FROM BOOKING WHERE CUSTOMER LIKE ?", Booking.class);
		query.setParameter(1, custid + "%");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Booking> getBookingsByDate(String startDate, String endDate, Integer room) throws ParseException {
		// TODO Auto-generated method stub
		SimpleDateFormat start = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date startutildate = start.parse(startDate);
		java.util.Date endutildate = start.parse(endDate);
		java.sql.Date startsqldate = new java.sql.Date(startutildate.getTime());
		java.sql.Date endsqldate = new java.sql.Date(endutildate.getTime());
		
		Query query = entityManager.createNativeQuery(
				"SELECT * FROM BOOKING WHERE START_DATE>= ? AND END_DATE<= ? AND ROOM_ID = ?", Booking.class);
		query.setParameter(1, startsqldate);
		query.setParameter(2, endsqldate);
		query.setParameter(3, room);
		return query.getResultList();
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	
	public double getTotalCostOfCustomer(Integer custid) {
		
		Query query = entityManager.createNativeQuery("SELECT * FROM BOOKING WHERE CUSTOMER=?",Booking.class);
		query.setParameter(1, custid);
		List<Booking> roomPriceList = query.getResultList();
		Iterator<Booking> iter = roomPriceList.iterator();
		double roomPrice=0.0;
		double cost=0.0;
		Date startDate=null;
		Date endDate=null;
		Booking nextBooking=null;
		while (iter.hasNext()) {
			nextBooking=iter.next();
			cost = nextBooking.getRoom().getRoomPrice();
			startDate=nextBooking.getStartDate();
			endDate=nextBooking.getEndDate();
			int diffInDays = (int)( (endDate.getTime() - startDate.getTime()) 
	                 / (1000 * 60 * 60 * 24));
			System.out.println("DIFF IN DAYS: "+diffInDays);
			roomPrice += cost*diffInDays;
		}
		Query query2 = entityManager.createNativeQuery("SELECT * FROM BOOKING WHERE CUSTOMER = ? AND BREAKFAST=true", Booking.class);
		query2.setParameter(1, custid);
		List<Booking> roomList = query2.getResultList();
		int breakfastCost=roomList.size();
		return (breakfastCost*250)+roomPrice;
	}
}