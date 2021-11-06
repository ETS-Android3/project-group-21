package ca.mcgill.ecse321.library.dto;

import java.sql.Time;

import ca.mcgill.ecse321.library.models.Shift;
import ca.mcgill.ecse321.library.models.Shift.DayOfWeek;

public class ShiftDto {
    
    //Shift Attributes
    private Time startTime;
    private Time endTime;
    private DayOfWeek day;
    private Long shiftCode;

    public ShiftDto (){
        //empty constructor
    }
    
    public ShiftDto (Long shiftCode, DayOfWeek day){
        //most common shift is hopefully just a regular 8 to 5
        this(shiftCode, Time.valueOf("08:00:00"), Time.valueOf("17:00:00"),day);
    }

    public ShiftDto(Long shiftCode, Time valueOf, Time valueOf2, DayOfWeek day) {
        this.day = day;
		this.startTime = startTime;
		this.endTime = endTime;
        this.shiftCode = shiftCode;
    }
 
    public Time getStartTime() {
        return startTime;
    }
    
    public Time getEndTime() {
        return endTime;
    }

    public DayOfWeek getDay() {
        return day;
    }

    public Long getShiftCode(){
        return shiftCode;
    }
}
