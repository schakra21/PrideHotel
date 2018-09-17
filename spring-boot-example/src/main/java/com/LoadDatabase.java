/*package com;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.model.Room;
import com.repository.RoomRepository;

@Configuration
@Slf4j
public class LoadDatabase {

	@Bean
	CommandLineRunner initDatabase(RoomRepository roomrepository) {
		Room room = new Room();
		return args -> {
			log.info("Preloading " + roomrepository.save(room.setRoomPrice(7000.00),room.setRoomType("Single"),room.setRoomStatus("Available")));
			log.info("Preloading " + roomrepository.save(room.setRoomPrice(7000.00),room.setRoomType("Single"),room.setRoomStatus("Available")));
			log.info("Preloading " + roomrepository.save(room.setRoomPrice(10000.00),room.setRoomType("Double"),room.setRoomStatus("Available")));
			log.info("Preloading " + roomrepository.save(room.setRoomPrice(10000.00),room.setRoomType("Double"),room.setRoomStatus("Available")));
			log.info("Preloading " + roomrepository.save(room.setRoomPrice(10000.00),room.setRoomType("Double"),room.setRoomStatus("Available")));
		};
	}
}
*/