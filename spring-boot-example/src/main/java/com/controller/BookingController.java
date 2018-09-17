package com.controller;

import java.text.ParseException;
import java.util.Iterator;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Booking;
import com.model.Room;
import com.repository.BookingRepository;

@RestController
public class BookingController {
	private final BookingRepository bookingRepository;

	public BookingController(BookingRepository bookingRepository) {
		this.bookingRepository = bookingRepository;
	}
	@GetMapping("/bookings")
    List<Booking> retrieveAllBookings() {

		return bookingRepository.findAll();
			
	}
	@PostMapping("/bookings")
	ResponseEntity<Object> createBooking(@RequestBody Booking[] newBooking) {
		/*boolean booking = true;		
		List<Booking> rooms=retrieveAllBookings();
		System.out.println("BOOKINGS: "+rooms.toString());
		Iterator<Booking> roomsIter= rooms.iterator();
		for (int j = 0; j < newBooking.length; j++) {
			if (roomsIter.hasNext()) {
				Integer room = roomsIter.next().getRoom().getRoomId();
				System.out.println("ROOMID: "+room);
				if(room==newBooking[j].getRoom().getRoomId()) {
					booking=false;
				}
			}
		}*/
		
	/*	for (int j = 1; j < newBooking.length; j++) {
			for (int k = 0; k < j; k++) {
				System.out.println("ROOM ID K:"+newBooking[k].getRoom().getRoomId());
				System.out.println("ROOM ID J:"+newBooking[j].getRoom().getRoomId());
				System.out.println("END DATE K"+newBooking[k].getEndDate());
				System.out.println("END DATE J"+newBooking[j].getEndDate());
				System.out.println("START DATE K"+newBooking[k].getStartDate());
				System.out.println("START DATE J"+newBooking[j].getStartDate());
				System.out.println("CUSTOMER ID K:"+newBooking[k].getCustomer());
				System.out.println("CUSTOMER ID J:"+newBooking[j].getCustomer());
				if (newBooking[k].getRoom().getRoomId() == newBooking[j].getRoom().getRoomId()
						&& newBooking[k].getEndDate() == newBooking[j].getEndDate()
						&& newBooking[k].getStartDate() == newBooking[j].getStartDate()
						&& newBooking[k].getCustomer() != newBooking[j].getCustomer()) {
					System.out.println("J loop");

					booking = false;
				}
			}
		}*/

		
		/*if (booking == false) {
			return new ResponseEntity<>("Booking failed, room already booked", HttpStatus.NOT_ACCEPTABLE);
		} else {*/
			for (int i = 0; i < newBooking.length; i++) {
				bookingRepository.save(newBooking[i]);
			}
			return new ResponseEntity<>("Booking is created successfully", HttpStatus.CREATED);
		//}
	}

	@RequestMapping("/bookings/filter/{roomid}")
	public List<Booking> getFilteredRoom(@PathVariable Integer roomid) {
		return bookingRepository.getBookingsByRoomId(roomid);
	}

	@RequestMapping("/bookings/filter1/{custid}")
	public List<Booking> getFilteredCustomer(@PathVariable Integer custid) {
		return bookingRepository.getBookingsByCustomerId(custid);
	}

	@RequestMapping("/bookings/filter2/{startDate}/{endDate}/{room}")
	public List<Booking> getFilteredDate(@PathVariable String startDate, @PathVariable String endDate,
			@PathVariable Integer room) throws ParseException {
		return bookingRepository.getBookingsByDate(startDate, endDate, room);
	}

	@PutMapping("/bookings/{id}")
	public ResponseEntity<Object> updateBooking(@RequestBody Booking newBooking, @PathVariable Integer id) {

		if (!bookingRepository.exists(id)) {
			return ResponseEntity.notFound().build();
		}
		bookingRepository.delete(id);
		newBooking.setBookingId(id);
		bookingRepository.save(newBooking);
		return new ResponseEntity<>("Booking is updated successfully", HttpStatus.OK);
	}

	@RequestMapping("/bookings/filter3/{custid}")
	public double getTotalCostOfCustomerTest(@PathVariable Integer custid) {
		return bookingRepository.getTotalCostOfCustomer(custid);
	}
}
