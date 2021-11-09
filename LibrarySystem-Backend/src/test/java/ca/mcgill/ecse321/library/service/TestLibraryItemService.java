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
import ca.mcgill.ecse321.library.models.LibraryItem;

@ExtendWith(MockitoExtension.class)
public class TestLibraryItemService {
	
	@Mock
	private LibraryItemRepository libraryItemRepository;
	
	@InjectMocks
	private LibraryItemService libraryItemService;
	
	private static final Long barcode = 1234567890l;
	private static final LibraryItem.ItemType type = LibraryItem.ItemType.Book;
	private static final String title = "Dune";
	private static final Boolean isReservable = true;
	private static final Boolean isReserved = false;
	private static final Integer loanPeriod = 21;
	
	private static final Long nonExistingBarcode = 555l;
	
	@BeforeEach
	public void setMockOutput() {
		lenient().when(libraryItemRepository.findLibraryItemByBarcode(anyLong())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(barcode)) {
				
				LibraryItem li = new LibraryItem();
				
				li.setBarcode(barcode);
				li.setType(type);
				li.setTitle(title);
				li.setIsReservable(isReservable);
				li.setIsReserved(isReserved);
				li.setIsReserved(false);
				li.setLoanPeriod(loanPeriod);
				
				return li;
			}
			return null;
		});
	}
	
