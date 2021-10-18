package ca.mcgill.ecse321.library.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.library.models.Reservation;
import ca.mcgill.ecse321.library.models.User;

public interface UserRepository extends CrudRepository<User, Long>{
	
	User findCitizenByCardID(Long cardID);
	User findUserByReservation(Reservation reservation);

}