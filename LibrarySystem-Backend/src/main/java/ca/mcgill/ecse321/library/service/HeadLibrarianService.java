package ca.mcgill.ecse321.library.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.library.dao.*;
import ca.mcgill.ecse321.library.models.*;

@Service
public class HeadLibrarianService {
	@Autowired
	private ca.mcgill.ecse321.library.dao.HeadLibrarianRepository headLibrarianRepository;
	
}
