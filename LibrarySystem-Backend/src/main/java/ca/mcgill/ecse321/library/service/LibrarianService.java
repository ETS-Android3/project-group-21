package ca.mcgill.ecse321.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.library.dao.*;
import ca.mcgill.ecse321.library.models.*;

@Service
public class LibrarianService {
	@Autowired
	private ca.mcgill.ecse321.library.dao.LibrarianRepository librarianRepository;
}
