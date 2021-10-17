package ca.mcgill.ecse321.library.dao;

import java.sql.Time;
import java.time.LocalTime;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.library.models.OpeningHour;
import ca.mcgill.ecse321.library.models.OpeningHour.DayOfWeek;




@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestOpeningHourPersistence {

	@Autowired
	private OpeningHourRepository openingHourRepository;
	
	@AfterEach
	public void clearDatabase() {
		openingHourRepository.deleteAll();
	}
	
	@Test
	@Transactional
	public void testPersistandLoadOpeningHour() {
		//create an instance of OpeningHour
		OpeningHour o = new OpeningHour();
		
		// initialize and set attributes to the library item
		Time startTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
		Time endTime = java.sql.Time.valueOf(LocalTime.of(13, 25));
		DayOfWeek day = DayOfWeek.Tuesday;
		
		//set the attributes to the instance o
		o.setStartTime(startTime);
		o.setEndTime(endTime);
		o.setDay(day);
		
		//save the opening hour
		openingHourRepository.save(o);
		
		//set opening hour to null
		o = null;
		
		//find the opening hour
		o = openingHourRepository.findOpeningHourByDay(day);
		
		//assert to see if tests work
		assertNotNull(o);
		assertEquals(startTime, o.getStartTime());
		assertEquals(endTime, o.getEndTime());
		assertEquals(day, o.getDay());
		
	}
}
