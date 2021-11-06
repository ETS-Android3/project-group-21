package ca.mcgill.ecse321.library.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
	
	@PostMapping(value = { "/reservations/{reservationID}", "/reservations/{reservationID}/" })
	public ReservationDto createReservation(@RequestParam("reservationID") Long reservationID, @RequestParam("libraryItem") LibraryItemDto libraryItemDto,
			@RequestParam("applicationUser") ApplicationUser applicationUserDto)
	throws IllegalArgumentException {
		LibraryItem li = libraryItemService.getLibraryItem(libraryItemDto.getBarcode());
		
		Citizen c = citizenService.getCitizenByID(applicationUserDto.getCardID());
		Librarian l = librarianService.getLibrarianByID(applicationUserDto.getCardID());
		HeadLibrarian hl = headLibrarianService.getHeadLibrarianByID(applicationUserDto.getCardID());
		ApplicationUser user = null;
		if (c != null) user = c; 
		if (l != null) user = l; 
		if (hl != null) user = hl; 
		
		Reservation reservation = reservationService.createReservation(reservationID, user, li);
		return convertToDto(reservation);
	}

	
	@GetMapping(value = { "/reservations/{reservationID}", "/reservations/{reservationID}/" })
	public ReservationDto getReservationByBarcode(@PathVariable("reservationID") Long reservationID) throws IllegalArgumentException {
		return convertToDto(reservationService.getReservation(reservationID));
	}
	
	
	@GetMapping(value = { "/reservations", "/reservations/" })
	public List<ReservationDto> getAllReservations() {
		List<ReservationDto> reservationDtos = new ArrayList<>();
		for (Reservation reservation : reservationService.getAllReservation()) {
			reservationDtos.add(convertToDto(reservation));
		}
		return reservationDtos;
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
	
	private ReservationDto convertToDto (Reservation r) {
		if (r == null) {
			throw new IllegalArgumentException("There is no such a rbrary item");
		}
		ApplicationUserDto userDto = convertToDto(r.getApplicationUser());		
		LibraryItemDto liDto = convertToDto(r.getLibraryItem());
		ReservationDto rDto = new ReservationDto(r.getReservationID(), userDto, liDto);
		return rDto;
	}
	
	private LibraryItemDto convertToDto (LibraryItem li) {
		if (li == null) {
			throw new IllegalArgumentException("There is no such a library item");
		}
		LibraryItemDto libraryItemDto = new LibraryItemDto(li.getBarcode(), li.getType(), li.getTitle(), li.getIsReservable(), li.getIsReserved(), li.getLoanPeriod());
		return libraryItemDto;
	}
	
	private ApplicationUserDto convertToDto (ApplicationUser user) {
		if (user == null) {
			throw new IllegalArgumentException("There is no such an application user");
		}
		ApplicationUserDto userDto = new ApplicationUserDto(user.getCardID(), user.getFullName(), user.getAddress(), user.getUsername(), user.getPassword());
		return userDto;
	}

}
