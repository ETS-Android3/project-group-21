package ca.mcgill.ecse321.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.library.dao.LibraryItemRepository;
import ca.mcgill.ecse321.library.models.LibraryItem;

@Service
public class LibraryItemService {
	
	@Autowired
	LibraryItemRepository libraryItemRepository;
	
	@Transactional
	public LibraryItem createLibraryItem(Long barcode, LibraryItem.ItemType type, String title, 
			Boolean isReservable, Boolean isReserved, Integer loanPeriod) {
		
		String error = "";
		
		if (barcode == null || barcode.equals("")) {
			error += "Barcode cannot be empty. ";
		}
		if (type == null || type.equals("")) {
			error += "Item type cannot be empty. ";
		}
		if (title == null || title.isBlank()) {
			error += "Title cannot be empty. ";
		}
		if (isReservable == null || isReservable.equals("")) {
			error += "Must specify if the library item is reservable. ";
		}
		if (isReservable == null || isReservable.equals("")) {
			error += "Must specify if the library item is reserved. ";
		}
		if (loanPeriod == null || loanPeriod.equals("")) {
			error += "Must specify the loan period of library item. ";
		}else if (loanPeriod < 0) {
			error += "Loan period must be greater or equal to 0. ";
		}else if (isReservable && loanPeriod == 0) {
			error += "Loan period for reservable item must be greater than 0. ";
		}else if (!isReservable && loanPeriod > 0) {
			error += "Loan period for non-reservable item must be equal to 0. ";
		}
		
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		
		LibraryItem li = new LibraryItem();
		li.setBarcode(barcode);
		li.setType(type);
		li.setTitle(title);
		li.setIsReservable(isReservable);
		li.setIsReserved(false);
		li.setLoanPeriod(loanPeriod);
		
		libraryItemRepository.save(li);
		
		return li;	
	}
	
	
	@Transactional 
	public LibraryItem deleteLibraryItem(LibraryItem li) {
		if (li == null ) {
			throw new IllegalArgumentException("Input LibraryItem must not be null.");
		}
		libraryItemRepository.delete(li);
		li = null;
		return li;
	}
	
	
	@Transactional
	public LibraryItem updateLibraryItemType(LibraryItem li, LibraryItem.ItemType type) {
		if (type == null || type.equals("")) {
			throw new IllegalArgumentException("Item type cannot be empty.");
		}
		li.setType(type);
		libraryItemRepository.save(li);
		return li;
	}
	
	@Transactional
	public LibraryItem updateLibraryItemTitle(LibraryItem li, String title) {
		if (title == null || title.isBlank()) {
			throw new IllegalArgumentException("Title cannot be empty.");
		}
		li.setTitle(title);
		libraryItemRepository.save(li);
		return li;
	}	
	
	@Transactional
	public LibraryItem updateLibraryItemIsReservable(LibraryItem li, Boolean isReservable) {
		if (isReservable == null) {
			throw new IllegalArgumentException("Must specify if the library item is reservable.");
		}
		li.setIsReservable(isReservable);;
		libraryItemRepository.save(li);
		return li;
	}
	
	@Transactional
	public LibraryItem updateLibraryItemLoanPeriod(LibraryItem li, Integer loanPeriod) {
		if (loanPeriod == null) {
			throw new IllegalArgumentException("Must specify the loan period of library item.");
		}		
		if (loanPeriod < 0) {
			throw new IllegalArgumentException("Loan period must be greater or equal to 0.");
		}
		if (li.getIsReservable() && loanPeriod == 0) {
			throw new IllegalArgumentException("Loan period for reservable item must be greater than 0.");
		}
		if (!li.getIsReservable() && loanPeriod > 0) {
			throw new IllegalArgumentException("Loan period for non-reservable item must be equal to 0.");
		}
		li.setLoanPeriod(loanPeriod);;
		libraryItemRepository.save(li);
		return li;
	}
	
	
	@Transactional
	public List<LibraryItem> getAllLibraryItem(){
		return (List<LibraryItem>) libraryItemRepository.findAll();
	}
	
	@Transactional
	public LibraryItem getLibraryItem(Long barcode) {
		LibraryItem li = libraryItemRepository.findLibraryItemByBarcode(barcode);
		return li;
	}

}
