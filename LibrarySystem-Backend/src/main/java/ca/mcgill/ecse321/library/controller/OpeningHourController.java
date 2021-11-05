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

import ca.mcgill.ecse321.library.dto.OpeningHourDto;
import ca.mcgill.ecse321.library.models.OpeningHour;
import ca.mcgill.ecse321.library.models.OpeningHour.DayOfWeek;
import ca.mcgill.ecse321.library.service.OpeningHourService;

@CrossOrigin(origins = "*")
@RestController
public class OpeningHourController {
	
	@Autowired
	private OpeningHourService service;
	
	@GetMapping(value = { "/openinghours", "/openinghours/"} )
	public List<OpeningHourDto> getAllOpeningHours(){
		List<OpeningHourDto> openinghourDtos = new ArrayList <>();
		for (OpeningHour openinghour : service.getAllOpeningHour()) {
			openinghourDtos.add(convertToDto(openinghour));
		}
		return openinghourDtos;
	}
	
	@PostMapping(value = {"/openinghours/{day}", "/openinghours/{day}/"})
	public OpeningHourDto createOpeningHour(@PathVariable("day") DayOfWeek day, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime startTime, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime endTime) throws IllegalArgumentException{
		OpeningHour openinghour = service.createOpeningHour(day, Time.valueOf(startTime), Time.valueOf(endTime));
		return convertToDto(openinghour);
	}
	
	private OpeningHourDto convertToDto(OpeningHour o) {
		if (o == null) {
			throw new IllegalArgumentException("There is no such Opening Hour!");
		}
		OpeningHourDto openinghourDto = new OpeningHourDto(o.getDay(), o.getStartTime(), o.getEndTime());
		return openinghourDto;
	}

}
