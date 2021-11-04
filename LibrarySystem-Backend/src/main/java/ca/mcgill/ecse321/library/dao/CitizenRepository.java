package ca.mcgill.ecse321.library.dao;

import org.springframework.data.repository.CrudRepository;


import ca.mcgill.ecse321.library.models.Citizen;

public interface CitizenRepository extends CrudRepository<Citizen, Long>{
	
	Citizen findCitizenByCardID(Long cardID);

	Citizen findCitizenByUsernameAndPassword(String username,String password);

}
