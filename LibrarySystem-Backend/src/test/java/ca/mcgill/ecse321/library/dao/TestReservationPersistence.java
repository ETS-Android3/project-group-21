package ca.mcgill.ecse321.library.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.library.models.*;
import ca.mcgill.ecse321.library.models.LibraryItem.ItemType;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestReservationPersistence {
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private LibraryItemRepository libraryItemRepository;
	
	@Autowired
	private CitizenRepository citizenRepository;
	
	@AfterEach
	public void clearDatabase() {
		reservationRepository.deleteAll();
		libraryItemRepository.deleteAll();
		citizenRepository.deleteAll();
	}
	
	@Test
	@Transactional
	public void testPersistAndLoadReservation() {

		// initialize and set attributes to the reservation
		// initialize a library item
		LibraryItem li = new LibraryItem();
//		ItemType type = ItemType.Book;
//		String title = "How to manage the library system";
//		boolean isReservable = true;
//		boolean isReserved = false;
//		int loadPeriod = 21;
//		Long cardID = 123L;
//		li.setType(type);
//		li.setTitle(title);
//		li.setIsReservable(isReservable);
//		li.setIsReserved(isReserved);
//		li.setLoanPeriod(loadPeriod);	
		li.setBarcode(234L);
		libraryItemRepository.save(li); 
//		Long barcode = li.getBarcode();
		
		// initialize a citizen
		Citizen c = new Citizen();
		c.setCardID(456L);
		citizenRepository.save(c);
//		long userId = c.getCardID();

		// initialize a reservation and associate the library item and citizen to it
		Long reservationId = 123L;
		Reservation r = new Reservation();
		r.setLibraryItem(li);
		r.setUser(c);	
		r.setReservationID(reservationId);
		reservationRepository.save(r);// By saving, a unique reservation ID is assigned to the reservation (no need to set it ourself)
		
		
		
		r = null;

		r = reservationRepository.findReservationByReservationID(reservationId);
		assertNotNull(r);
		assertEquals(reservationId, r.getReservationID());
		//assertEquals(barcode, r.getLibraryItem().getBarcode());
		//assertEquals(userId, r.getUser().getCardID());
	}

}
