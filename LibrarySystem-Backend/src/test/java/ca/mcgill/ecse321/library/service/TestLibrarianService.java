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

import ca.mcgill.ecse321.library.dao.LibrarianRepository;
import ca.mcgill.ecse321.library.models.Librarian;


@ExtendWith(MockitoExtension.class)
public class TestLibrarianService {
	
	@Mock
	private LibrarianRepository LibrarianRepository;
	
	@InjectMocks
	private LibrarianService service;
	
	private static final String fullname = "Tony Stark";
	private static final String username = "Ironman";
	private static final String password = "abc";
	private static final String address = "NewYork";
	private static final Boolean onlineAccountActive = true; // Librarian's account is always activated
	private static final Long cardID = 123L;
	private static final Long wrongID = 456L;
	
	@BeforeEach
	public void setMockOutput() {

		
		lenient().when(LibrarianRepository.findLibrarianByCardID(anyLong())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(cardID)) {
				
				Librarian aLibrarian = new Librarian();
				aLibrarian.setFullName(fullname);
				aLibrarian.setUsername(username);
				aLibrarian.setPassword(password);
				aLibrarian.setAddress(address);
				aLibrarian.setOnlineAccountActivated(onlineAccountActive);// Librarian's account is always activated
				aLibrarian.setCardID(cardID);

				return aLibrarian;
			} else {
			return null;
			}
		});

	}
	
	@Test
	public void testCreatLibrarian() {
		assertEquals(0, service.getAllLibrarian().size());

		String fullname = "Tony Stark";
		String username = "Ironman";
		String password = "abc";
		String address = "NewYork";
		Long cardID = 123L;
		
		Librarian Librarian = null;
		try {
			Librarian = service.createLibrarian(fullname, username, password, address,cardID);
		} catch(IllegalArgumentException e) {
			fail();
		}

	//	System.out.println("Librarian:" + LibrarianRepository.findLibrarianByCardID(cardID).getFullName());
		assertNotNull(Librarian);
		assertEquals(fullname, Librarian.getFullName());
		assertEquals(username, Librarian.getUsername());
		assertEquals(password, Librarian.getPassword());
		assertEquals(address, Librarian.getAddress());
		assertEquals(onlineAccountActive, Librarian.getOnlineAccountActivated());

		assertEquals(cardID, Librarian.getCardID());
	}
	
	@Test
	public void testCreatLibrarianWithNoFullname() {
		assertEquals(0, service.getAllLibrarian().size());

		String fullname = null;
		String username = "Ironman";
		String password = "abc";
		String address = "NewYork";
		Long cardID = 123L;
		
		Librarian Librarian = null;
		String error = "";
		try {
			Librarian = service.createLibrarian(fullname, username, password, address,cardID);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(Librarian);
		assertEquals(error,"Fullname can't be empty");
	
	}
	
	@Test
	public void testCreatLibrarianWithNoUsername() {
		assertEquals(0, service.getAllLibrarian().size());

		String fullname = "Tony Stark";
		String username = null;
		String password = "abc";
		String address = "NewYork";
		Long cardID = 123L;
		
		Librarian Librarian = null;
		String error = "";
		try {
			Librarian = service.createLibrarian(fullname, username, password, address,cardID);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(Librarian);
		assertEquals(error,"Username can't be empty");

	}
	
	@Test
	public void testCreatLibrarianWithNoPassword() {
		assertEquals(0, service.getAllLibrarian().size());

		String fullname = "Tony Stark";
		String username = "Ironman";
		String password = null;
		String address = "NewYork";
		Long cardID = 123L;
		
		Librarian Librarian = null;
		String error = "";
		try {
			Librarian = service.createLibrarian(fullname, username, password, address,cardID);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(Librarian);
		assertEquals(error,"Password can't be empty");

	}
	
	@Test
	public void testCreatLibrarianWithNoAddress() {
		assertEquals(0, service.getAllLibrarian().size());

		String fullname = "Tony Stark";
		String username = "Ironman";
		String password = "abc";
		String address = null;
		Long cardID = 123L;
		
		Librarian Librarian = null;
		String error = "";
		try {
			Librarian = service.createLibrarian(fullname, username, password, address,cardID);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(Librarian);
		assertEquals(error,"Address can't be empty");

	}
	
	@Test
	public void testDeleteLibrarian() {
		String fullname = "Tony Stark";
		String username = "Ironman";
		String password = "abc";
		String address = "NewYork";
		Long cardID = 123L;
		
		Librarian Librarian = null;
		try {
			Librarian = service.createLibrarian(fullname, username, password, address,cardID);
		} catch(IllegalArgumentException e) {
			fail();
		}
		try {
		Librarian=service.deleteLibrarian(Librarian);
		} catch(IllegalArgumentException e) {
			fail();
		}
		assertNull(Librarian);
			
		//System.out.println(service.getLibrarianByID(cardID).getFullName());
	}
	
	@Test
	public void testUpdateLibrarianFullname() {
		String fullname = "Tony Stark";
		String username = "Ironman";
		String password = "abc";
		String address = "NewYork";
		Long cardID = 123L;
		
		Librarian Librarian = null;
		try {
			Librarian = service.createLibrarian(fullname, username, password, address,cardID);
		} catch(IllegalArgumentException e) {
			fail();
		}
		String newname="Petter Parker";
		try {
		Librarian=service.editLibrarianFullName(Librarian, newname);
		} catch(IllegalArgumentException e) {
			fail();
		}
		assertNotNull(Librarian);
		assertEquals(newname, Librarian.getFullName());
		
		//System.out.println(service.getLibrarianByID(cardID).getFullName());
			
	}
	
	@Test
	public void testUpdateLibrarianFullnameWithNull() {
		String fullname = "Tony Stark";
		String username = "Ironman";
		String password = "abc";
		String address = "NewYork";
		Long cardID = 123L;
		String error="";
		
		Librarian Librarian = null;
		try {
			Librarian = service.createLibrarian(fullname, username, password, address,cardID);
		} catch(IllegalArgumentException e) {
			fail();
		}
		String newname=null;
		try {
			Librarian=service.editLibrarianFullName(Librarian, newname);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNotNull(Librarian);
		assertEquals(fullname, Librarian.getFullName());
		assertEquals(error,"fullname can't be empty");
			
	}
	
	
	
	@Test
	public void testUpdateLibrarianUsername() {
		String fullname = "Tony Stark";
		String username = "Ironman";
		String password = "abc";
		String address = "NewYork";
		Long cardID = 123L;

		
		Librarian Librarian = null;
		try {
			Librarian = service.createLibrarian(fullname, username, password, address,cardID);
		} catch(IllegalArgumentException e) {
			fail();
		}
		String newname="Spiderman";
		try {
			Librarian=service.editLibrarianUserName(Librarian, newname);
		} catch(IllegalArgumentException e) {
			fail();
		}
		assertNotNull(Librarian);
		assertEquals(newname, Librarian.getUsername());
			
	}
	
	@Test
	public void testUpdateLibrarianUsernameWithNull() {
		String fullname = "Tony Stark";
		String username = "Ironman";
		String password = "abc";
		String address = "NewYork";
		Long cardID = 123L;
		String error="";
		
		Librarian Librarian = null;
		try {
			Librarian = service.createLibrarian(fullname, username, password, address,cardID);
		} catch(IllegalArgumentException e) {
			fail();
		}
		String newname=null;
		try {
			Librarian=service.editLibrarianUserName(Librarian, newname);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNotNull(Librarian);
		assertEquals(username, Librarian.getUsername());
		assertEquals(error,"username can't be empty");
			
	}
	
	@Test
	public void testUpdateLibrarianPassword() {
		String fullname = "Tony Stark";
		String username = "Ironman";
		String password = "abc";
		String address = "NewYork";
		Long cardID = 123L;
		
		Librarian Librarian = null;
		try {
			Librarian = service.createLibrarian(fullname, username, password, address,cardID);
		} catch(IllegalArgumentException e) {
			fail();
		}
		String newname="zxc";
		try {
			Librarian=service.editLibrarianPassword(Librarian, newname);
		} catch(IllegalArgumentException e) {
			fail();
		}
		assertNotNull(Librarian);
		assertEquals(newname, Librarian.getPassword());
			
	}
	
	@Test
	public void testUpdateLibrarianPasswordWithNull() {
		String fullname = "Tony Stark";
		String username = "Ironman";
		String password = "abc";
		String address = "NewYork";
		Long cardID = 123L;
		String error="";
		
		Librarian Librarian = null;
		try {
			Librarian = service.createLibrarian(fullname, username, password, address,cardID);
		} catch(IllegalArgumentException e) {
			fail();
		}
		String newname=null;
		try {
			Librarian=service.editLibrarianPassword(Librarian, newname);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNotNull(Librarian);
		assertEquals(password, Librarian.getPassword());
		assertEquals(error,"password can't be empty");
			
	}
	
	@Test
	public void testUpdateLibrarianAddress() {
		String fullname = "Tony Stark";
		String username = "Ironman";
		String password = "abc";
		String address = "NewYork";
		Long cardID = 123L;
		
		Librarian Librarian = null;
		try {
			Librarian = service.createLibrarian(fullname, username, password, address,cardID);
		} catch(IllegalArgumentException e) {
			fail();
		}
		String newname="Canada";
		try {
			Librarian=service.editLibrarianAddress(Librarian, newname);
		} catch(IllegalArgumentException e) {
			fail();
		}
		assertNotNull(Librarian);
		assertEquals(newname, Librarian.getAddress());
			
	}
	
	@Test
	public void testUpdateLibrarianAddressWithNull() {
		String fullname = "Tony Stark";
		String username = "Ironman";
		String password = "abc";
		String address = "NewYork";
		Long cardID = 123L;
		String error="";
		
		Librarian Librarian = null;
		try {
			Librarian = service.createLibrarian(fullname, username, password, address,cardID);
		} catch(IllegalArgumentException e) {
			fail();
		}
		String newname=null;
		try {
			Librarian=service.editLibrarianAddress(Librarian, newname);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNotNull(Librarian);
		assertEquals(address, Librarian.getAddress());
		assertEquals(error,"address can't be empty");
			
	}
	
	
	@Test
	public void testGetExistingLibrarian() {
		assertEquals(cardID, service.getLibrarianByID(cardID).getCardID());
	}

	@Test
	public void testGetNonExistingLibrarian() {
		assertNull(service.getLibrarianByID(wrongID));
	}
}
