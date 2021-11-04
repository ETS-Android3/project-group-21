package ca.mcgill.ecse321.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.library.service.OpeningHourService;

@CrossOrigin(origins = "*")
@RestController
public class OpeningHourController {
	
	@Autowired
	private OpeningHourService service;

}
