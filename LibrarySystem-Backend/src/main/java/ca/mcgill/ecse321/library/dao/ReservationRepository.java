package ca.mcgill.ecse321.library.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.library.models.LibraryItem;
import ca.mcgill.ecse321.library.models.Reservation;
import ca.mcgill.ecse321.library.models.User;

public interface ReservationRepository extends CrudRepository<Reservation, Integer>{
	
	Reservation findReservationByReservationID(int reservationID);
	Reservation findByLibraryItem(LibraryItem libraryItem);
	List<Reservation> findByUser(User user);

}
