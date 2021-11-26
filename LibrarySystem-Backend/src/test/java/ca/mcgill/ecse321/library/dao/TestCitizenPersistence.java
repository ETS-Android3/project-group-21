package ca.mcgill.ecse321.library.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.transaction.Transactional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.library.models.Citizen;
import ca.mcgill.ecse321.library.models.ApplicationUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestCitizenPersistence{
	@Autowired
	private CitizenRepository citizenrepository;
	
	@AfterEach
	public void clearDataBase() {
		citizenrepository.deleteAll();
	}
	
	@Test
	@Transactional
	public void testPersistAndLoadCitizen() {
		Citizen aCitizen = new Citizen();
		String name = "Noshin Chowdhury";

		String address = "YoWorld";
		String ApplicationUsername = "KidA";
		String password = "KidArocks12138";
		Boolean onlineAccountActivated = true;
		Boolean isLocal = false;
		Double balance = 121.38;
		Long cardID = 1234L;
		
		aCitizen.setFullName(name);
		aCitizen.setCardID(cardID);
		aCitizen.setAddress(address);
		aCitizen.setUsername(ApplicationUsername);
		aCitizen.setPassword(password);
		aCitizen.setOnlineAccountActivated(onlineAccountActivated);
		aCitizen.setIsLocal(isLocal);
		aCitizen.setBalance(balance);
		
	    citizenrepository.save(aCitizen);
	    
		
		aCitizen = null;
		aCitizen = citizenrepository.findCitizenByCardID(cardID);
		
		assertNotNull(aCitizen);
		assertEquals(name,aCitizen.getFullName());
		assertEquals(cardID,aCitizen.getCardID());
		assertEquals(address,aCitizen.getAddress());
		assertEquals(password,aCitizen.getPassword());
		assertEquals(onlineAccountActivated,aCitizen.getOnlineAccountActivated());
		assertEquals(isLocal,aCitizen.getIsLocal());
		assertEquals(balance,aCitizen.getBalance());
		assertEquals(cardID,aCitizen.getCardID());
	}
	
}