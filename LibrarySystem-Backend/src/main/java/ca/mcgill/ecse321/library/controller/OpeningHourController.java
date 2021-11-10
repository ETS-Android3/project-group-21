package ca.mcgill.ecse321.library.controller;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import ca.mcgill.ecse321.library.dto.OpeningHourDto;
import ca.mcgill.ecse321.library.models.OpeningHour;
import ca.mcgill.ecse321.library.models.OpeningHour.DayOfWeek;
import ca.mcgill.ecse321.library.service.OpeningHourService;

@CrossOrigin(origins = "*")
@RestController
public class OpeningHourController {
	
	@Autowired
	private OpeningHourService openinghourservice;
	
	/*
	 * @Author: Dania Pennimpede
	 * Get all opening hour
	 */
	
	@GetMapping(value = { "/openinghours", "/openinghours/"} )
	public List<OpeningHourDto> getAllOpeningHours(){
		List<OpeningHourDto> openinghourDtos = new ArrayList <>();
		for (OpeningHour openinghour : openinghourservice.getAllOpeningHour()) {
			openinghourDtos.add(convertToDto(openinghour));
		}
		return openinghourDtos;
	}
	
	/*
	 * @Author: Dania Pennimpede
	 * Create opening Hour
	 */
	@PostMapping(value = {"/openinghours/{day}", "/openinghours/{day}/"})
	public OpeningHourDto createOpeningHour(@PathVariable("day") DayOfWeek day, 
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime startTime, 
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime endTime) throws IllegalArgumentException{
		OpeningHour openinghour = openinghourservice.createOpeningHour(day, Time.valueOf(startTime), Time.valueOf(endTime));
		return convertToDto(openinghour);
	}
	
	/*
	 * @Author: Dania Pennimpede
	 * Helper method that convert opening hour to DTO
	 */
	private OpeningHourDto convertToDto(OpeningHour o) {
		if (o == null) {
			throw new IllegalArgumentException("There is no such Opening Hour!");
		}
		OpeningHourDto openinghourDto = new OpeningHourDto(o.getDay(), o.getStartTime(), o.getEndTime());
		return openinghourDto;
	}
	
	/*
	 * @Author: Dania Pennimpede
	 * Get Opening Hour by day
	 */
	@GetMapping(value = { "/openinghours/{day}", "/openinghours/{day}/" })
	public OpeningHourDto getOpeningHourByDay(@PathVariable("day") DayOfWeek day) throws IllegalArgumentException {
		return convertToDto(openinghourservice.getOpeningHourByDay(day));
	}
	
	/*
	 * @Author: Dania Pennimpede
	 * Delete opening hour
	 */
	@DeleteMapping(value = { "/openinghours/{day}", "/openinghours/{day}/" })
	public void deleteOpeningHour(@PathVariable("day") DayOfWeek day) throws IllegalArgumentException {
		OpeningHour openinghour = openinghourservice.getOpeningHourByDay(day);
		openinghourservice.deleteOpeningHour(openinghour);
	}
	
	
	
	@PatchMapping(value = { "/openinghours/{day}", "/openinghours/{day}/" })
	public OpeningHourDto editOpeningHour(@PathVariable("day") String day, 
			@RequestParam(required=false) String startTime, @RequestParam (required=false) String endTime) throws IllegalArgumentException {
		
		OpeningHour.DayOfWeek dayOfWeek = null;
    	if (day.equalsIgnoreCase("Monday")) {
    		dayOfWeek = OpeningHour.DayOfWeek.Monday;
    	}else if (day.equalsIgnoreCase("Tuesday")) {
    		dayOfWeek = OpeningHour.DayOfWeek.Tuesday;
    	}else if (day.equalsIgnoreCase("Wednesday")) {
    		dayOfWeek = OpeningHour.DayOfWeek.Wednesday;
    	}else if (day.equalsIgnoreCase("Thursday")) {
    		dayOfWeek = OpeningHour.DayOfWeek.Thursday;
    	}else if (day.equalsIgnoreCase("Friday")) {
    		dayOfWeek = OpeningHour.DayOfWeek.Friday;
    	}else if (day.equalsIgnoreCase("Saturday")) {
    		dayOfWeek = OpeningHour.DayOfWeek.Saturday;
    	}else if (day.equalsIgnoreCase("Sunday")) {
    		dayOfWeek = OpeningHour.DayOfWeek.Sunday;
    	}
		OpeningHour openinghour = openinghourservice.getOpeningHourByDay(dayOfWeek);	
		
		if (startTime != null) {
			openinghourservice.updateOpeningHourStartTime(openinghour, Time.valueOf(startTime));
		}
		if (startTime != null) {
			openinghourservice.updateOpeningHourEndTime(openinghour, Time.valueOf(startTime), Time.valueOf(endTime));
		}
		return convertToDto(openinghour);
	}

}
