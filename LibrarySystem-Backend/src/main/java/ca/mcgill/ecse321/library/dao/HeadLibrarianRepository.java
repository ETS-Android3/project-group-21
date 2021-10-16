package ca.mcgill.ecse321.library.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.library.models.HeadLibrarian;

public interface HeadLibrarianRepository extends CrudRepository<HeadLibrarian, Integer>{

	HeadLibrarian findHeadLibrarianById(int Id);

}
