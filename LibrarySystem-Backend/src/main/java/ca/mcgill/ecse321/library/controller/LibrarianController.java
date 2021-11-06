package ca.mcgill.ecse321.library.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
	
	
	@PostMapping(value= {"/librarians/{cardID}","/librarians/{cardID}/"})
	public LibrarianDto createLibrarian(@PathVariable("cardID") Long cardID, @RequestParam String name, @RequestParam String Address, 
			@RequestParam String username, @RequestParam String password) throws IllegalArgumentException{
		Librarian h = librarianService.createLibrarian(name, username, password, Address, cardID);
		return convertToDto(h);
	}
	
	@GetMapping(value = { "/librarians","/librarians/"})
	public List<LibrarianDto> getAllLibrarian(){
		List<LibrarianDto> LibrarianDtos = new ArrayList<>();
		for (Librarian hl: librarianService.getAllLibrarian()) {
			LibrarianDtos.add(convertToDto(hl));
		}
		return LibrarianDtos;
	}
	
	@GetMapping(value = { "/librarians/{cardID}","/librarians/{cardID}/"})
	public LibrarianDto getLibrarianById(@PathVariable("cardID") Long cardID) throws IllegalArgumentException{
		return convertToDto(librarianService.getLibrarianByID(cardID));
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

	private LibrarianDto convertToDto(Librarian h) {
		if (h == null) {
			throw new IllegalArgumentException ("There is no such Librarian");
		}
		LibrarianDto LibrarianDto = new LibrarianDto (h.getCardID(),h.getFullName(),h.getAddress(),h.getUsername(),h.getPassword());
		return LibrarianDto;
	}
}
