package ca.mcgill.ecse321.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.library.dao.ReservationRepository;
import ca.mcgill.ecse321.library.models.LibraryItem;
import ca.mcgill.ecse321.library.models.Reservation;
import ca.mcgill.ecse321.library.models.User;

@Service
public class ReservationService {
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@Transactional
	public Reservation createReservation(Long reservationID, User user, LibraryItem lt) {
		String error = "";
		
		if(reservationID == null || reservationID.equals("")) {
			error += "Reservation ID cannot be empty. ";
		}
		if(user == null || user.equals("")) {
			error += "User cannot be empty. ";
		}
		if(lt == null || lt.equals("")) {
			error += "Library item cannot be empty. ";
		}
		
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		
		Reservation r = new Reservation();
		r.setLibraryItem(lt);
		r.setReservationID(reservationID);
		r.setUser(user);
		
		reservationRepository.save(r);
		
		return r;
	}
	
	@Transactional
	public Reservation deleteReservation(Reservation r) {
		if(r == null) {
			throw new IllegalArgumentException("Input reservation cannot be null. ");
		}
		reservationRepository.delete(r);
		r = null;
		return r;
	}
	
	
// Doesn't make sense to update a reservation, just cancel the old one and make a new one	
//	@Transactional
//	public Reservation updateReservation() {
//		Reservation r = null;
//		
//		return r;
//	}
	
	
	@Transactional
	public List<Reservation> getAllReservation(){
		return (List<Reservation>) reservationRepository.findAll();
	}
	
	
	@Transactional
	public Reservation getReservation(Long reservationID) {
		Reservation r = reservationRepository.findReservationByReservationID(reservationID);
		return r;
	}
}
