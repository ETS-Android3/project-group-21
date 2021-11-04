package ca.mcgill.ecse321.library.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.lenient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;

import ca.mcgill.ecse321.library.dao.LibraryItemRepository;
import ca.mcgill.ecse321.library.dao.ReservationRepository;
import ca.mcgill.ecse321.library.models.Citizen;
import ca.mcgill.ecse321.library.models.LibraryItem;
import ca.mcgill.ecse321.library.models.Reservation;
import ca.mcgill.ecse321.library.models.User;


@ExtendWith(MockitoExtension.class)
public class TestReservationService {

	@Mock
	private ReservationRepository reservationRepository;

	@InjectMocks
	private ReservationService reservationService;
	
	private static final Long rId = 1230l;
	private static final Long nonExistingRId = 111111l;
	private static final LibraryItem li = new LibraryItem();
	private static final User user = new Citizen();
	
	@BeforeEach
	public void setMockOutput() {
		lenient().when(reservationRepository.findReservationByReservationID(anyLong())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(rId)) {
				
				Reservation r = new Reservation();
				
//				li.setBarcode(2021l);
//				user.setCardID(1104L);
				
				r.setLibraryItem(li);
				r.setReservationID(rId);
				r.setUser(user);
				
				return r;
			}
			return null;
		});
	}
	
	
/*
 * Tests for createReservation method
 */
	@Test
	public void testCreateReservation() {
		assertEquals(0, reservationService.getAllReservation().size());
		
		Long reservationID = 1230l;
		LibraryItem li = new LibraryItem();
		User user = new Citizen();
		
		Reservation r = null;
		try {
			r = reservationService.createReservation(reservationID, user, li);
		}catch(IllegalArgumentException e) {
			fail();
		}
		
//		System.out.println(reservationService.getReservation(reservationID));
		
		assertNotNull(r);
		assertEquals(reservationID, r.getReservationID());
		assertEquals(li, r.getLibraryItem());
		assertEquals(user, r.getUser());
}
	
	@Test
	public void testCreateReservationNull() {
//		assertEquals(0, libraryItemService.getAllLibraryItem().size());
		
		String error = "";
		
		Long reservationID = null;
		LibraryItem li = null;
		User user = null;
		
		Reservation r = null;
		try {
			r = reservationService.createReservation(reservationID, user, li);
		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(r);
		assertEquals("Reservation ID cannot be empty. User cannot be empty. Library item cannot be empty. ", error);
	}
	
	
	
/*
 * Tests for deleteReservation method
 */
	
	@Test
	public void testDeleteReservation() {
//		assertEquals(0, libraryItemService.getAllLibraryItem().size());

		Reservation r = null;
		Long reservationID = 1230l;
		LibraryItem li = new LibraryItem();
		User user = new Citizen();
		r = reservationService.createReservation(reservationID, user, li);
		
		assertNotNull(r);
		
		try {
			r = reservationService.deleteReservation(r);
		}catch(IllegalArgumentException e) {
			fail();
		}
		assertNull(r);
	}
	
	
	@Test
	public void testDeleteNullReservation() {
//		assertEquals(0, libraryItemService.getAllLibraryItem().size());
		String error = "";
		Reservation r = null;

		try {
			r = reservationService.deleteReservation(r);
		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(r);
		assertEquals("Input reservation cannot be null. ", error);
	}
	
	
/* 
 * Tests for getReservation method
 */
	
	@Test 
	public void testGetExistingReservation() {
		assertEquals(rId, reservationService.getReservation(rId).getReservationID());
	}
	
	public void testGetNonExistingReservation() {
		assertNull(reservationService.getReservation(nonExistingRId));
	}

}
