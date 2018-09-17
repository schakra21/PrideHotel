package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "BOOKING")
public class Booking {
	@Id
	@GeneratedValue
	@Column(name = "BOOKING_ID")
	private Integer bookingId;
	@Column(name = "CUSTOMER")
	private Integer customerId;
	@ManyToOne
	/*@JoinColumns(foreignKey = @javax.persistence.ForeignKey(name = "ROOM"), value = {
			@JoinColumn(referencedColumnName = "ROOM_ID") })*/
	@JoinColumn(name = "ROOM_ID")
	private Room roomId;
	@Column(name = "START_DATE")
	private Date startDate;
	@Column(name = "END_DATE")
	private Date endDate;
	@Column(name = "BREAKFAST")
	private boolean breakfast;

	public Integer getBookingId() {
		return bookingId;
	}

	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}

	public Integer getCustomer() {
		return customerId;
	}

	public void setCustomer(Integer customerId) {
		this.customerId = customerId;
	}

	public Room getRoom() {
		return roomId;
	}

	public void setRoom(Room roomId) {
		this.roomId = roomId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public boolean getBreakfast() {
		return breakfast;
	}

	public void setBreakfast(boolean breakfast) {
		this.breakfast = breakfast;
	}
}
