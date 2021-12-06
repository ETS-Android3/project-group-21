package ca.mcgill.ecse321.library.controller;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse321.library.dao.CitizenRepository;
import ca.mcgill.ecse321.library.dto.CitizenDto;
import ca.mcgill.ecse321.library.dto.LibrarianDto;
import ca.mcgill.ecse321.library.models.Citizen;
import ca.mcgill.ecse321.library.models.Librarian;
import ca.mcgill.ecse321.library.service.CitizenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class CitizenController {

    @Autowired
    private CitizenService citizenService;

    private CitizenDto convertToDto(Citizen citizen){
        if(citizen == null){
            throw new IllegalArgumentException("There is no such a citizen");
        }
        CitizenDto citizenDto = new CitizenDto(citizen.getCardID(),citizen.getFullName(), citizen.getAddress(),
                citizen.getUsername(),citizen.getPassword(),citizen.getIsLocal(),citizen.getBalance(),
                citizen.getOnlineAccountActivated());
        return citizenDto;
    }


    @GetMapping(value = {"/citizens","/citizens/"})
    public List<CitizenDto> getCitizens(){
        List<CitizenDto> citizenDtos = new ArrayList<>();
        for(Citizen citizen : citizenService.getAllCitizen()){
            citizenDtos.add(convertToDto(citizen));
        }
        return citizenDtos;
    }
    
	@GetMapping(value = { "/citizens/{cardID}", "/citizens/{cardID}/"})
	public CitizenDto getCitizenById(@PathVariable("cardID") Long cardID) throws IllegalArgumentException{
		return convertToDto(citizenService.getCitizenByID(cardID));
	}

//    @GetMapping(value = {"/citizens/{username}/{password}","/citizens/{username}/{password}/"})
//    public CitizenDto getCitizenByUsernameAndPassword(@PathVariable String username, @PathVariable String password){
//        if(username==null || password==null){
//            throw new IllegalArgumentException("no citizen with such username and password");
//        }
//        return convertToDto(citizenService.getCitizenByUsernameAndPassword(username,password));
//    }

    @PostMapping(value = {"/citizens/{cardID}", "/citizens/{cardID}/"})
    public CitizenDto createCitizen(@PathVariable Long cardID, @RequestParam String username, 
    		@RequestParam String password,@RequestParam String fullname,
    		@RequestParam String address, @RequestParam Boolean onlineAccountActivated,
            @RequestParam Boolean isLocal,@RequestParam Double balance){
        Citizen aCitizen = citizenService.creatCitizen(fullname,username,password,address,onlineAccountActivated,
                isLocal,balance, cardID);
        CitizenDto citizenDto = convertToDto(aCitizen);
        return citizenDto;
    }

	@DeleteMapping(value = { "/citizens/{cardID}", "/citizens/{cardID}/" })
	public void deleteCitizen(@PathVariable("cardID") Long cardID) throws IllegalArgumentException {
		Citizen citizen = citizenService.getCitizenByID(cardID);
		citizenService.deleteCitizen(citizen);
	}
    
    
	@PatchMapping(value = { "/citizens/{cardID}", "/citizens/{cardID}/" })
	public CitizenDto editCitizen(@PathVariable("cardID") Long cardID, 
			@RequestParam(required=false) String username, @RequestParam (required=false) String password,
			@RequestParam(required=false) String fullname,
			@RequestParam (required=false) String address, @RequestParam (required=false) Boolean onlineAccountActivated,
			@RequestParam(required=false) Boolean isLocal, @RequestParam(required=false) Double balance) throws IllegalArgumentException {
	
		Citizen citizen = citizenService.getCitizenByID(cardID);
		

		if (username != null) {
			citizenService.editCitizenUsername(citizen, username);
		}
		if (password != null) {
			citizenService.editCitizenPassword(citizen, password);
		}
		if (fullname != null) {
			citizenService.editCitizenFullname(citizen, fullname);
		}
		if (address != null) {
			citizenService.editCitizenAddress(citizen, address);
		}
		if (onlineAccountActivated != null) {
			citizenService.editCitizenOnlineAccountActivated(citizen, onlineAccountActivated);
		}
		if (isLocal != null) {
			citizenService.editCitizenIsLocal(citizen, isLocal);
		}	
		if (balance != null) {
			citizenService.editCitizenBalance(citizen, balance);
		}
		return convertToDto(citizen);
	}
}
