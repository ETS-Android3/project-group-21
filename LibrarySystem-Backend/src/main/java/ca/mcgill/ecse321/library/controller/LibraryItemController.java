package ca.mcgill.ecse321.library.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import ca.mcgill.ecse321.library.dto.LibraryItemDto;
import ca.mcgill.ecse321.library.models.LibraryItem;
import ca.mcgill.ecse321.library.service.LibraryItemService;

@CrossOrigin(origins = "*") 
/* CrossOrigin: allows requests to come accross different platforms (eg. jump from the other websites) */
@RestController
public class LibraryItemController {
	
	@Autowired
	private LibraryItemService libraryItemService;
	
	
	private LibraryItemDto convertToDto (LibraryItem li) {
		if (li == null) {
			throw new IllegalArgumentException("There is no such a library item");
		}
		LibraryItemDto libraryItemDto = new LibraryItemDto(li.getBarcode(), li.getType(), li.getTitle(), li.getIsReservable(), li.getIsReserved(), li.getLoanPeriod());
		return libraryItemDto;
	}

	
	private LibraryItem convertToDomainObject(LibraryItemDto liDto) {
		List<LibraryItem> allLibraryItems = libraryItemService.getAllLibraryItem();
		for (LibraryItem li : allLibraryItems) {
			if (li.getBarcode().equals(liDto.getBarcode())) {
				return li;
			}
		}
		return null;
	}
	
	
	@PostMapping(value = { "/libraryItems/{barcode}", "/libraryItems/{barcode}/" })
	public LibraryItemDto createLibraryItem(@RequestParam("barcode") Long barcode, @RequestParam("type") LibraryItem.ItemType type,
			@RequestParam("title") String title, @RequestParam("isReservable") Boolean isReservable, 
			@RequestParam("isReserved") Boolean isReserved, @RequestParam("loanPeriod") Integer loanPeriod)
	throws IllegalArgumentException {
		LibraryItem libraryItem = libraryItemService.createLibraryItem(barcode, type, title, isReservable, isReserved, loanPeriod);
		return convertToDto(libraryItem);
	}

	
	@GetMapping(value = { "/libraryItems/", "/libraryItems/" })
	public List<LibraryItemDto> getAllLibraryItems() {
		List<LibraryItemDto> libraryItemDtos = new ArrayList<>();
		for (LibraryItem libraryItem : libraryItemService.getAllLibraryItem()) {
			libraryItemDtos.add(convertToDto(libraryItem));
		}
		return libraryItemDtos;
	}
	
	
	
	
}
