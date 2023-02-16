package it.giuliafranzosiw2d5.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.giuliafranzosiw2d5.entities.Booking;

@Service
public class BookingService {
	
	@Autowired
	private BookingRepository bookingRepo;
	
	public void insert(Booking b) {
		bookingRepo.save(b);
	}
}
