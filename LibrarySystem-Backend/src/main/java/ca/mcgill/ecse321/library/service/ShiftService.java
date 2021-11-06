package ca.mcgill.ecse321.library.service;

import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.library.dao.ShiftRepository;
import ca.mcgill.ecse321.library.models.Shift;
import ca.mcgill.ecse321.library.models.Shift.DayOfWeek;
//should only need to import the shift class to get the days of the week enum class

@Service
public class ShiftService {
    
    @Autowired
    ShiftRepository shiftRepository;

    @Transactional
    public Shift createShift(Long shiftCode, Time startTime, Time endTime, DayOfWeek day){

        if (startTime == null) {
            throw new IllegalArgumentException ("Shift must have a starting time");
        }

        if (endTime == null) {
            throw new IllegalArgumentException ("Shift must have a ending time");
        }

        if (day == null) {
            throw new IllegalArgumentException ("Shift must be on a day of the week");
        }

        if (endTime != null && startTime != null && endTime.before(startTime)) {
	        throw new IllegalArgumentException ("Shift end time cannot be before its start time");
	    }

        Shift aShift=new Shift();
        aShift.setStartTime(startTime);
        aShift.setEndTime(endTime);
        aShift.setDay(day);
        aShift.setShiftCode(shiftCode);

        shiftRepository.save(aShift);
        return aShift;
    }

    @Transactional
    public Shift getShift(Long shiftCode){
        return shiftRepository.findShiftByShiftCode(shiftCode);
    }

    @Transactional
    public List<Shift> getAllShifts(){
        return (List<Shift>) shiftRepository.findAll();
    } 

    @Transactional
	public Shift deleteShift(Shift shift) {
		if(shift == null) {
			throw new IllegalArgumentException("Input shift cannot be null");
		}
		shiftRepository.delete(shift);
		shift = null;
		return shift;
	}

    @Transactional
    public Shift updateShiftStartTime (Shift aShift, Time startTime){
        if (aShift == null){
            throw new IllegalArgumentException("Input shift cannot be null");
        }

        if (startTime == null){
            throw new IllegalArgumentException ("Shift must have a starting time");
        }

        Time endTime = aShift.getEndTime();

        if (startTime.after(endTime)){
            throw new IllegalArgumentException ("Shift end time cannot be before its start time");
        }

        shiftRepository.delete(aShift);
        aShift.setStartTime(startTime);
        shiftRepository.save(aShift);
        return aShift;
    }

    @Transactional
    public Shift updateShiftEndTime (Shift aShift, Time endTime){
        if (aShift == null){
            throw new IllegalArgumentException("Input shift cannot be null");
        }

        if (endTime == null){
            throw new IllegalArgumentException ("Shift must have a ending time");
        }


        Time startTime=aShift.getStartTime();

        if (endTime != null && startTime != null && endTime.before(startTime)) {
	        throw new IllegalArgumentException ("Shift end time cannot be before its start time");
	    }

        shiftRepository.delete(aShift);
        aShift.setEndTime(endTime);
        shiftRepository.save(aShift);
        return aShift;
    }
    
    @Transactional
    public Shift updateShiftDay (Shift aShift, DayOfWeek day){
        if (aShift == null){
            throw new IllegalArgumentException("Input shift cannot be null");
        }

        if (day == null){
            throw new IllegalArgumentException ("Shift must be on a day of the week");
        }

        shiftRepository.delete(aShift);
        aShift.setDay(day);
        shiftRepository.save(aShift);
        return aShift;
    }

    //The Shift Code isn't something that should be editted
}
