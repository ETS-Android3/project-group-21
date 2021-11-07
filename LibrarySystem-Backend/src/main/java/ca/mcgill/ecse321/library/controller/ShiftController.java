package ca.mcgill.ecse321.library.controller;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.library.dto.ApplicationUserDto;
import ca.mcgill.ecse321.library.dto.ShiftDto;
import ca.mcgill.ecse321.library.models.*;
import ca.mcgill.ecse321.library.models.Shift.DayOfWeek;
import ca.mcgill.ecse321.library.service.HeadLibrarianService;
import ca.mcgill.ecse321.library.service.LibrarianService;
import ca.mcgill.ecse321.library.service.ShiftService;

@CrossOrigin(origins = "*")
@RestController
public class ShiftController {
    
    @Autowired
    private ShiftService shiftService;
	
	@Autowired
	private LibrarianService librarianService;
	
	@Autowired
	private HeadLibrarianService headLibrarianService;
	

    @GetMapping(value = { "/shifts", "/shifts/"} )
	public List<ShiftDto> getAllShifts(){
		List<ShiftDto> shiftdtos = new ArrayList <>();
		for (Shift shift : shiftService.getAllShifts()) {
			shiftdtos.add(convertToDto(shift));
		}
		return shiftdtos;
	}

    @GetMapping(value = { "/shifts/{shiftCode}", "/shifts/{shiftCode}/"})
	public ShiftDto getLibrarianById(@PathVariable("shiftCode") Long shiftCode) throws IllegalArgumentException{
		return convertToDto(shiftService.getShift(shiftCode));
	}

    @PostMapping(value = { "/shifts/{shiftCode}", "/shifts/{shiftCode}/"} )
    public ShiftDto createShiftDto(@PathVariable("shiftCode") Long shiftCode, 
    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime startTime, 
    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime endTime,
    @RequestParam DayOfWeek day, @RequestParam("applicationUser") ApplicationUser applicationUserDto) throws IllegalArgumentException{

		Librarian l = librarianService.getLibrarianByID(applicationUserDto.getCardID());
		HeadLibrarian hl = headLibrarianService.getHeadLibrarianByID(applicationUserDto.getCardID());
		ApplicationUser user = null;		
		if (l != null) user = l; 
		if (hl != null) user = hl; 
        //keep the user null if the user is a citizen

		Shift shift = shiftService.createShift(shiftCode,Time.valueOf(startTime),Time.valueOf(endTime), day, user);
		return convertToDto(shift);
	}


    private ShiftDto convertToDto(Shift s){
        if(s == null) {
			throw new IllegalArgumentException("Input shift cannot be null. ");
		}

        ShiftDto shiftDto = new ShiftDto( s.getShiftCode(), s.getStartTime(), s.getEndTime(), s.getDay(), convertToDto(s.getApplicationUser()));
        return shiftDto;
    }

    private ApplicationUserDto convertToDto (ApplicationUser user) {
		if (user == null) {
			throw new IllegalArgumentException("There is no such an application user");
		}
		ApplicationUserDto userDto = new ApplicationUserDto(user.getCardID(), user.getFullName(), user.getAddress(), user.getUsername(), user.getPassword());
		return userDto;
	}
}
