package ca.mcgill.ecse321.library.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.library.models.Shift;

public interface ShiftRepository extends CrudRepository<Shift, Integer>{

	Shift findShiftByShiftCode(int shiftCode);

}