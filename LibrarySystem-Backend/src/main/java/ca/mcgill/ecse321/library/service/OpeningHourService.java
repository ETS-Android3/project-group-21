package ca.mcgill.ecse321.library.service;

import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.library.dao.OpeningHourRepository;
import ca.mcgill.ecse321.library.models.OpeningHour;
import ca.mcgill.ecse321.library.models.OpeningHour.DayOfWeek;

@Service 
public class OpeningHourService {
	
	@Autowired
	OpeningHourRepository openingHourRepository;
	
	@Transactional
	public OpeningHour createOpeningHour(DayOfWeek day, Time startTime, Time endTime) {
		
		String error = "";
		
		if (day == null) {
			error = error + "Opening Hour day cannot be empty!";
		}
		if (startTime == null) {
			error = error + "Opening Hour start time cannot be empty!";
		}
		
		if (endTime == null) {
			error = error + "Opening Hour end time cannot be empty!";
		}
		
		if (endTime != null && startTime != null && endTime.before(startTime)) {
	        error = error + "Opening Hour end time cannot be before event start time!";
	    }
		
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		
		OpeningHour o = new OpeningHour();
		o.setDay(day);
		o.setStartTime(startTime);
		o.setEndTime(endTime);
		
		openingHourRepository.save(o);
		return o;
	}
	
	@Transactional
	public OpeningHour getOpeningHourByDay(DayOfWeek day) {
		return openingHourRepository.findOpeningHourByDay(day);
		
	}
	
	@Transactional
	public List<OpeningHour> getAllOpeningHour(){
		return (List<OpeningHour>) openingHourRepository.findAll();
	}
	
	@Transactional 
	public OpeningHour deleteOpeningHour(OpeningHour o) {
		if(o == null) {
			throw new IllegalArgumentException("Inputed Opening Hour must not be null!");
		}
		
		openingHourRepository.delete(o);
		o = null;
		return o;
	}
	
	@Transactional
	public OpeningHour updateOpeningHourDayOfWeek(OpeningHour o, DayOfWeek day) {
		if (day == null) {
			throw new IllegalArgumentException("Opening Hour day cannot be empty!");
		}
		o.setDay(day);
		openingHourRepository.save(o);
		return o;
	}
	
	@Transactional
	public OpeningHour updateOpeningHourStartTime(OpeningHour o, Time startTime) {
		if (startTime == null) {
			throw new IllegalArgumentException("Opening Hour start time cannot be empty!");
		}
		o.setStartTime(startTime);
		openingHourRepository.save(o);
		return o;
	}
	
	@Transactional
	public OpeningHour updateOpeningHourEndTime(OpeningHour o, Time startTime, Time endTime) {
		if (endTime == null) {
			throw new IllegalArgumentException("Opening Hour end time cannot be empty!");
		}
		
		if (endTime != null && startTime != null && endTime.before(startTime)) {
			throw new IllegalArgumentException("Opening Hour end time cannot be before event start time!");
	    }
		
		
		o.setEndTime(endTime);
		openingHourRepository.save(o);
		return o;
	}
}
