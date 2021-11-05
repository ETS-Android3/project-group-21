package ca.mcgill.ecse321.library.dto;

import java.sql.Time;

import ca.mcgill.ecse321.library.models.OpeningHour.DayOfWeek;

public class OpeningHourDto {
	
	  //OpeningHour Attributes
	  private Time startTime;
	  private Time endTime;
	  private DayOfWeek day;
	  
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
	  
	public DayOfWeek getDay() {
		  return day;
	  }
	  
	  public Time getStartTime() {
		  return startTime;
	  }
	  
	  public Time getEndTime() {
		  return endTime;
	  }
	  
	  
	  
}
