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
	
	private static final DayOfWeek day = DayOfWeek.Friday;
	private static final DayOfWeek day2 = DayOfWeek.Saturday;
	
	
	
	private static final Time startTime = Time.valueOf("09:00:00");
	private static final Time endTime = Time.valueOf("17:00:00");
	
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
	 * Tests for createOpeningHour method	
	 */

	@Test
	public void testCreateOpeningHour() {
		assertEquals(0, openinghourservice.getAllOpeningHour().size());
		
		DayOfWeek day = DayOfWeek.Tuesday;
		Time startTime = Time.valueOf("09:00:00");
		Time endTime = Time.valueOf("10:30:00");
		
		OpeningHour openingHour = null;
		try {
			openingHour = openinghourservice.createOpeningHour(day, startTime, endTime);
		}
		catch(IllegalArgumentException e) {
			fail();
		}
		
		assertNotNull(openingHour);
		assertEquals(day, openingHour.getDay());
		assertEquals(startTime, openingHour.getStartTime());
		assertEquals(endTime, openingHour.getEndTime());
		
	}
	
	@Test
	public void testCreateOpeningHourNull() {
		String error = "";
		
		DayOfWeek day = null;
		Time startTime = null;
		Time endTime = null;
		
		OpeningHour openingHour = null;
		
		try {
			
			openingHour = openinghourservice.createOpeningHour(day, startTime, endTime);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
			
		}
		
		assertNull(openingHour);
		assertEquals("Opening Hour day cannot be empty!Opening Hour start time cannot be empty!Opening Hour end time cannot be empty!", error);
		
	}
	
	
	@Test
	public void testCreateOpeningHourEndTimeBeforeStartTime() {
		String error ="";
		
		DayOfWeek day = DayOfWeek.Thursday;
		LocalTime startTime = LocalTime.parse("09:00");
		LocalTime endTime = LocalTime.parse("08:59");
		
		OpeningHour openingHour = null;
		try {
			openingHour = openinghourservice.createOpeningHour(day, Time.valueOf(startTime), Time.valueOf(endTime));
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(openingHour);
		assertEquals("Opening Hour end time cannot be before event start time!", error);
		
	}

	
	/*
	 * Tests for deleteOpeningHour method
	 */
	
	@Test
	public void testDeleteOpeningHour() {
		OpeningHour openingHour = null;
		
		DayOfWeek day = DayOfWeek.Wednesday;
		LocalTime startTime = LocalTime.parse("09:00");
		LocalTime endTime = LocalTime.parse("10:30");
		
		openingHour = openinghourservice.createOpeningHour(day, Time.valueOf(startTime), Time.valueOf(endTime));
		
		assertNotNull(openingHour);
		
		try {
			openingHour = openinghourservice.deleteOpeningHour(openingHour);
		}   catch(IllegalArgumentException e) {
			fail();
		}
		assertNull(openingHour);
	}
	
	@Test
	public void testDeleteOpeningHourNull() {
		String error ="";
		
		OpeningHour openingHour = null;
		
		try {
			openingHour = openinghourservice.deleteOpeningHour(openingHour);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(openingHour);
		assertEquals("Inputed Opening Hour must not be null!", error);
		
	}
	
		
	/*
	 * Tests for update OpeningHour method
	 */
	
	@Test
	public void testUpdateOpeningHourDay() {
		
		DayOfWeek day = DayOfWeek.Tuesday;
		LocalTime startTime = LocalTime.parse("09:00");
		LocalTime endTime = LocalTime.parse("10:30");
		
		OpeningHour openingHour1 = openinghourservice.createOpeningHour(day, Time.valueOf(startTime), Time.valueOf(endTime));
		
		OpeningHour openingHour2 = null;
		OpeningHour.DayOfWeek day2 = OpeningHour.DayOfWeek.Wednesday;
		
		try {
			openingHour2 = openinghourservice.updateOpeningHourDayOfWeek(openingHour1, day2);
		} catch(IllegalArgumentException e) {
		 	fail();
		}
		
		assertNotNull(openingHour2);
		assertEquals(day2, openingHour2.getDay());
	}
	
	@Test
	public void testUpdateOpeningHourDayFail() {
		String error = "";
		
		DayOfWeek day = DayOfWeek.Tuesday;
		LocalTime startTime = LocalTime.parse("09:00");
		LocalTime endTime = LocalTime.parse("10:30");
		
		OpeningHour openingHour1 = openinghourservice.createOpeningHour(day, Time.valueOf(startTime), Time.valueOf(endTime));
		
		OpeningHour openingHour2 = null;
		OpeningHour.DayOfWeek day2 = null;
		try {
			openingHour2 = openinghourservice.updateOpeningHourDayOfWeek(openingHour1, day2);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(openingHour2);
		assertEquals("Opening Hour day cannot be empty!", error);
	}
	
	@Test
	public void testUpdateOpeningHourStartTime() {
		String error = "";
		
		DayOfWeek day = DayOfWeek.Tuesday;
		Time startTime = Time.valueOf("09:00:00");
		Time endTime = Time.valueOf("10:30:00");
		
		OpeningHour openingHour1 = openinghourservice.createOpeningHour(day, startTime, endTime);
		OpeningHour openingHour2 = null;
		
		Time startTime2 = Time.valueOf("10:00:00");
		
		try {
			openingHour2 = openinghourservice.updateOpeningHourStartTime(openingHour1,startTime2);
	} catch(IllegalArgumentException e) {
		fail();
	}
		
		assertNotNull(openingHour2);
		assertEquals(startTime2, openingHour2.getStartTime());
		

	}
	
	@Test
	public void testUpdateOpeningHourStartTimeFail() {
		String error = "";
		
		DayOfWeek day = DayOfWeek.Tuesday;
		Time startTime = Time.valueOf("09:00:00");
		Time endTime = Time.valueOf("10:30:00");
		
		OpeningHour openingHour1 = openinghourservice.createOpeningHour(day, startTime, endTime);
		OpeningHour openingHour2 = null;
		
		Time startTime2 = null;
		
		try {
			openingHour2 = openinghourservice.updateOpeningHourStartTime(openingHour1,startTime2);
	} catch(IllegalArgumentException e) {
		error = e.getMessage();
	}
		
		assertNull(openingHour2);
		assertEquals("Opening Hour start time cannot be empty!", error);
		

	}
	
	
	@Test
	public void testUpdateOpeningHourEndTime() {
		String error = "";
		
		DayOfWeek day = DayOfWeek.Tuesday;
		Time startTime = Time.valueOf("09:00:00");
		Time endTime = Time.valueOf("10:30:00");
		
		OpeningHour openingHour1 = openinghourservice.createOpeningHour(day, startTime, endTime);
		OpeningHour openingHour2 = null;
		
		Time endTime2 = Time.valueOf("11:00:00");
		
		try {
			openingHour2 = openinghourservice.updateOpeningHourEndTime(openingHour1, startTime, endTime2);
	} catch(IllegalArgumentException e) {
		fail();
	}
		
		assertNotNull(openingHour2);
		assertEquals(endTime2, openingHour2.getEndTime());
	}
	
	@Test
	public void testUpdateOpeningHourEndTimeFail() {
		String error = "";
		
		DayOfWeek day = DayOfWeek.Tuesday;
		Time startTime = Time.valueOf("09:00:00");
		Time endTime = Time.valueOf("10:30:00");
		
		OpeningHour openingHour1 = openinghourservice.createOpeningHour(day, startTime, endTime);
		OpeningHour openingHour2 = null;
		
		Time endTime2 = null;
		
		try {
			openingHour2 = openinghourservice.updateOpeningHourEndTime(openingHour1, startTime, endTime2);
	} catch(IllegalArgumentException e) {
		error = e.getMessage();
	}
		
		assertNull(openingHour2);
		assertEquals("Opening Hour end time cannot be empty!", error);
		

	}
	
	@Test
	public void testCUpdateOpeningHourEndTimeBeforeStartTime() {
		String error = "";
		
		DayOfWeek day = DayOfWeek.Tuesday;
		Time startTime = Time.valueOf("09:00:00");
		Time endTime = Time.valueOf("10:30:00");
		
		OpeningHour openingHour1 = openinghourservice.createOpeningHour(day, startTime, endTime);
		OpeningHour openingHour2 = null;
		
		Time endTime2 = Time.valueOf("08:59:00");
		
		try {
			openingHour2 = openinghourservice.updateOpeningHourEndTime(openingHour1,startTime, endTime2);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(openingHour2);
		assertEquals("Opening Hour end time cannot be before event start time!", error);
		
	}
	
	/* 
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
