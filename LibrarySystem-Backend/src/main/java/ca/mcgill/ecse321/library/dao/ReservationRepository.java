package ca.mcgill.ecse321.library.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.library.models.Reservation;

public interface ReservationRepository extends CrudRepository<Reservation, Integer>{
	
	Reservation findReservationByReservationID(int reservationID);

}
