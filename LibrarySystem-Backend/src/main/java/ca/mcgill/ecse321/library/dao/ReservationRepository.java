package ca.mcgill.ecse321.library.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.library.models.LibraryItem;
import ca.mcgill.ecse321.library.models.Reservation;
import ca.mcgill.ecse321.library.models.ApplicationUser;

public interface ReservationRepository extends CrudRepository<Reservation, Long>{
	
	Reservation findReservationByReservationID(Long reservationID);
	Reservation findByLibraryItem(LibraryItem libraryItem);
	List<Reservation> findByApplicationUser(ApplicationUser ApplicationUser);

}
