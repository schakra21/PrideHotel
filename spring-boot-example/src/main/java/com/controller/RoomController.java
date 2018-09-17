package com.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.model.Room;
import com.repository.RoomRepository;

@RestController
public class RoomController {
	//Room room = new Room();
	private final RoomRepository roomRepository;

	RoomController(RoomRepository roomRepository) {
		this.roomRepository = roomRepository;
	}
	
	@GetMapping("/rooms")
    List<Room> retrieveAllRooms() {

		return roomRepository.findAll();
			
	}
	
	
}
