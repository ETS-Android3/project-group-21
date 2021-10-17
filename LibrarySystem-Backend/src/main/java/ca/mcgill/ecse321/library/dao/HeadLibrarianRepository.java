package ca.mcgill.ecse321.library.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.library.models.Shift;
import ca.mcgill.ecse321.library.models.HeadLibrarian;
import ca.mcgill.ecse321.library.models.OpeningHour;


public interface HeadLibrarianRepository extends CrudRepository<HeadLibrarian, Long>{

    HeadLibrarian findHeadLibrarianByCardID(long cardID);
    HeadLibrarian findHeadLibrarianByShift(Shift shift);

    HeadLibrarian findHeadLibrarianByOpeninghour(OpeningHour openinghour);

}
