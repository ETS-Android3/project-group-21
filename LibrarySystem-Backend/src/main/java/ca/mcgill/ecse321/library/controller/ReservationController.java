package ca.mcgill.ecse321.library.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.library.dto.ApplicationUserDto;
import ca.mcgill.ecse321.library.dto.LibraryItemDto;
import ca.mcgill.ecse321.library.dto.ReservationDto;
import ca.mcgill.ecse321.library.models.ApplicationUser;
import ca.mcgill.ecse321.library.models.Citizen;
import ca.mcgill.ecse321.library.models.HeadLibrarian;
import ca.mcgill.ecse321.library.models.Librarian;
import ca.mcgill.ecse321.library.models.LibraryItem;
import ca.mcgill.ecse321.library.models.Reservation;
import ca.mcgill.ecse321.library.service.CitizenService;
import ca.mcgill.ecse321.library.service.HeadLibrarianService;
import ca.mcgill.ecse321.library.service.LibrarianService;
import ca.mcgill.ecse321.library.service.LibraryItemService;
import ca.mcgill.ecse321.library.service.ReservationService;


/**
 * No edit method for this class because our designed system does not allow anyone to modify a reservation
 * The only way to change a reservation is to cancel the existing one and make a new one
 */

@CrossOrigin(origins = "*")
@RestController
public class ReservationController {
	
	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private LibraryItemService libraryItemService;
	
	@Autowired
	private CitizenService citizenService;
	
	@Autowired
	private LibrarianService librarianService;
	
	@Autowired
	private HeadLibrarianService headLibrarianService;
	
	
	/*
	 * @author Chun Li
	 * Create a ReservationDto
	 * @param reservationID: a long representing the unique ID of a reservation
	 * @param libraryItemDto: a libraryItemDto associated to this reservation
	 * @param applicationUserDto: a applicationUserDto associated to this reservation
	 */
	@PostMapping(value = { "/reservations/{reservationID}", "/reservations/{reservationID}/" })
	public ReservationDto createReservation(@PathVariable("reservationID") Long reservationID, 
			@RequestParam("barcode") Long barcode,
			@RequestParam("cardID") Long cardID)
	throws IllegalArgumentException {
		
		
		LibraryItem li = libraryItemService.getLibraryItem(barcode);
		
		Citizen c = citizenService.getCitizenByID(cardID);
		Librarian l = librarianService.getLibrarianByID(cardID);
		HeadLibrarian hl = headLibrarianService.getHeadLibrarianByID(cardID);
		//ApplicationUser user = null;
		if (c != null) {
			//user = c; 
			Reservation reservation = reservationService.createReservation(reservationID, c, li);
			return convertToDto(reservation);
		}
		else if (l != null) {
			//user = l; 
			Reservation reservation = reservationService.createReservation(reservationID, l, li);
			System.out.println(l);
			return convertToDto(reservation);
		}
		else {
			//user = hl; 
			Reservation reservation = reservationService.createReservation(reservationID, hl, li);
			return convertToDto(reservation);
		}
		
		//Reservation reservation = reservationService.createReservation(reservationID, user, li);
		//return convertToDto(reservation);
	}
//	@PostMapping(value = { "/reservations/{reservationID}", "/reservations/{reservationID}/" })
//	public ReservationDto createReservation(@RequestParam("reservationID") Long reservationID, 
//			@RequestParam("libraryItem") LibraryItemDto libraryItemDto,
//			@RequestParam("applicationUser") ApplicationUser applicationUserDto)
//	throws IllegalArgumentException {
//		
//		
//		LibraryItem li = libraryItemService.getLibraryItem(libraryItemDto.getBarcode());
//		
//		Citizen c = citizenService.getCitizenByID(applicationUserDto.getCardID());
//		Librarian l = librarianService.getLibrarianByID(applicationUserDto.getCardID());
//		HeadLibrarian hl = headLibrarianService.getHeadLibrarianByID(applicationUserDto.getCardID());
//		ApplicationUser user = null;
//		if (c != null) user = c; 
//		if (l != null) user = l; 
//		if (hl != null) user = hl; 
//		
//		Reservation reservation = reservationService.createReservation(reservationID, user, li);
//		return convertToDto(reservation);
//	}

	
	/*
	 * @author Chun Li
	 * Get a ReservationDto by reservationID
	 * @param reservationID
	 */
	@GetMapping(value = { "/reservations/{reservationID}", "/reservations/{reservationID}/" })
	public ReservationDto getReservationByBarcode(@PathVariable("reservationID") Long reservationID) throws IllegalArgumentException {
		return convertToDto(reservationService.getReservation(reservationID));
	}
	
	/*
	 * @author Chun Li
	 * Get all ReservationDtos
	 * @param reservationID
	 */	
	@GetMapping(value = { "/reservations", "/reservations/" })
	public List<ReservationDto> getAllReservations() {
		List<ReservationDto> reservationDtos = new ArrayList<>();
		for (Reservation reservation : reservationService.getAllReservation()) {
			reservationDtos.add(convertToDto(reservation));
		}
		return reservationDtos;
	}
	
	/*
	 * @author Chun Li
	 * Delete a ReservationDto by reservationID
	 * @param reservationID
	 */	
	@DeleteMapping(value = { "/reservations/{reservationID}", "/reservations/{reservationID}/" })
	public void deleteReservation(@PathVariable("reservationID") Long reservationID) throws IllegalArgumentException {
		Reservation reservation = reservationService.getReservation(reservationID);
		reservationService.deleteReservation(reservation);
	}
	
	
	private Reservation convertToDomainObject(ReservationDto rDto) {
		List<Reservation> allReservations = reservationService.getAllReservation();
		for (Reservation r : allReservations) {
			if (r.getReservationID().equals(rDto.getReservationID())) {
				return r;
			}
		}
		return null;
	}
	
	/*
	 * @author Chun Li
	 * Convert a domain object of Reservation to a ReservationDto
	 * @param Reservation : a domain object
	 */
	private ReservationDto convertToDto (Reservation reservation) {
		if (reservation == null) {
			throw new IllegalArgumentException("There is no such a rbrary item");
		}
		ApplicationUserDto userDto = convertToDto(reservation.getApplicationUser());		
		LibraryItemDto liDto = convertToDto(reservation.getLibraryItem());
		ReservationDto rDto = new ReservationDto(reservation.getReservationID(), userDto, liDto);
		return rDto;
	}
	
	/*
	 * @author Chun Li
	 * Convert a domain object of LibraryItem to a LibraryItemDto
	 * @param LibraryItem : a domain object
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
	 * Convert a domain object of ApplicationUser to a ApplicationUserDto
	 * @param ApplicationUser : a domain object
	 */
	private ApplicationUserDto convertToDto (ApplicationUser user) {
		if (user == null) {
			throw new IllegalArgumentException("There is no such an application user");
		}
		ApplicationUserDto userDto = new ApplicationUserDto(user.getCardID(), user.getFullName(), user.getAddress(), user.getUsername(), user.getPassword());
		return userDto;
	}

}
