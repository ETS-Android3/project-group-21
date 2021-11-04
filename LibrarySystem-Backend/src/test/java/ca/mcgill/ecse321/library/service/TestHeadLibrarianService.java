package ca.mcgill.ecse321.library.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.lenient;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.library.dao.HeadLibrarianRepository;
import ca.mcgill.ecse321.library.models.HeadLibrarian;


@ExtendWith(MockitoExtension.class)
public class TestHeadLibrarianService {
	
	@Mock
	private HeadLibrarianRepository headLibrarianRepository;
	
	@InjectMocks
	private HeadLibrarianService service;
	
	private static final String fullname = "Tony Stark";
	private static final String username = "Ironman";
	private static final String password = "abc";
	private static final String address = "NewYork";
	private static final Boolean onlineAccountActive = true; // headlibrarian's account is always activated
	private static final Long cardID = 123L;
	private static final Long wrongID = 456L;
	
	@BeforeEach
	public void setMockOutput() {

		
		lenient().when(headLibrarianRepository.findHeadLibrarianByCardID(anyLong())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(cardID)) {
				
				HeadLibrarian aHeadLibrarian = new HeadLibrarian();
				aHeadLibrarian.setFullName(fullname);
				aHeadLibrarian.setUsername(username);
				aHeadLibrarian.setPassword(password);
				aHeadLibrarian.setAddress(address);
				aHeadLibrarian.setOnlineAccountActivated(onlineAccountActive);// headlibrarian's account is always activated
				aHeadLibrarian.setCardID(cardID);

				return aHeadLibrarian;
			} else {
			return null;
			}
		});

	}
	
	@Test
	public void testCreatHeadLibrarian() {

		String fullname = "Tony Stark";
		String username = "Ironman";
		String password = "abc";
		String address = "NewYork";
		Long cardID = 123L;
		
		HeadLibrarian HeadLibrarian = null;
		try {
			HeadLibrarian = service.createHeadlibrarian(fullname, username, password, address,cardID);
		} catch(IllegalArgumentException e) {
			fail();
		}

	//	System.out.println("HeadLibrarian:" + HeadLibrarianRepository.findHeadLibrarianByCardID(cardID).getFullName());
		assertNotNull(HeadLibrarian);
		assertEquals(fullname, HeadLibrarian.getFullName());
		assertEquals(username, HeadLibrarian.getUsername());
		assertEquals(password, HeadLibrarian.getPassword());
		assertEquals(address, HeadLibrarian.getAddress());
		assertEquals(onlineAccountActive, HeadLibrarian.getOnlineAccountActivated());

		assertEquals(cardID, HeadLibrarian.getCardID());
	}
	
	@Test
	public void testCreatHeadLibrarianWithNoFullname() {

		String fullname = null;
		String username = "Ironman";
		String password = "abc";
		String address = "NewYork";
		Long cardID = 123L;
		
		HeadLibrarian HeadLibrarian = null;
		String error = "";
		try {
			HeadLibrarian = service.createHeadlibrarian(fullname, username, password, address,cardID);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(HeadLibrarian);
		assertEquals(error,"Fullname can't be empty");
	
	}
	
	@Test
	public void testCreatHeadLibrarianWithNoUsername() {

		String fullname = "Tony Stark";
		String username = null;
		String password = "abc";
		String address = "NewYork";
		Long cardID = 123L;
		
		HeadLibrarian HeadLibrarian = null;
		String error = "";
		try {
			HeadLibrarian = service.createHeadlibrarian(fullname, username, password, address,cardID);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(HeadLibrarian);
		assertEquals(error,"Username can't be empty");

	}
	
	@Test
	public void testCreatHeadLibrarianWithNoPassword() {

		String fullname = "Tony Stark";
		String username = "Ironman";
		String password = null;
		String address = "NewYork";
		Long cardID = 123L;
		
		HeadLibrarian HeadLibrarian = null;
		String error = "";
		try {
			HeadLibrarian = service.createHeadlibrarian(fullname, username, password, address,cardID);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(HeadLibrarian);
		assertEquals(error,"Password can't be empty");

	}
	
	@Test
	public void testCreatHeadLibrarianWithNoAddress() {

		String fullname = "Tony Stark";
		String username = "Ironman";
		String password = "abc";
		String address = null;
		Long cardID = 123L;
		
		HeadLibrarian HeadLibrarian = null;
		String error = "";
		try {
			HeadLibrarian = service.createHeadlibrarian(fullname, username, password, address,cardID);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(HeadLibrarian);
		assertEquals(error,"Address can't be empty");

	}
	
	@Test
	public void testDeleteHeadLibrarian() {
		String fullname = "Tony Stark";
		String username = "Ironman";
		String password = "abc";
		String address = "NewYork";
		Long cardID = 123L;
		
		HeadLibrarian HeadLibrarian = null;
		try {
			HeadLibrarian = service.createHeadlibrarian(fullname, username, password, address,cardID);
		} catch(IllegalArgumentException e) {
			fail();
		}
		try {
		HeadLibrarian=service.deleteHeadLibrarian(HeadLibrarian);
		} catch(IllegalArgumentException e) {
			fail();
		}
		assertNull(HeadLibrarian);
			
		//System.out.println(service.getHeadLibrarianByID(cardID).getFullName());
	}
	
	@Test
	public void testUpdateHeadLibrarianFullname() {
		String fullname = "Tony Stark";
		String username = "Ironman";
		String password = "abc";
		String address = "NewYork";
		Long cardID = 123L;
		
		HeadLibrarian HeadLibrarian = null;
		try {
			HeadLibrarian = service.createHeadlibrarian(fullname, username, password, address,cardID);
		} catch(IllegalArgumentException e) {
			fail();
		}
		String newname="Petter Parker";
		try {
		HeadLibrarian=service.editHeadLibrarianFullName(HeadLibrarian, newname);
		} catch(IllegalArgumentException e) {
			fail();
		}
		assertNotNull(HeadLibrarian);
		assertEquals(newname, HeadLibrarian.getFullName());
		
		
			
	}
	
	@Test
	public void testUpdateHeadLibrarianFullnameWithNull() {
		String fullname = "Tony Stark";
		String username = "Ironman";
		String password = "abc";
		String address = "NewYork";
		Long cardID = 123L;
		String error="";
		
		HeadLibrarian HeadLibrarian = null;
		try {
			HeadLibrarian = service.createHeadlibrarian(fullname, username, password, address,cardID);
		} catch(IllegalArgumentException e) {
			fail();
		}
		String newname=null;
		try {
			HeadLibrarian=service.editHeadLibrarianFullName(HeadLibrarian, newname);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNotNull(HeadLibrarian);
		assertEquals(fullname, HeadLibrarian.getFullName());
		assertEquals(error,"fullname can't be empty");
			
	}
	
	
	
	@Test
	public void testUpdateHeadLibrarianUsername() {
		String fullname = "Tony Stark";
		String username = "Ironman";
		String password = "abc";
		String address = "NewYork";
		Long cardID = 123L;

		
		HeadLibrarian HeadLibrarian = null;
		try {
			HeadLibrarian = service.createHeadlibrarian(fullname, username, password, address,cardID);
		} catch(IllegalArgumentException e) {
			fail();
		}
		String newname="Spiderman";
		try {
			HeadLibrarian=service.editHeadLibrarianUserName(HeadLibrarian, newname);
		} catch(IllegalArgumentException e) {
			fail();
		}
		assertNotNull(HeadLibrarian);
		assertEquals(newname, HeadLibrarian.getUsername());
			
	}
	
	@Test
	public void testUpdateHeadLibrarianUsernameWithNull() {
		String fullname = "Tony Stark";
		String username = "Ironman";
		String password = "abc";
		String address = "NewYork";
		Long cardID = 123L;
		String error="";
		
		HeadLibrarian HeadLibrarian = null;
		try {
			HeadLibrarian = service.createHeadlibrarian(fullname, username, password, address,cardID);
		} catch(IllegalArgumentException e) {
			fail();
		}
		String newname=null;
		try {
			HeadLibrarian=service.editHeadLibrarianUserName(HeadLibrarian, newname);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNotNull(HeadLibrarian);
		assertEquals(username, HeadLibrarian.getUsername());
		assertEquals(error,"username can't be empty");
			
	}
	
	@Test
	public void testUpdateHeadLibrarianPassword() {
		String fullname = "Tony Stark";
		String username = "Ironman";
		String password = "abc";
		String address = "NewYork";
		Long cardID = 123L;
		
		HeadLibrarian HeadLibrarian = null;
		try {
			HeadLibrarian = service.createHeadlibrarian(fullname, username, password, address,cardID);
		} catch(IllegalArgumentException e) {
			fail();
		}
		String newname="zxc";
		try {
			HeadLibrarian=service.editHeadLibrarianPassword(HeadLibrarian, newname);
		} catch(IllegalArgumentException e) {
			fail();
		}
		assertNotNull(HeadLibrarian);
		assertEquals(newname, HeadLibrarian.getPassword());
			
	}
	
	@Test
	public void testUpdateHeadLibrarianPasswordWithNull() {
		String fullname = "Tony Stark";
		String username = "Ironman";
		String password = "abc";
		String address = "NewYork";
		Long cardID = 123L;
		String error="";
		
		HeadLibrarian HeadLibrarian = null;
		try {
			HeadLibrarian = service.createHeadlibrarian(fullname, username, password, address,cardID);
		} catch(IllegalArgumentException e) {
			fail();
		}
		String newname=null;
		try {
			HeadLibrarian=service.editHeadLibrarianPassword(HeadLibrarian, newname);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNotNull(HeadLibrarian);
		assertEquals(password, HeadLibrarian.getPassword());
		assertEquals(error,"password can't be empty");
			
	}
	
	@Test
	public void testUpdateHeadLibrarianAddress() {
		String fullname = "Tony Stark";
		String username = "Ironman";
		String password = "abc";
		String address = "NewYork";
		Long cardID = 123L;
		
		HeadLibrarian HeadLibrarian = null;
		try {
			HeadLibrarian = service.createHeadlibrarian(fullname, username, password, address,cardID);
		} catch(IllegalArgumentException e) {
			fail();
		}
		String newname="Canada";
		try {
			HeadLibrarian=service.editHeadLibrarianAddress(HeadLibrarian, newname);
		} catch(IllegalArgumentException e) {
			fail();
		}
		assertNotNull(HeadLibrarian);
		assertEquals(newname, HeadLibrarian.getAddress());
			
	}
	
	@Test
	public void testUpdateHeadLibrarianAddressWithNull() {
		String fullname = "Tony Stark";
		String username = "Ironman";
		String password = "abc";
		String address = "NewYork";
		Long cardID = 123L;
		String error="";
		
		HeadLibrarian HeadLibrarian = null;
		try {
			HeadLibrarian = service.createHeadlibrarian(fullname, username, password, address,cardID);
		} catch(IllegalArgumentException e) {
			fail();
		}
		String newname=null;
		try {
			HeadLibrarian=service.editHeadLibrarianAddress(HeadLibrarian, newname);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNotNull(HeadLibrarian);
		assertEquals(address, HeadLibrarian.getAddress());
		assertEquals(error,"address can't be empty");
			
	}
	
	@Test
	public void testGetExistingHeadLibrarian() {
		assertEquals(cardID, service.getHeadLibrarianByID(cardID).getCardID());
	}

	@Test
	public void testGetNonExistingPerson() {
		assertNull(service.getHeadLibrarianByID(wrongID));
	}

}
