package com.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.model.Booking;
import com.model.Room;

public class BookingControllerTest extends AbstractTest {
   @Override
   @Before
   public void setUp() {
      super.setUp();
   }
   
   @Test
	public void retrieveAllBookings() throws Exception {
		String uri = "/bookings";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Booking[] bookingsList = super.mapFromJson(content, Booking[].class);
		assertTrue(bookingsList.length > 0);
	}
   
   @Test
   public void getBookingsByRoomIdTest() throws Exception {
	  createBookingTest();
      String uri = "/bookings/filter/2";
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
         .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
      
      int status = mvcResult.getResponse().getStatus();
      assertEquals(200, status);
      String content = mvcResult.getResponse().getContentAsString();
      Booking[] bookingByRoomIdList = super.mapFromJson(content, Booking[].class);
      assertTrue(bookingByRoomIdList.length > 0);
   }
   
   @Test
   public void getBookingsByCustomerIdTest() throws Exception {
	  createBookingTest();
      String uri = "/bookings/filter1/1";
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
         .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
      
      int status = mvcResult.getResponse().getStatus();
      assertEquals(200, status);
      String content = mvcResult.getResponse().getContentAsString();
      Booking[] bookingByCustomerIdList = super.mapFromJson(content, Booking[].class);
      assertTrue(bookingByCustomerIdList.length > 0);
   }
   
   @Test
   public void getBookingsByDateTest() throws Exception {
	  createBookingTest();
	 
      String uri = "/bookings/filter2/2018-12-12/2018-12-14/2";
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
         .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
      
      int status = mvcResult.getResponse().getStatus();
      assertEquals(200, status);
      String content = mvcResult.getResponse().getContentAsString();
      Booking[] bookingByDate = super.mapFromJson(content, Booking[].class);
      assertTrue(bookingByDate.length>0);
   }
   
   @Test
   public void createBookingTest() throws Exception {
      String uri = "/bookings";
      Room room = new Room();
      room.setRoomId(2);
      Room room1 = new Room();
      room1.setRoomId(1);
      Date startdate=new SimpleDateFormat("yyyy-MM-dd").parse("2018-12-12");
      Date enddate=new SimpleDateFormat("yyyy-MM-dd").parse("2018-12-14");
      Booking[] booking = new Booking[2];
      booking[0] = new Booking();
      booking[0].setRoom(room);
      booking[0].setCustomer(1);
      booking[0].setStartDate(startdate);
      booking[0].setEndDate(enddate);
      booking[0].setBreakfast(true);
      booking[1] = new Booking();
      booking[1].setRoom(room1);
      booking[1].setCustomer(2);
      booking[1].setStartDate(startdate);
      booking[1].setEndDate(enddate);
      booking[1].setBreakfast(true);
      String inputJson = super.mapToJson(booking);
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
         .contentType(MediaType.APPLICATION_JSON_VALUE)
         .content(inputJson)).andReturn();
      
      int status = mvcResult.getResponse().getStatus();
      assertEquals(201, status);
      String content = mvcResult.getResponse().getContentAsString();
      assertEquals(content, "Booking is created successfully");
   }
   @Test
   public void updateBooking() throws Exception {
	  createBookingTest();
	  Room room = new Room();
      room.setRoomId(2);
      String uri = "/bookings/1";
      Date startdate=new SimpleDateFormat("yyyy-MM-dd").parse("2018-12-12");
      Date enddate=new SimpleDateFormat("yyyy-MM-dd").parse("2018-12-14");
      Booking booking = new Booking();
     
      booking.setRoom(room);
	  booking.setCustomer(1);
	  booking.setStartDate(startdate);
	  booking.setEndDate(enddate);
	  booking.setBreakfast(false);
      String inputJson = super.mapToJson(booking);
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
         .contentType(MediaType.APPLICATION_JSON_VALUE)
         .content(inputJson)).andReturn();
      
      int status = mvcResult.getResponse().getStatus();
      assertEquals(200, status);
      String content = mvcResult.getResponse().getContentAsString();
      assertEquals(content, "Booking is updated successfully");
   }
   
   @Test
	public void getTotalCostOfCustomerTest() throws Exception {
		//String uri = "/bookings";
		  Room room = new Room();
		  room.setRoomId(2);
	      Date startdate=new SimpleDateFormat("yyyy-MM-dd").parse("2018-12-12");
	      Date enddate=new SimpleDateFormat("yyyy-MM-dd").parse("2018-12-14");
	      Booking booking = new Booking();
	      booking.setRoom(room);
	      booking.setCustomer(1);
	      booking.setStartDate(startdate);
	      booking.setEndDate(enddate);
	      booking.setBreakfast(true);
	      
		String uri1 = "/bookings/filter3/1";
		MvcResult mvcResult11 = mvc.perform(MockMvcRequestBuilders.get(uri1).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult11.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult11.getResponse().getContentAsString();
		Double roomCost = super.mapFromJson(content, Double.class);
		assertTrue(roomCost > 0);
	}
   
}
