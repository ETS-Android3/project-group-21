package ca.mcgill.ecse321.library.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.library.models.HeadLibrarian;
import ca.mcgill.ecse321.library.models.Librarian;
import ca.mcgill.ecse321.library.models.Shift;

public interface ShiftRepository extends CrudRepository<Shift, Integer>{
    
    Shift findShiftByShiftCode(int shiftCode); 
    List<Shift> findShiftByLibrarians(Librarian librarians);
    List<Shift> findShiftByHeadLibrarian(HeadLibrarian headlibrarian);

    //this works now, no touch
}
