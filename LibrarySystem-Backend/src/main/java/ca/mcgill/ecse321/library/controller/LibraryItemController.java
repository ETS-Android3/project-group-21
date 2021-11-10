package ca.mcgill.ecse321.library.controller;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	
	/*
	 * @author Chun Li
	 * Convert a domain object to a data transfer object
	 * @param libraryItem: a domain object
	 */
	private LibraryItemDto convertToDto (LibraryItem libraryItem) {
		if (libraryItem == null) {
			throw new IllegalArgumentException("There is no such a library item");
		}
		LibraryItemDto libraryItemDto = new LibraryItemDto(libraryItem.getBarcode(), libraryItem.getType(), 
				libraryItem.getTitle(), libraryItem.getIsReservable(), libraryItem.getIsReserved(), libraryItem.getLoanPeriod());
		return libraryItemDto;
	}

	
	/*
	 * @author Chun Li
	 * Convert a data transfer object to a domain object
	 * @param libraryItemDto: a data transfer object
	 */
	private LibraryItem convertToDomainObject(LibraryItemDto libraryItemDto) {
		List<LibraryItem> allLibraryItems = libraryItemService.getAllLibraryItem();
		for (LibraryItem libraryItem : allLibraryItems) {
			if (libraryItem.getBarcode().equals(libraryItemDto.getBarcode())) {
				return libraryItem;
			}
		}
		return null;
	}
	
	
	/*
	 * @author Chun Li
	 * Create a LibraryItemDto
	 * @param barcode: a string representing the unique barcode of the item
	 * @param type: a LibraryItem.ItemType indicating the item type
	 * @param title: a String representing the title of the item
	 * @param isReservable: a boolean indicating if the item is reservable
	 * @param isReserved: a boolean indicating if the item is reserved by a user
	 * @param loanPeriod: an integer specifying the length of the loan period of the item
	 */
	@PostMapping(value = { "/libraryitems/{barcode}", "/libraryitems/{barcode}/" })
	public LibraryItemDto createLibraryItem(@PathVariable("barcode") Long barcode, @RequestParam("type") String type,
			@RequestParam("title") String title, @RequestParam("isReservable") Boolean isReservable, 
			@RequestParam("isReserved") Boolean isReserved, @RequestParam("loanPeriod") Integer loanPeriod)
	throws IllegalArgumentException {
		// convert string itemType to enum ItemType
		LibraryItem.ItemType itemType = null;
		if (type.equalsIgnoreCase("book")){
			itemType = LibraryItem.ItemType.Book;
		}else if (type.equalsIgnoreCase("movie")){
			itemType = LibraryItem.ItemType.Movie;
		}else if (type.equalsIgnoreCase("music")){
			itemType = LibraryItem.ItemType.Music;
		}else if (type.equalsIgnoreCase("archive")){
			itemType = LibraryItem.ItemType.Archive;
		}else if (type.equalsIgnoreCase("newspaper")){
			itemType = LibraryItem.ItemType.Newspaper;
		}
		LibraryItem libraryItem = libraryItemService.createLibraryItem(barcode, itemType, title, isReservable, isReserved, loanPeriod);
		return convertToDto(libraryItem);
	}

	
	/*
	 * @author Chun Li
	 * Get a LibraryItemDto by its uniqie barcode
	 * @param barcode
	 */
	@GetMapping(value = { "/libraryitems/{barcode}", "/libraryitems/{barcode}/" })
	public LibraryItemDto getLibraryItemByBarcode(@PathVariable("barcode") Long barcode) throws IllegalArgumentException {
		return convertToDto(libraryItemService.getLibraryItem(barcode));
	}
	
	
	/*
	 * @author Chun Li
	 * Get all LibraryItemDtos
	 */
	@GetMapping(value = { "/libraryitems", "/libraryitems/" })
	public List<LibraryItemDto> getAllLibraryItems() {
		List<LibraryItemDto> libraryItemDtos = new ArrayList<>();
		for (LibraryItem libraryItem : libraryItemService.getAllLibraryItem()) {
			libraryItemDtos.add(convertToDto(libraryItem));
		}
		return libraryItemDtos;
	}
	
	
	/*
	 * @author Chun Li
	 * Delete a LibraryItemDto by its unique barcode
	 * @param barcode
	 */
	@DeleteMapping(value = { "/libraryitems/{barcode}", "/libraryitems/{barcode}/" })
	public void deleteLibraryItem(@PathVariable("barcode") Long barcode) throws IllegalArgumentException {
		LibraryItem libraryItem = libraryItemService.getLibraryItem(barcode);
		libraryItemService.deleteLibraryItem(libraryItem);
	}
	
	@PatchMapping(value = { "/libraryitems/{barcode}", "/libraryitems/{barcode}/" })
	public LibraryItemDto editLibraryItem(@PathVariable("barcode") Long barcode, 
			@RequestParam(required=false) String type, @RequestParam (required=false) String title,
			@RequestParam (required=false) Boolean isReservable,@RequestParam (required=false) Boolean isReserved,
			@RequestParam(required=false) Integer loanPeriod) throws IllegalArgumentException {
		LibraryItem libraryItem = libraryItemService.getLibraryItem(barcode);
		if (type != null) {
			LibraryItem.ItemType itemType = null;
			if (type.equalsIgnoreCase("book")){
				itemType = LibraryItem.ItemType.Book;
			}else if (type.equalsIgnoreCase("movie")){
				itemType = LibraryItem.ItemType.Movie;
			}else if (type.equalsIgnoreCase("music")){
				itemType = LibraryItem.ItemType.Music;
			}else if (type.equalsIgnoreCase("archive")){
				itemType = LibraryItem.ItemType.Archive;
			}else if (type.equalsIgnoreCase("newspaper")){
				itemType = LibraryItem.ItemType.Newspaper;
			}
			libraryItemService.updateLibraryItemType(libraryItem, itemType);
			
		}
		if (title != null) {
		libraryItemService.updateLibraryItemTitle(libraryItem, title);
		}
		if (isReservable != null) {
			libraryItemService.updateLibraryItemIsReservable(libraryItem, isReservable);
		}
		if (isReserved != null) {
			libraryItemService.updateLibraryItemIsReserved(libraryItem, isReservable);
		}
		if (loanPeriod != null) {
			libraryItemService.updateLibraryItemLoanPeriod(libraryItem, loanPeriod);
		}
		return convertToDto(libraryItem);
	}

	
	
	
}
