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

import ca.mcgill.ecse321.library.dto.*;
import ca.mcgill.ecse321.library.models.*;
import ca.mcgill.ecse321.library.service.*;


@CrossOrigin(origins = "*") 
@RestController
public class HeadLibrarianController {
	
	@Autowired
	private HeadLibrarianService headLibrarianService;


	/*
	 * @Author: Yujin li
	 * create a HeadLibrarian
	 * @param fullname
	 * @param username
	 * @param password
	 * @param address
	 * @param cardID
	 */

	@PostMapping(value= {"/headlibrarian/{cardID}","/headlibrarian/{cardID}/"})
	public HeadLibrarianDto createHeadLibrarian(@PathVariable("cardID") Long cardID, 
			@RequestParam String name, @RequestParam String address, 
			@RequestParam String username, @RequestParam String password) throws IllegalArgumentException{
		HeadLibrarian h = headLibrarianService.createHeadlibrarian(name, username, password, address, cardID);
		return convertToDto(h);
	}


	/*
	 * @Author: Yujin li
	 * get all HeadLibrarian
	 */
	@GetMapping(value = { "/headlibrarian", "/headlibrarian/" })
	public List<HeadLibrarianDto> getAllHeadLibrarians(){
		List<HeadLibrarianDto> headLibrarianDtos = new ArrayList<>();
		for (HeadLibrarian hl: headLibrarianService.getAllHeadLibrarian()) {
			headLibrarianDtos.add(convertToDto(hl));
		}
		return headLibrarianDtos;
	}

	/*
	 * @Author: Yujin li
	 * get HeadLibrarian with a provided ID
	 * @param cardID
	 */
	@GetMapping(value = { "/headlibrarian/{cardID}","/headlibrarian/{cardID}/"})
	public HeadLibrarianDto getHeadLibrarianById(@PathVariable("cardID") Long cardID) throws IllegalArgumentException{
		return convertToDto(headLibrarianService.getHeadLibrarianByID(cardID));
	}

	
	/*
	 * @Author: Yujin li
	 * delete HeadLibrarian with a provided ID
	 * @param cardID
	 */
	@DeleteMapping(value = { "/headlibrarian/{cardID}", "/headlibrarian/{cardID}/" })
	public void deleteHeadLibrarian(@PathVariable("cardID") Long cardID) throws IllegalArgumentException {
		HeadLibrarian headLibrarian = headLibrarianService.getHeadLibrarianByID(cardID);
		headLibrarianService.deleteHeadLibrarian(headLibrarian);
	}	
	
	/*
	 * @Author: Yujin li
	 * edit partial headlibrarian information with provided information
	 * @param cardID
	 * @param fullname
	 * @param username
	 * @param password
	 * @param address
	 */
	@PatchMapping(value = { "/headlibrarian/{cardID}", "/headlibrarian/{cardID}/" })
	public HeadLibrarianDto editHeadLibrarian(@PathVariable("cardID") Long cardID, 
			@RequestParam(required=false) String name, @RequestParam (required=false) String address,
			@RequestParam (required=false) String username,@RequestParam (required=false) String password) throws IllegalArgumentException {
		HeadLibrarian headLibrarian = headLibrarianService.getHeadLibrarianByID(cardID);
		if (name != null) {
			headLibrarianService.editHeadLibrarianFullName(headLibrarian, name);	
		}
		if (address != null) {
			headLibrarianService.editHeadLibrarianAddress(headLibrarian, address);
		}
		if (username != null) {
			headLibrarianService.editHeadLibrarianUserName(headLibrarian, name);
		}
		if (password != null) {
			headLibrarianService.editHeadLibrarianPassword(headLibrarian, password);
		}

		return convertToDto(headLibrarian);
	}
	
	
	/*
	 * @Author: Yujin li
	 * helper method that convert DTO to headlibrarian
	 * @param aHeadLibrarianDto
	 */
	private HeadLibrarian convertToDomainObject(HeadLibrarianDto hDto) {
		List<HeadLibrarian> allHeadLibrarians = headLibrarianService.getAllHeadLibrarian();
		for (HeadLibrarian headlibrarian : allHeadLibrarians) {
			if (headlibrarian.getCardID().equals(hDto.getID())) {
				return headlibrarian;
			}
		}
		return null;
	}

	/*
	 * @Author: Yujin li
	 * helper method convert headlibrarian to dto
	 * @param aHeadLibrarian
	 */
	private HeadLibrarianDto convertToDto(HeadLibrarian h) {
		if (h == null) {
			throw new IllegalArgumentException ("There is no such HeadLibrarian");
		}
		HeadLibrarianDto headLibrarianDto = new HeadLibrarianDto (h.getCardID(),h.getFullName(),h.getAddress(),h.getUsername(),h.getPassword());
		return headLibrarianDto;
	}
}
