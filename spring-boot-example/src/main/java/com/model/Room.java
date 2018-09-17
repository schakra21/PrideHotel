package com.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ROOM")
public class Room {

	@Id
	@GeneratedValue
	//@OneToMany(mappedBy="BOOKING", cascade = CascadeType.ALL, orphanRemoval = true)
	@Column(name = "ROOM_ID")
	private Integer roomId;
	@Column(name = "ROOM_PRICE")
	private Double roomPrice;
	@Column(name = "ROOM_TYPE")
	private String roomType;
	@Column(name = "ROOM_STATUS")
	private String roomStatus;
	

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}
	
	public Double getRoomPrice() {
		return roomPrice;
	}

	public void setRoomPrice(Double roomPrice) {
		this.roomPrice = roomPrice;
	}
	
	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	
	public String getRoomStatus() {
		return roomStatus;
	}

	public void setRoomStatus(String roomStatus) {
		this.roomStatus = roomStatus;
	}
}