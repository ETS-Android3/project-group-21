package ca.mcgill.ecse321.library.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.library.models.HeadLibrarian;
import ca.mcgill.ecse321.library.models.Librarian;
import ca.mcgill.ecse321.library.models.Shift;

public interface ShiftRepository extends CrudRepository<Shift, Long>{
    
    Shift findShiftByShiftCode(long shiftCode); 
//    List<Shift> findShiftByLibrarians(Librarian librarians);
//    List<Shift> findShiftByHeadLibrarian(HeadLibrarian headlibrarian);

}
