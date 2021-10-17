package ca.mcgill.ecse321.library.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.transaction.Transactional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.library.models.*;
import ca.mcgill.ecse321.library.models.LibraryItem.ItemType;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestLibraryItemPersistence {
	
	@Autowired
	private LibraryItemRepository libraryItemRepository;
	
	@AfterEach
	public void clearDatabase() {
		libraryItemRepository.deleteAll();
	}
		
	@Test
	@Transactional
	public void testPersistAndLoadLibraryItem() {
		LibraryItem li = new LibraryItem();
		
		// initialize and set attributes to the library item
		ItemType type = ItemType.Book;
		String title = "How to manage the library system";
		boolean isReservable = true;
		boolean isReserved = false;
		int loadPeriod = 21;
		li.setType(type);
		li.setTitle(title);
		li.setIsReservable(isReservable);
		li.setIsReserved(isReserved);
		li.setLoanPeriod(loadPeriod);
		
		libraryItemRepository.save(li); // By saving, a unique barcode is assigned to the library item (no need to set it ourself)
		Long barcode = li.getBarcode(); // get the barcode generated by the repository
		
		li = null;
		
		li = libraryItemRepository.findLibraryItemByBarcode(barcode); // find the library item using the assigned barcode
		assertNotNull(li);
		assertEquals(barcode, li.getBarcode());
		assertEquals(type, li.getType());
		assertEquals(title, li.getTitle());
		assertEquals(isReservable, li.getIsReservable());
		assertEquals(isReserved, li.getIsReserved());
		assertEquals(loadPeriod, li.getLoanPeriod());
	}

}
