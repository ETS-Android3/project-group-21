package ca.mcgill.ecse321.library.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.library.models.HeadLibrarian;
import ca.mcgill.ecse321.library.models.Librarian;
import ca.mcgill.ecse321.library.models.Shift;

public interface ShiftRepository extends CrudRepository<Shift, Integer>{
    
    Shift findReservationByShiftCode(int shiftCode); 
    List<Shift> findByLibrarians(Librarian librarians);
    List<Shift> findByHeadLibrarian(HeadLibrarian headlibrarian);

    //this works now, no touch
}
