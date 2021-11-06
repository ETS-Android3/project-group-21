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

import ca.mcgill.ecse321.library.dto.ShiftDto;
import ca.mcgill.ecse321.library.models.*;
import ca.mcgill.ecse321.library.models.Shift.DayOfWeek;
import ca.mcgill.ecse321.library.service.ShiftService;

@CrossOrigin(origins = "*")
@RestController
public class ShiftController {
    
    @Autowired
    private ShiftService service;

    @GetMapping(value = { "/shifts", "/shifts/"} )
	public List<ShiftDto> getAllShifts(){
		List<ShiftDto> shiftdtos = new ArrayList <>();
		for (Shift shift : service.getAllShifts()) {
			shiftdtos.add(convertToDto(shift));
		}
		return shiftdtos;
	}

    @PostMapping(value = { "/shifts/{shiftCode}", "/shifts/{shiftCode}/"} )
    public ShiftDto createShiftDto(@PathVariable("shiftCode") Long shiftCode, 
    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime startTime, 
    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime endTime,
    @RequestParam DayOfWeek day) throws IllegalArgumentException{
		Shift shift = service.createShift(shiftCode,Time.valueOf(startTime),Time.valueOf(endTime),day);
		return convertToDto(shift);
	}


    private ShiftDto convertToDto(Shift s){
        if(s == null) {
			throw new IllegalArgumentException("Input shift cannot be null. ");
		}

        ShiftDto shiftDto = new ShiftDto( s.getShiftCode(), s.getStartTime(), s.getEndTime(), s.getDay());
        return shiftDto;
    }
}
