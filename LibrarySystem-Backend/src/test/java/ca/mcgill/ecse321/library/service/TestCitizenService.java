package ca.mcgill.ecse321.library.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.lenient;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.library.dao.CitizenRepository;
import ca.mcgill.ecse321.library.models.Citizen;


@ExtendWith(MockitoExtension.class)
public class TestCitizenService {
	
	@Mock
	private CitizenRepository citizenRepository;
	
	@InjectMocks
	private CitizenService service;
	
	private static final String fullname = "Noshin Chowdhury";
	private static final String username = "KidA";
	private static final String password = "noshinKidA";
	private static final String address = "1650, YoWorld";
	private static final Boolean onlineAccountActive = true;
	private static final Boolean isLocal = false;
	private static final Double balance = 520.0;
	
	@BeforeEach
	public void setMockOutput() {
//		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
//			return invocation.getArgument(0);
//		};
//		lenient().when(citizenRepository.save(any(Citizen.class))).
//		thenAnswer(returnParameterAsAnswer);
		
		lenient().when(citizenRepository.findCitizenByCardID(anyLong())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(fullname)) {
				
				Citizen aCitizen = new Citizen();
				aCitizen.setFullName(fullname);
				aCitizen.setUsername(username);
				aCitizen.setPassword(password);
				aCitizen.setAddress(address);
				aCitizen.setOnlineAccountActivated(onlineAccountActive);
				aCitizen.setIsLocal(isLocal);
				aCitizen.setBalance(balance);

				return aCitizen;
			}
			return null;
		});
	}
	
	@Test
	public void testCreatCitizen() {
		String fullname = "Noshin Chowdhury";
		String username = "KidA";
		String password = "noshinKidA";
		String address = "1650, YoWorld";
		Boolean onlineAccountActive = true;
		Boolean isLocal = false;
		Double balance = 520.0;
		
		Citizen citizen = null;
		try {
			citizen = service.creatCitizen(fullname, username, password, address, onlineAccountActive, isLocal, balance);
		} catch(IllegalArgumentException e) {
			fail();
		}
		Long ID = citizen.getCardID();
		
		assertNotNull(citizen);
		assertEquals(fullname, citizen.getFullName());
		assertEquals(username, citizen.getUsername());
		assertEquals(password, citizen.getPassword());
		assertEquals(address, citizen.getAddress());
		assertEquals(onlineAccountActive, citizen.getOnlineAccountActivated());
		assertEquals(isLocal, citizen.getIsLocal());
		assertEquals(balance,citizen.getBalance());
		assertEquals(ID, citizen.getCardID());
	}
	
	@Test
	public void testCreateCitizenWithNoFullname() {
		String fullname = null;
		String username = "KidA";
		String password = "noshinKidA";
		String address = "1650, YoWorld";
		Boolean onlineAccountActive = true;
		Boolean isLocal = false;
		Double balance = 520.0;
		
		Citizen citizen = null;
		String error = "";
		try {
			citizen = service.creatCitizen(fullname, username, password, address, onlineAccountActive, isLocal, balance);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(citizen);
		assertEquals(error,"Fullname can't be empty");
	}
	
	@Test
	public void testCreateCitizenWithNoUsername() {
		String fullname = "Noshin Chowdhury";
		String username = null;
		String password = "noshinKidA";
		String address = "1650, YoWorld";
		Boolean onlineAccountActive = true;
		Boolean isLocal = false;
		Double balance = 520.0;
		
		Citizen citizen = null;
		String error = "";
		try {
			citizen = service.creatCitizen(fullname, username, password, address, onlineAccountActive, isLocal, balance);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(citizen);
		assertEquals(error,"Username can't be empty");
	}
	
	@Test
	public void testCreateCitizenWithPassword() {
		String fullname = "Noshin Chowdhury";
		String username = "KidA";
		String password = null;
		String address = "1650, YoWorld";
		Boolean onlineAccountActive = true;
		Boolean isLocal = false;
		Double balance = 520.0;
		
		Citizen citizen = null;
		String error = "";
		try {
			citizen = service.creatCitizen(fullname, username, password, address, onlineAccountActive, isLocal, balance);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(citizen);
		assertEquals(error,"password can't be empty");
	}
	
	@Test
	public void testCreateCitizenWithNoAddress() {
		String fullname = "Noshin Chowdhury";
		String username = "KidA";
		String password = "noshinKidA";
		String address = null;
		Boolean onlineAccountActive = true;
		Boolean isLocal = false;
		Double balance = 520.0;
		
		Citizen citizen = null;
		String error = "";
		try {
			citizen = service.creatCitizen(fullname, username, password, address, onlineAccountActive, isLocal, balance);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(citizen);
		assertEquals(error,"Address can't be empty");
	}
	
	@Test
	public void testCreateCitizenWithNoOnlineActive() {
		String fullname = "Noshin Chowdhury";
		String username = "KidA";
		String password = "noshinKidA";
		String address = "1650, YoWorld";
		Boolean onlineAccountActive = null;
		Boolean isLocal = false;
		Double balance = 520.0;
		
		Citizen citizen = null;
		String error = "";
		try {
			citizen = service.creatCitizen(fullname, username, password, address, onlineAccountActive, isLocal, balance);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(citizen);
		assertEquals(error,"onlineAccountActivated can't be empty");
	}
	
	@Test
	public void testCreateCitizenWithNoIsLocal() {
		String fullname = "Noshin Chowdhury";
		String username = "KidA";
		String password = "noshinKidA";
		String address = "1650, YoWorld";
		Boolean onlineAccountActive = true;
		Boolean isLocal = null;
		Double balance = 520.0;
		
		Citizen citizen = null;
		String error = "";
		try {
			citizen = service.creatCitizen(fullname, username, password, address, onlineAccountActive, isLocal, balance);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(citizen);
		assertEquals(error,"isLocal can't be empty");
	}
	
	@Test
	public void testCreateCitizenWithNoBalance() {
		String fullname = "Noshin Chowdhury";
		String username = "KidA";
		String password = "noshinKidA";
		String address = "1650, YoWorld";
		Boolean onlineAccountActive = true;
		Boolean isLocal = false;
		Double balance = null;
		
		Citizen citizen = null;
		String error = "";
		try {
			citizen = service.creatCitizen(fullname, username, password, address, onlineAccountActive, isLocal, balance);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(citizen);
		assertEquals(error,"Balance can't be empty");
	}
	@Test
	@Transactional
	public void getCitizenById() {
		String fullname = "Noshin Chowdhury";
		String username = "KidA";
		String password = "noshinKidA";
		String address = "1650, YoWorld";
		Boolean onlineAccountActive = true;
		Boolean isLocal = false;
		Double balance = 520.0;
		
		Citizen citizen = null;
		try {
			citizen = service.creatCitizen(fullname, username, password, address, onlineAccountActive, isLocal, balance);
		} catch(IllegalArgumentException e) {
			fail();
		}
		Long ID = citizen.getCardID();
		System.out.println(citizen.getFullName());
		System.out.println("the iD: "+ID);
		citizen = null;
		
		citizen = service.getCitizenByID(ID);
		
		assertNotNull(citizen);
		assertEquals(fullname, citizen.getFullName());
		assertEquals(username, citizen.getUsername());
		assertEquals(password, citizen.getPassword());
		assertEquals(address, citizen.getAddress());
		assertEquals(onlineAccountActive, citizen.getOnlineAccountActivated());
		assertEquals(isLocal, citizen.getIsLocal());
		assertEquals(balance,citizen.getBalance());
		assertEquals(ID, citizen.getCardID());
	}
	
}
