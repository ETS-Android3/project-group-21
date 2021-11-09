package ca.mcgill.ecse321.library.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;

import java.sql.Time;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;

import ca.mcgill.ecse321.library.dao.OpeningHourRepository;
import ca.mcgill.ecse321.library.models.OpeningHour;
import ca.mcgill.ecse321.library.models.OpeningHour.DayOfWeek;

@ExtendWith(MockitoExtension.class)
public class TestOpeningHourService {
	
	@Mock
	private OpeningHourRepository openinghourDao;
	
	@InjectMocks
	private OpeningHourService openinghourservice;
	
	//two instances of day (examples)
	private static final DayOfWeek day = DayOfWeek.Friday;
	private static final DayOfWeek day2 = DayOfWeek.Saturday;
	
	//instances of startTime and endTime
	private static final Time startTime = Time.valueOf("09:00:00");
	private static final Time endTime = Time.valueOf("17:00:00");
	
	/*
	 * @Author: Dania Pennimpede
	 * Mock of opening hour 
	 * 
	 */
	@BeforeEach
	public void SetMockUp() {
		lenient().when(openinghourDao.findOpeningHourByDay(any(DayOfWeek.class))).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(day)) {
				OpeningHour openingHour = new OpeningHour();
				openingHour.setDay(day);
				openingHour.setStartTime(startTime);
				openingHour.setEndTime(endTime);
				return openingHour;
			}
			else {
				return null;
			}
		});
		
	}
	
	/* 
	 * @Author: Dania Pennimpede
	 * Tests for createOpeningHour method	
	 */

	@Test
	public void testCreateOpeningHour() {
		assertEquals(0, openinghourservice.getAllOpeningHour().size()); //check if exists
		
		//instance variables
		DayOfWeek day = DayOfWeek.Tuesday;
		Time startTime = Time.valueOf("09:00:00");
		Time endTime = Time.valueOf("10:30:00");
		
		OpeningHour openingHour = null; //create empty opening hour 
		try {
			openingHour = openinghourservice.createOpeningHour(day, startTime, endTime); 
		}
		catch(IllegalArgumentException e) {
			fail();
		}
		
		// assert if our service works
		assertNotNull(openingHour);
		assertEquals(day, openingHour.getDay());
		assertEquals(startTime, openingHour.getStartTime());
		assertEquals(endTime, openingHour.getEndTime());
		
	}
	
	/* 
	 * @Author: Dania Pennimpede
	 * Tests for createOpeningHour method if opening hour is null
	 */
	@Test
	public void testCreateOpeningHourNull() {
		String error = "";
		
		//instance variables
		DayOfWeek day = null;
		Time startTime = null;
		Time endTime = null;
		
		OpeningHour openingHour = null; //create empty opening hour 
		
		try {
			
			openingHour = openinghourservice.createOpeningHour(day, startTime, endTime);
		} catch (IllegalArgumentException e) {
			error = e.getMessage(); //get message if something does not work
			
		}
		
		//check if errors in service method works
		assertNull(openingHour);
		assertEquals("Opening Hour day cannot be empty!Opening Hour start time cannot be empty!Opening Hour end time cannot be empty!", error);
		
	}
	
	/* 
	 * @Author: Dania Pennimpede
	 * Tests for createOpeningHour method if opening hour end time is before start time
	 */
	@Test
	public void testCreateOpeningHourEndTimeBeforeStartTime() {
		String error ="";
		
		//instance variables
		DayOfWeek day = DayOfWeek.Thursday;
		LocalTime startTime = LocalTime.parse("09:00");
		LocalTime endTime = LocalTime.parse("08:59");
		
		OpeningHour openingHour = null;//create empty opening hour 
		try {
			openingHour = openinghourservice.createOpeningHour(day, Time.valueOf(startTime), Time.valueOf(endTime));
		} catch (IllegalArgumentException e) {
			error = e.getMessage();  //get message if something does not work
		}
		
		//check if errors in service method works
		assertNull(openingHour);
		assertEquals("Opening Hour end time cannot be before event start time!", error);
		
	}

	
	/*
	 * @Author: Dania Pennimpede
	 * Tests for deleteOpeningHour method
	 */
	
	@Test
	public void testDeleteOpeningHour() {
		OpeningHour openingHour = null;
		
		//instance variables
		DayOfWeek day = DayOfWeek.Wednesday;
		LocalTime startTime = LocalTime.parse("09:00");
		LocalTime endTime = LocalTime.parse("10:30");
		
		openingHour = openinghourservice.createOpeningHour(day, Time.valueOf(startTime), Time.valueOf(endTime)); //create valid opening hour
		
		assertNotNull(openingHour); //make sure it exists
		
		try {
			openingHour = openinghourservice.deleteOpeningHour(openingHour); //try deleting the created opening hour
		}   catch(IllegalArgumentException e) {
			fail();
		}
		assertNull(openingHour); //check if it is deleted by seeing if it is null
	}
	
	/*
	 * @Author: Dania Pennimpede
	 * Tests for deleteOpeningHour method if opening hour is null
	 */
	@Test
	public void testDeleteOpeningHourNull() {
		String error ="";
		
		OpeningHour openingHour = null; //null opening hour
		
		try {
			openingHour = openinghourservice.deleteOpeningHour(openingHour); //try deleting the null opening hour
		} catch(IllegalArgumentException e) {
			error = e.getMessage(); //get message if something does not work
		}
		
		//check if errors in service method works
		assertNull(openingHour);
		assertEquals("Inputed Opening Hour must not be null!", error);
		
	}
	
		
	/*
	 * @Author: Dania Pennimpede
	 * Tests for update OpeningHour method
	 */
	
	@Test
	public void testUpdateOpeningHourDay() {
		
		//instance variables
		DayOfWeek day = DayOfWeek.Tuesday;
		LocalTime startTime = LocalTime.parse("09:00");
		LocalTime endTime = LocalTime.parse("10:30");
		
		OpeningHour openingHour1 = openinghourservice.createOpeningHour(day, Time.valueOf(startTime), Time.valueOf(endTime)); //create valid opening hour
		
		OpeningHour openingHour2 = null; //create null opening hour
		OpeningHour.DayOfWeek day2 = OpeningHour.DayOfWeek.Wednesday;
		
		try {
			openingHour2 = openinghourservice.updateOpeningHourDayOfWeek(openingHour1, day2); //update day
		} catch(IllegalArgumentException e) {
		 	fail();
		}
		
		assertNotNull(openingHour2); //make sure the opening hour exists
		assertEquals(day2, openingHour2.getDay()); //make sure the correct date is there
	}
	
	
	
	@Test
	public void testUpdateOpeningHourDayFail() {
		String error = "";
		
		//instance variables
		DayOfWeek day = DayOfWeek.Tuesday;
		LocalTime startTime = LocalTime.parse("09:00");
		LocalTime endTime = LocalTime.parse("10:30");
		
		OpeningHour openingHour1 = openinghourservice.createOpeningHour(day, Time.valueOf(startTime), Time.valueOf(endTime)); //create valid opening hour
		
		OpeningHour openingHour2 = null; //create null opening hour
		OpeningHour.DayOfWeek day2 = null;
		try {
			openingHour2 = openinghourservice.updateOpeningHourDayOfWeek(openingHour1, day2); //update day
		} catch(IllegalArgumentException e) {
			error = e.getMessage();  //get message if something does not work
		}
		
		assertNull(openingHour2); //make sure opening hour does not exist since day does not exist
		assertEquals("Opening Hour day cannot be empty!", error); //check if error is correct
	}
	
	@Test
	public void testUpdateOpeningHourStartTime() {
		String error = "";
		
		//instance variables
		DayOfWeek day = DayOfWeek.Tuesday;
		Time startTime = Time.valueOf("09:00:00");
		Time endTime = Time.valueOf("10:30:00");
		
		OpeningHour openingHour1 = openinghourservice.createOpeningHour(day, startTime, endTime); //create valid opening hour
		OpeningHour openingHour2 = null;
		
		Time startTime2 = Time.valueOf("10:00:00");
		
		try {
			openingHour2 = openinghourservice.updateOpeningHourStartTime(openingHour1,startTime2); //update start time
	} catch(IllegalArgumentException e) {
		fail();
	}
		
		assertNotNull(openingHour2); //make sure the opening hour exists
		assertEquals(startTime2, openingHour2.getStartTime()); //make sure the correct start time is there
		

	}
	
	@Test
	public void testUpdateOpeningHourStartTimeFail() {
		String error = "";
		
		//instance variables
		DayOfWeek day = DayOfWeek.Tuesday;
		Time startTime = Time.valueOf("09:00:00");
		Time endTime = Time.valueOf("10:30:00");
		
		OpeningHour openingHour1 = openinghourservice.createOpeningHour(day, startTime, endTime); //create valid opening hour
		OpeningHour openingHour2 = null;
		
		Time startTime2 = null;
		
		try {
			openingHour2 = openinghourservice.updateOpeningHourStartTime(openingHour1,startTime2);  //update start time
	} catch(IllegalArgumentException e) {
		error = e.getMessage();
	}
		
		assertNull(openingHour2); //make sure opening hour does not exist since start time does not exist
		assertEquals("Opening Hour start time cannot be empty!", error); //check if error is correct
		

	}
	
	
	@Test
	public void testUpdateOpeningHourEndTime() {
		String error = "";
		
		//instance variables
		DayOfWeek day = DayOfWeek.Tuesday;
		Time startTime = Time.valueOf("09:00:00");
		Time endTime = Time.valueOf("10:30:00");
		
		OpeningHour openingHour1 = openinghourservice.createOpeningHour(day, startTime, endTime); //create valid opening hour
		OpeningHour openingHour2 = null;
		
		Time endTime2 = Time.valueOf("11:00:00");
		
		try {
			openingHour2 = openinghourservice.updateOpeningHourEndTime(openingHour1, startTime, endTime2); //update end time
	} catch(IllegalArgumentException e) {
		fail();
	}
		
		assertNotNull(openingHour2);  //make sure the opening hour exists
		assertEquals(endTime2, openingHour2.getEndTime()); //make sure the correct end time is there
	}
	
	@Test
	public void testUpdateOpeningHourEndTimeFail() {
		String error = "";
		
		//instance variables
		DayOfWeek day = DayOfWeek.Tuesday;
		Time startTime = Time.valueOf("09:00:00");
		Time endTime = Time.valueOf("10:30:00");
		
		OpeningHour openingHour1 = openinghourservice.createOpeningHour(day, startTime, endTime); //create valid opening hour
		OpeningHour openingHour2 = null;
		
		Time endTime2 = null;
		
		try {
			openingHour2 = openinghourservice.updateOpeningHourEndTime(openingHour1, startTime, endTime2);//update end time
	} catch(IllegalArgumentException e) {
		error = e.getMessage();
	}
		
		assertNull(openingHour2); //make sure opening hour does not exist since end time does not exist
		assertEquals("Opening Hour end time cannot be empty!", error); //check if error is correct
		

	}
	
	@Test
	public void testCUpdateOpeningHourEndTimeBeforeStartTime() {
		String error = "";
		
		//instance variables
		DayOfWeek day = DayOfWeek.Tuesday;
		Time startTime = Time.valueOf("09:00:00");
		Time endTime = Time.valueOf("10:30:00");
		
		OpeningHour openingHour1 = openinghourservice.createOpeningHour(day, startTime, endTime); //create valid opening hour
		OpeningHour openingHour2 = null;
		
		Time endTime2 = Time.valueOf("08:59:00");
		
		try {
			openingHour2 = openinghourservice.updateOpeningHourEndTime(openingHour1,startTime, endTime2); //update end time
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(openingHour2); //make sure opening hour does not exist since end time is before start time
		assertEquals("Opening Hour end time cannot be before event start time!", error); //check if error is correct
		
	}
	
	/* 
	 * @Author: Dania Pennimpede
	 * Tests for getOpeningHour method
	 */
	
	@Test
	public void testGetExistingOpeningHour() {
		assertEquals(day, openinghourservice.getOpeningHourByDay(day).getDay());
	}
	
	@Test
	public void testGetNonExistingOpeningHour() {
		assertNull(openinghourservice.getOpeningHourByDay(day2));
	}
	
}