/* 
 * Tests for createLibraryItem method	
 */
	
	@Test
	public void testCreateLibraryItem() {
		assertEquals(0, libraryItemService.getAllLibraryItem().size());
		
		Long barcode = 1234567890l;
		LibraryItem.ItemType type = LibraryItem.ItemType.Movie;
		String title = "Dune Movie";
		Boolean isReservable = true;
		Integer loanPeriod = 7;
		
		LibraryItem li = null;
		try {
			li = libraryItemService.createLibraryItem(barcode, type, title, isReservable, isReserved, loanPeriod);
		}catch(IllegalArgumentException e) {
			fail();
		}
				
		assertNotNull(li);
		assertEquals(barcode, li.getBarcode());
		assertEquals(type, li.getType());
		assertEquals(title, li.getTitle());
		assertEquals(isReservable, li.getIsReservable());
		assertEquals(isReserved, li.getIsReserved());
		assertEquals(loanPeriod, li.getLoanPeriod());	
	}
	
	
	@Test
	public void testCreateLibraryItemNull() {
//		assertEquals(0, libraryItemService.getAllLibraryItem().size());
		
		String error = "";
		
		Long barcode = null;
		LibraryItem.ItemType type = null;
		String title = null;
		Boolean isReservable = null;
		Integer loanPeriod = null;
		
		LibraryItem li = null;
		try {
			li = libraryItemService.createLibraryItem(barcode, type, title, isReservable, isReserved, loanPeriod);
		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
				
		assertNull(li);
		assertEquals("Barcode cannot be empty. Item type cannot be empty. Title cannot be empty. Must specify if the library item is reservable. Must specify if the library item is reserved. Must specify the loan period of library item. ", error);
	}
	
	@Test
	public void testCreateLibraryItemWithLoanPeriodLessThanZero() {
//		assertEquals(0, libraryItemService.getAllLibraryItem().size());
		
		String error = "";
		
		Long barcode = 1234567890l;
		LibraryItem.ItemType type = LibraryItem.ItemType.Movie;
		String title = "Dune Movie";
		Boolean isReservable = true;
		Boolean isReserved = false;
		Integer loanPeriod = -5;
		
		LibraryItem li = null;
		try {
			li = libraryItemService.createLibraryItem(barcode, type, title, isReservable, isReserved, loanPeriod);
		}catch(IllegalArgumentException e) {
			error += e.getMessage();
		}
				
		assertNull(li);
		assertEquals("Loan period must be greater or equal to 0. ", error);
	}
	
	@Test
	public void testCreateReservableLibraryItemWithZeroLoanPeriod() {
//		assertEquals(0, libraryItemService.getAllLibraryItem().size());
		
		String error = "";
		
		Long barcode = 111l;
		LibraryItem.ItemType type = LibraryItem.ItemType.Movie;
		String title = "Dune Movie";
		Boolean isReservable = true;
		Boolean isReserved = false;
		Integer loanPeriod = 0;
		
		LibraryItem li = null;
		try {
			li = libraryItemService.createLibraryItem(barcode, type, title, isReservable, isReserved, loanPeriod);
		}catch(IllegalArgumentException e) {
			error += e.getMessage();
		}
				
		assertNull(li);
		assertEquals("Loan period for reservable item must be greater than 0. ", error);
	}
	
	
	@Test
	public void testCreateNonReservableLibraryItemWithNonZeroLoanPeriod() {
//		assertEquals(0, libraryItemService.getAllLibraryItem().size());
		
		String error = "";
		
		Long barcode = 111l;
		LibraryItem.ItemType type = LibraryItem.ItemType.Movie;
		String title = "Dune Movie";
		Boolean isReservable = false;
		Boolean isReserved = false;
		Integer loanPeriod = 7;
		
		LibraryItem li = null;
		try {
			li = libraryItemService.createLibraryItem(barcode, type, title, isReservable, isReserved, loanPeriod);
		}catch(IllegalArgumentException e) {
			error += e.getMessage();
		}
				
		assertNull(li);
		assertEquals("Loan period for non-reservable item must be equal to 0. ", error);
	}
	
	
/*
 * Tests for DeleteLibarayItem method
 */
	
	@Test
	public void testDeleteLibraryItem() {
//		assertEquals(0, libraryItemService.getAllLibraryItem().size());
		
		LibraryItem li = null;

		Long barcode = 111l;
		LibraryItem.ItemType type = LibraryItem.ItemType.Movie;
		String title = "Dune Movie";
		Boolean isReservable = true;
		Boolean isReserved = false;
		Integer loanPeriod = 7;
		
		li = libraryItemService.createLibraryItem(barcode, type, title, isReservable, isReserved, loanPeriod);
		
		assertNotNull(li);
		
		try {
			li = libraryItemService.deleteLibraryItem(li);
		}catch(IllegalArgumentException e) {
			fail();
		}
		
		assertNull(li);
	}
	
	
	@Test
	public void testDeleteNullLibraryItem() {
//		assertEquals(0, libraryItemService.getAllLibraryItem().size());
		
		String error = "";
		
		LibraryItem li = null;
		
		try {
			li = libraryItemService.deleteLibraryItem(li);
		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(li);
		assertEquals("Input LibraryItem must not be null.", error);
	}
	
	
/*
 * Tests for Update LibraryItem method
 */
	
	@Test
	public void testUpdateLibraryItemType() {
//		assertEquals(0, libraryItemService.getAllLibraryItem().size());

		Long barcode = 111l;
		LibraryItem.ItemType type1 = LibraryItem.ItemType.Movie;
		String title = "Dune Movie";
		Boolean isReservable = true;
		Boolean isReserved = false;
		Integer loanPeriod = 7;
		
		LibraryItem original = libraryItemService.createLibraryItem(barcode, type1, title, isReservable, isReserved, loanPeriod);
//		LibraryItem test = libraryItemService.getLibraryItem(barcode);
//		System.out.println(test);
		LibraryItem updated = null;
		LibraryItem.ItemType type2 = LibraryItem.ItemType.Book;

		try {
			updated = libraryItemService.updateLibraryItemType(original, type2);
		}catch(IllegalArgumentException e) {
			fail();
		}
		
		assertNotNull(updated);
		assertEquals(type2, updated.getType());
	}
	
	@Test
	public void testUpdateLibraryItemTypeFail() {
//		assertEquals(0, libraryItemService.getAllLibraryItem().size());
		
		String error = "";

		Long barcode = 111l;
		LibraryItem.ItemType type1 = LibraryItem.ItemType.Movie;
		String title = "Dune Movie";
		Boolean isReservable = true;
		Boolean isReserved = false;
		Integer loanPeriod = 7;
		
		LibraryItem original = libraryItemService.createLibraryItem(barcode, type1, title, isReservable, isReserved, loanPeriod);
		LibraryItem updated = null;
		LibraryItem.ItemType type2 = null;

		try {
			updated = libraryItemService.updateLibraryItemType(original, type2);
		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(updated);
		assertEquals("Item type cannot be empty.", error);
	}
	
	
	
	
	
	@Test
	public void testUpdateLibraryItemTitle() {
//		assertEquals(0, libraryItemService.getAllLibraryItem().size());

		Long barcode = 111l;
		LibraryItem.ItemType type = LibraryItem.ItemType.Movie;
		String title1 = "Dune Movie";
		Boolean isReservable = true;
		Boolean isReserved = false;
		Integer loanPeriod = 7;
		
		LibraryItem original = libraryItemService.createLibraryItem(barcode, type, title1, isReservable, isReserved, loanPeriod);
		LibraryItem updated = null;
		String title2 = "Dune Book";

		try {
			updated = libraryItemService.updateLibraryItemTitle(original, title2);
		}catch(IllegalArgumentException e) {
			fail();
		}
		
		assertNotNull(updated);
		assertEquals(title2, updated.getTitle());
	}
	
	@Test
	public void testUpdateLibraryItemTitleFail() {
//		assertEquals(0, libraryItemService.getAllLibraryItem().size());
		
		String error = "";

		Long barcode = 111l;
		LibraryItem.ItemType type = LibraryItem.ItemType.Movie;
		String title1 = "Dune Movie";
		Boolean isReservable = true;
		Boolean isReserved = false;
		Integer loanPeriod = 7;
		
		LibraryItem original = libraryItemService.createLibraryItem(barcode, type, title1, isReservable, isReserved, loanPeriod);
		LibraryItem updated = null;
		String title2 = "";

		try {
			updated = libraryItemService.updateLibraryItemTitle(original, title2);
		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(updated);
		assertEquals("Title cannot be empty.", error);
	}
	
	
	
	@Test
	public void testUpdateLibraryItemIsReservable() {
//		assertEquals(0, libraryItemService.getAllLibraryItem().size());

		Long barcode = 111l;
		LibraryItem.ItemType type = LibraryItem.ItemType.Movie;
		String title = "Dune Movie";
		Boolean isReservable1 = true;
		Boolean isReserved = false;
		Integer loanPeriod = 7;
		
		LibraryItem original = libraryItemService.createLibraryItem(barcode, type, title, isReservable1, isReserved, loanPeriod);
		LibraryItem updated = null;
		Boolean isReservable2 = false;
//		libraryItemService.updateLibraryItemLoanPeriod(original,0);
		try {
			updated = libraryItemService.updateLibraryItemIsReservable(original, isReservable2);
		}catch(IllegalArgumentException e) {
			fail();
		}
		
		assertNotNull(updated);
		assertEquals(isReservable2, updated.getIsReservable());
	}
	
	@Test
	public void testUpdateLibraryItemIsReservableFail() {
//		assertEquals(0, libraryItemService.getAllLibraryItem().size());
		
		String error = "";

		Long barcode = 111l;
		LibraryItem.ItemType type = LibraryItem.ItemType.Movie;
		String title = "Dune Movie";
		Boolean isReservable1 = true;
		Boolean isReserved = false;
		Integer loanPeriod = 7;
		
		LibraryItem original = libraryItemService.createLibraryItem(barcode, type, title, isReservable1, isReserved, loanPeriod);
		LibraryItem updated = null;
		Boolean isReservable2 = null;

		try {
			updated = libraryItemService.updateLibraryItemIsReservable(original, isReservable2);
		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(updated);
		assertEquals("Must specify if the library item is reservable.", error);
	}
	
	
	
	
	@Test
	public void testUpdateLibraryItemLoanPeriod() {
//		assertEquals(0, libraryItemService.getAllLibraryItem().size());

		Long barcode = 111l;
		LibraryItem.ItemType type = LibraryItem.ItemType.Movie;
		String title = "Dune Movie";
		Boolean isReservable = true;
		Boolean isReserved = false;
		Integer loanPeriod1 = 7;
		
		LibraryItem original = libraryItemService.createLibraryItem(barcode, type, title, isReservable, isReserved, loanPeriod1);
		LibraryItem updated = null;
		Integer loanPeriod2 = 21;

		try {
			updated = libraryItemService.updateLibraryItemLoanPeriod(original, loanPeriod2);
		}catch(IllegalArgumentException e) {
			fail();
		}
		
		assertNotNull(updated);
		assertEquals(loanPeriod2, updated.getLoanPeriod());
	}
	
	@Test
	public void testUpdateLibraryItemLoanPeriodNull() {
//		assertEquals(0, libraryItemService.getAllLibraryItem().size());

		String error = "";
		
		Long barcode = 111l;
		LibraryItem.ItemType type = LibraryItem.ItemType.Movie;
		String title = "Dune Movie";
		Boolean isReservable = true;
		Boolean isReserved = false;
		Integer loanPeriod1 = 7;
		
		LibraryItem original = libraryItemService.createLibraryItem(barcode, type, title, isReservable, isReserved, loanPeriod1);
		LibraryItem updated = null;
		Integer loanPeriod2 = null;

		try {
			updated = libraryItemService.updateLibraryItemLoanPeriod(original, loanPeriod2);
		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(updated);
		assertEquals("Must specify the loan period of library item.", error);
	}	
	
	@Test
	public void testUpdateLibraryItemLoanPeriodLessThanZero() {
//		assertEquals(0, libraryItemService.getAllLibraryItem().size());

		String error = "";
		
		Long barcode = 111l;
		LibraryItem.ItemType type = LibraryItem.ItemType.Movie;
		String title = "Dune Movie";
		Boolean isReservable = true;		
		Boolean isReserved = false;
		Integer loanPeriod1 = 7;
		
		LibraryItem original = libraryItemService.createLibraryItem(barcode, type, title, isReservable, isReserved, loanPeriod1);
		LibraryItem updated = null;
		Integer loanPeriod2 = -5;

		try {
			updated = libraryItemService.updateLibraryItemLoanPeriod(original, loanPeriod2);
		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(updated);
		assertEquals("Loan period must be greater or equal to 0.", error);
	}
	
	@Test
	public void testUpdateLibraryItemLoanPeriodForReservableItem() {
//		assertEquals(0, libraryItemService.getAllLibraryItem().size());

		String error = "";
		
		Long barcode = 111l;
		LibraryItem.ItemType type = LibraryItem.ItemType.Movie;
		String title = "Dune Movie";
		Boolean isReservable = true;
		Boolean isReserved = false;
		Integer loanPeriod1 = 7;
		
		LibraryItem original = libraryItemService.createLibraryItem(barcode, type, title, isReservable, isReserved, loanPeriod1);
		LibraryItem updated = null;
		Integer loanPeriod2 = 0;

		try {
			updated = libraryItemService.updateLibraryItemLoanPeriod(original, loanPeriod2);
		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(updated);
		assertEquals("Loan period for reservable item must be greater than 0.", error);
	}
	
	@Test
	public void testUpdateLibraryItemLoanPeriodForNonReservableItem() {
//		assertEquals(0, libraryItemService.getAllLibraryItem().size());

		String error = "";
		
		Long barcode = 111l;
		LibraryItem.ItemType type = LibraryItem.ItemType.Movie;
		String title = "Dune Movie";
		Boolean isReservable = false;
		Boolean isReserved = false;
		Integer loanPeriod1 = 0;
		
		LibraryItem original = libraryItemService.createLibraryItem(barcode, type, title, isReservable, isReserved, loanPeriod1);
		LibraryItem updated = null;
		Integer loanPeriod2 = 7;

		try {
			updated = libraryItemService.updateLibraryItemLoanPeriod(original, loanPeriod2);
		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(updated);
		assertEquals("Loan period for non-reservable item must be equal to 0.", error);
	}
	
	
/* 
 * Tests for getLibraryItem method
 */
	
	@Test
	public void testGetExistingLibraryItem() {
		assertEquals(barcode, libraryItemService.getLibraryItem(barcode).getBarcode());
	}
	
	@Test
	public void testGetNonExistingLibraryItem() {
		assertNull(libraryItemService.getLibraryItem(nonExistingBarcode));
	}

}