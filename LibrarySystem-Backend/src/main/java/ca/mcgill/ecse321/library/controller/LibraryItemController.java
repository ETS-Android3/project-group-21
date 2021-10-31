package ca.mcgill.ecse321.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.library.service.LibraryItemService;

@CrossOrigin(origins = "*") 
/* CrossOrigin: allows requests to come accross different platforms (eg. jump from the other websites) */
@RestController
public class LibraryItemController {
	
	@Autowired
	private LibraryItemService libraryItemService;

}
