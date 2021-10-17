package ca.mcgill.ecse321.library.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
public class TestShiftPersistence {
    
    @Autowired
	private ShiftRepository shiftRepository;
	
	@Autowired
	private LibrarianRepository librarianRepository;

    @Autowired
	private HeadLibrarianRepository headLibrarianRepository;

    @AfterEach
	public void clearDatabase() {
		shiftRepository.deleteAll();
        librarianRepository.deleteAll();
        headLibrarianRepository.deleteAll();
	}

	@Test
	@Transactional
	void testPersistAndLoadShift() {

        // initialize and set attributes to the shift
        // initialize the librarian
        Librarian l = new Librarian();
		librarianRepository.save(l);
		long userIdl = l.getCardID();
        List ls=new ArrayList<Librarian>();
        ls.add(l);

        // initialize the headlibrarian
        HeadLibrarian hl = new HeadLibrarian();
		headLibrarianRepository.save(hl);
		long userIdhl = hl.getCardID();
        

        //initialize shift
        Shift s=new Shift();
        s.setDay(DayOfWeek.Monday);
        DayOfWeek day=s.getDay();
        s.setLibrarians(ls);
        s.setHeadLibrarian(hl);
        shiftRepository.save(s);
        long sCode=s.getShiftCode();

        s = null;   //remove reference

        s=shiftRepository.findShiftByShiftCode(sCode);
        assertNotNull(s);
        assertEquals(userIdhl, s.getHeadLibrarian().getCardID());
        assertEquals(userIdl, s.getLibrarians().get(0).getCardID());
        assertEquals(sCode, s.getShiftCode()); 
        assertEquals(day, s.getDay());
	}
    
}
