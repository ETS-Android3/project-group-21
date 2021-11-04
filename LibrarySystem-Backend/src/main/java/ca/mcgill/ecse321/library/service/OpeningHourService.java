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
		openingHourRepository.delete(o);
		o = null;
		return o;
	}
	
	@Transactional
	public OpeningHour updateOpeningHourDayOfWeek(OpeningHour o, DayOfWeek day) {
		openingHourRepository.delete(o);
		o.setDay(day);
		openingHourRepository.save(o);
		return o;
	}
	
	@Transactional
	public OpeningHour updateOpeningHourStartTime(OpeningHour o, Time startTime) {
		openingHourRepository.delete(o);
		o.setStartTime(startTime);
		openingHourRepository.save(o);
		return o;
	}
	
	@Transactional
	public OpeningHour updateOpeningHourEndTime(OpeningHour o, Time endTime) {
		openingHourRepository.delete(o);
		o.setEndTime(endTime);
		openingHourRepository.save(o);
		return o;
	}
}
