
package ca.mcgill.ecse321.library.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.library.models.Shift;
import ca.mcgill.ecse321.library.models.Librarian;

public interface LibrarianRepository extends CrudRepository<Librarian, Long>{
    
    Librarian findLibrarianByCardID(long cardID);
//    List<Librarian> findLibrarianByShift(Shift shift);

}
