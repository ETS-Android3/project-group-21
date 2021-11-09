package ca.mcgill.ecse321.library.service;

import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.library.dao.ShiftRepository;
import ca.mcgill.ecse321.library.models.Shift;
import ca.mcgill.ecse321.library.models.Shift.DayOfWeek;
import ca.mcgill.ecse321.library.models.ApplicationUser;
import ca.mcgill.ecse321.library.models.HeadLibrarian;
import ca.mcgill.ecse321.library.models.Librarian;

@Service
public class ShiftService {
    
    @Autowired
    ShiftRepository shiftRepository;

    @Transactional
    public Shift createShift(Long shiftCode, Time startTime, Time endTime, DayOfWeek day, ApplicationUser user){

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

        if (user == null){
            throw new IllegalArgumentException ("ApplicationUser cannot be empty");
        }
       
        
        if (!(user instanceof Librarian || user instanceof HeadLibrarian)){
            throw new IllegalArgumentException ("Shifts can only be assigned to Librarians or the Headlibrarian");
        }
        

        Shift aShift=new Shift();
        aShift.setStartTime(startTime);
        aShift.setEndTime(endTime);
        aShift.setDay(day);
        aShift.setShiftCode(shiftCode);
        aShift.setApplicationUser(user);

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

    //update all the parameters in one shot because it is more logical to implement with in interface afterwards
    @Transactional
    public Shift updateShift(Shift aShift, Time startTime, Time endTime, DayOfWeek day, ApplicationUser user){
        if (aShift == null){
            throw new IllegalArgumentException("Input shift cannot be null");
        }

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

        if (user == null){
            throw new IllegalArgumentException ("ApplicationUser cannot be empty");
        }
       
        
        if (!(user instanceof Librarian || user instanceof HeadLibrarian)){
            throw new IllegalArgumentException ("Shifts can only be assigned to Librarians or the Headlibrarian");
        }
        
        shiftRepository.delete(aShift);
        aShift.setStartTime(startTime);
        aShift.setEndTime(endTime);
        aShift.setDay(day);
        //aShift.setShiftCode(shiftCode);
        //if we want to update a shift then the shift code is the only onstant normally
        aShift.setApplicationUser(user);
        shiftRepository.save(aShift);
        return aShift;
    }

    //The Shift Code isn't something that should be editted
}
