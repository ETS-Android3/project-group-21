package ca.mcgill.ecse321.library.dto;

import java.sql.Time;

import ca.mcgill.ecse321.library.models.OpeningHour.DayOfWeek;

public class OpeningHourDto {
	
	  //OpeningHour Attributes
	  private Time startTime;
	  private Time endTime;
	  private DayOfWeek day;
	  
	  /*
	   * @Author: Dania Pennimpede
	   * Create different opening hour dto methods using different inputs
	   */
	  public OpeningHourDto() {
		  
	  }
	  
	  public OpeningHourDto(DayOfWeek day) {
		  this(day, Time.valueOf("09:00:00"), Time.valueOf("17:00:00"));
	  }
	  
	  public OpeningHourDto(DayOfWeek day, Time startTime, Time endTime) {
		  this.day = day;
		  this.startTime = startTime;
		  this.endTime = endTime;
		  
	  }
	  
	  /*
	   * @Author: Dania Pennimpede
	   * Get the day of the opening hour
	   */
	  
	  public DayOfWeek getDay() {
		  return day;
	  }
	  
	  /*
	   * @Author: Dania Pennimpede
	   * Get the start time of the opening hour
	   */
	  public Time getStartTime() {
		  return startTime;
	  }
	  
	  /*
	   * @Author: Dania Pennimpede
	   * Get the end time of the opening hour
	   */
	  public Time getEndTime() {
		  return endTime;
	  }
	  
	  
	  
}
