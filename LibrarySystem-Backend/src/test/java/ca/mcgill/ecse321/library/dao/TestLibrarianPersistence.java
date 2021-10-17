package ca.mcgill.ecse321.library.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.same;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.library.models.*;
import ca.mcgill.ecse321.library.models.Shift.DayOfWeek;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestLibrarianPersistence {
    
    @Autowired
	private ShiftRepository shiftRepository;
	
	@Autowired
	private LibrarianRepository librarianRepository;

    @AfterEach
	public void clearDatabase() {
		shiftRepository.deleteAll();
        librarianRepository.deleteAll();
	}

    @Test
    @Transactional
	void testPersistAndLoadLibrarian() {
        
        //initialize and set attributes to the librarian
        //initialize a shift
        Shift s2=new Shift();
        s2.setDay(DayOfWeek.Monday);
        DayOfWeek day=s2.getDay();
        s2.setStartTime(new Time(6,30,0));
        s2.setEndTime(new Time(16,30,0));
        shiftRepository.save(s2);
        long sCode=s2.getShiftCode();
        List sl = new ArrayList<Shift>();
        sl.add(s2);

        //initiate a librarian

        Librarian l2=new Librarian();
        //l2.setShift(sl);

        librarianRepository.save(l2);
        long libId = l2.getCardID();
        

        //remove reference
        l2=null;

        //load in persistence
        
        l2=librarianRepository.findLibrarianByCardID(libId);
        assertNotNull(l2);
        //assertEquals(sCode, l2.getShift().get(0).getShiftCode());
        assertEquals(libId, l2.getCardID());

	}
}
