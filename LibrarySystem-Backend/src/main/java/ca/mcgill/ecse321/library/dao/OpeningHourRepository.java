package ca.mcgill.ecse321.library.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.library.models.OpeningHour;

import ca.mcgill.ecse321.library.models.OpeningHour.DayOfWeek;

public interface OpeningHourRepository extends CrudRepository<OpeningHour, DayOfWeek> {
	
	OpeningHour findOpeningHourByDay(DayOfWeek day);
}
