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
public class HeadLibrarianController {
	
	@Autowired
	private HeadLibrarianService headLibrarianService;
	

	
	
	@PostMapping(value= {"/headlibrarians/{cardID}","/headlibrarians/{cardID}/"})
	public HeadLibrarianDto createHeadLibrarian(@PathVariable("cardID") Long cardID, @RequestParam String name, @RequestParam String Address, 
			@RequestParam String username, @RequestParam String password) throws IllegalArgumentException{
		HeadLibrarian h = headLibrarianService.createHeadlibrarian(name, username, password, Address, cardID);
		return convertToDto(h);
	}
	
	@GetMapping(value = { "/headlibrarians", "/headlibrarians/" })
	public List<HeadLibrarianDto> getAllHeadLibrarians(){
		List<HeadLibrarianDto> headLibrarianDtos = new ArrayList<>();
		for (HeadLibrarian hl: headLibrarianService.getAllHeadLibrarian()) {
			headLibrarianDtos.add(convertToDto(hl));
		}
		return headLibrarianDtos;
	}

	
	@GetMapping(value = { "/headlibrarians/{cardID}","/headlibrarians/{cardID}/"})
	public HeadLibrarianDto getHeadLibrarianById(@PathVariable("cardID") Long cardID) throws IllegalArgumentException{
		return convertToDto(headLibrarianService.getHeadLibrarianByID(cardID));
	}
	
	
	
	
	
	private HeadLibrarian convertToDomainObject(HeadLibrarianDto hDto) {
		List<HeadLibrarian> allHeadLibrarians = headLibrarianService.getAllHeadLibrarian();
		for (HeadLibrarian headlibrarian : allHeadLibrarians) {
			if (headlibrarian.getCardID().equals(hDto.getID())) {
				return headlibrarian;
			}
		}
		return null;
	}

	private HeadLibrarianDto convertToDto(HeadLibrarian h) {
		if (h == null) {
			throw new IllegalArgumentException ("There is no such HeadLibrarian");
		}
		HeadLibrarianDto headLibrarianDto = new HeadLibrarianDto (h.getCardID(),h.getFullName(),h.getAddress(),h.getUsername(),h.getPassword());
		return headLibrarianDto;
	}
}
