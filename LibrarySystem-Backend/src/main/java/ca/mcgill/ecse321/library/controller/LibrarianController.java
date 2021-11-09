package ca.mcgill.ecse321.library.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import ca.mcgill.ecse321.library.dto.*;
import ca.mcgill.ecse321.library.models.*;
import ca.mcgill.ecse321.library.service.*;


@CrossOrigin(origins = "*") 
@RestController
public class LibrarianController {
	
	@Autowired
	private LibrarianService librarianService;

	/*
	 * @Author: Yujin li
	 * create a Librarian
	 * @param fullname
	 * @param username
	 * @param password
	 * @param address
	 * @param cardID
	 */

	@PostMapping(value= {"/librarians/{cardID}","/librarians/{cardID/"})
	public LibrarianDto createLibrarian(@PathVariable ("cardID") Long cardID, @RequestParam String fullname, @RequestParam String Address, 
			@RequestParam String username, @RequestParam String password) throws IllegalArgumentException{
		//Long id = Long.parseLong(cardID);
		Librarian h = librarianService.createLibrarian(fullname, username, password, Address, cardID);
		return convertToDto(h);
	}

	/*
	 * @Author: Yujin li
	 * get all Librarian
	 */
	@GetMapping(value = { "/librarians","/librarians/"})
	public List<LibrarianDto> getAllLibrarian(){
		List<LibrarianDto> LibrarianDtos = new ArrayList<>();
		for (Librarian hl: librarianService.getAllLibrarian()) {
			LibrarianDtos.add(convertToDto(hl));
		}
		return LibrarianDtos;
	}

	/*
	 * @Author: Yujin li
	 * get Librarian with a provided ID
	 * @param cardID
	 */
	@GetMapping(value = { "/librarians/{cardID}","/librarians/{cardID}/"})
	public LibrarianDto getLibrarianById(@PathVariable("cardID") Long cardID) throws IllegalArgumentException{
		return convertToDto(librarianService.getLibrarianByID(cardID));
	}

	/*
	 * @Author: Yujin li
	 * helper method that convert DTO to librarian
	 * @param aLibrarianDto
	 */
	
	
//	@PatchMapping(value = { "/librarians/{cardID}", "/librarians/{cardID}/" })
//	public void editLibrarianName(@PathVariable("cardID") Long cardID, @RequestParam String name)
//			throws IllegalArgumentException {
//		Librarian librarian = librarianService.getLibrarianByID(cardID);
//		librarianService.editLibrarianFullName(librarian, name);
//	}
	
	
	@DeleteMapping(value = { "/librarians/{cardID}", "/librarians/{cardID}/" })
	public void deleteAppointmentReminder(@PathVariable("cardID") Long cardID) throws IllegalArgumentException {

		Librarian librarian = librarianService.getLibrarianByID(cardID);
		librarianService.deleteLibrarian(librarian);
	}
	
	
	private Librarian convertToDomainObject(LibrarianDto hDto) {
		List<Librarian> allLibrarians = librarianService.getAllLibrarian();
		for (Librarian Librarian : allLibrarians) {
			if (Librarian.getCardID().equals(hDto.getID())) {
				return Librarian;
			}
		}
		return null;
	}

	/*
	 * @Author: Yujin li
	 * helper method that convert librarian to DTO
	 * @param aLibrarian
	 */
	private LibrarianDto convertToDto(Librarian h) {
		if (h == null) {
			throw new IllegalArgumentException ("There is no such Librarian");
		}
		LibrarianDto LibrarianDto = new LibrarianDto (h.getCardID(),h.getFullName(),h.getAddress(),h.getUsername(),h.getPassword());
		return LibrarianDto;
	}
}
