package ca.mcgill.ecse321.library.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.library.models.LibraryItem;

public interface LibraryItemRepository extends CrudRepository<LibraryItem, Long>{
	
	LibraryItem findLibraryItemByBarcode(Long barcode);
	
}
