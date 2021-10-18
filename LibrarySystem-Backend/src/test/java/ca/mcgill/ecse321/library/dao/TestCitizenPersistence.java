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
import ca.mcgill.ecse321.library.models.User;

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
	public void testPersistAndLoadUser() {
		Citizen aCitizen = new Citizen();
		String name = "Noshin Chowdhury";
		Long cardID = (long) 267901544;
		String address = "YoWorld";
		String username = "KidA";
		String password = "KidArocks12138";
		Boolean onlineAccountActivated = true;
		Boolean isLocal = false;
		Double balance = 121.38;
		
		aCitizen.setFullName(name);
		aCitizen.setCardID(cardID);
		aCitizen.setAddress(address);
		aCitizen.setUsername(username);
		aCitizen.setPassword(password);
		aCitizen.setOnlineAccountActivated(onlineAccountActivated);
		aCitizen.setIsLocal(isLocal);
		aCitizen.setBalance(balance);
		
	    citizenrepository.save(aCitizen);
		Long id = aCitizen.getCardID();
		
		aCitizen = null;
		aCitizen = (Citizen) citizenrepository.findCitizenByCardID(id);
		
		assertNotNull(aCitizen);
		assertEquals(name,aCitizen.getFullName());
		assertEquals(cardID,aCitizen.getCardID());
		assertEquals(address,aCitizen.getAddress());
		assertEquals(password,aCitizen.getPassword());
		assertEquals(onlineAccountActivated,aCitizen.getOnlineAccountActivated());
		assertEquals(isLocal,aCitizen.getIsLocal());
		assertEquals(balance,aCitizen.getBalance());
	}
	
}