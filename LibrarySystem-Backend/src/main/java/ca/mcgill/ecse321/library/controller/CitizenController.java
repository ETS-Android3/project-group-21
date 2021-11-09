package ca.mcgill.ecse321.library.controller;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse321.library.dao.CitizenRepository;
import ca.mcgill.ecse321.library.dto.CitizenDto;
import ca.mcgill.ecse321.library.models.Citizen;
import ca.mcgill.ecse321.library.service.CitizenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

    @GetMapping(value = {"/citizens/{username}/{password}","/citizens/{username}/{password}/"})
    public CitizenDto getCitizenByUsernameAndPassword(@PathVariable String username, @PathVariable String password){
        if(username==null || password==null){
            throw new IllegalArgumentException("no citizen with such username and password");
        }
        return convertToDto(citizenService.getCitizenByUsernameAndPassword(username,password));
    }

    @PostMapping(value = {"/citizens/{cardID}", "/citizens/{cardID}/"})
    public CitizenDto createCitizen(@PathVariable Long cardID, @RequestParam String username, @RequestParam String password,@RequestParam String fullname,
    		@RequestParam String address, @RequestParam Boolean onlineAccountActivated,
            @RequestParam Boolean isLocal,@RequestParam Double balance){
        Citizen aCitizen = citizenService.creatCitizen(fullname,username,password,address,onlineAccountActivated,
                isLocal,balance, cardID);
        CitizenDto citizenDto = convertToDto(aCitizen);
        return citizenDto;
    }

    
    
    
//    @PutMapping("/citizens/{cardID}")
//    public ResponseEntity<?> saveCitizen(@RequestBody Citizen citizen,
//      @PathVariable("cardID") String cardID) {
//        CitizenRepository citizenRepository;
//        Long id = Long.parseLong(cardID);
//		citizenRepository.save(citizen);
//        return ResponseEntity.ok("resource saved");
//    }
    
    @PatchMapping(value = {"/citizens/{fullName}","/citizens/{fullName}/"})
    public ResponseEntity<?> editCitizenFullName(@PathVariable("cardID") Long cardID, @RequestParam String fullName){
        Citizen citizen = null;
        try{
            citizen = citizenService.getCitizenByID(cardID);
            citizen = citizenService.editCitizenFullname(citizen,fullName);
        } catch(IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(convertToDto(citizen),HttpStatus.CREATED);
    }
    @PatchMapping(value = {"/citizens/{userName}","/citizens/{userName}/"})
    public ResponseEntity<?> editCitizenUserName(@PathVariable("cardID") Long cardID, @RequestParam String userName){
        Citizen citizen = null;
        try{
            citizen = citizenService.getCitizenByID(cardID);
            citizen = citizenService.editCitizenUsername(citizen,userName);
        } catch(IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(convertToDto(citizen),HttpStatus.CREATED);
    }

    
    @PatchMapping(value = {"/citizens/{password}","/citizens/{password}/"})
    public ResponseEntity<?> editCitizenPassword(@PathVariable("cardID") Long cardID, @RequestParam String password){
        Citizen citizen = null;
        try{
            citizen = citizenService.getCitizenByID(cardID);
            citizen = citizenService.editCitizenPassword(citizen,password);
        } catch(IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(convertToDto(citizen),HttpStatus.CREATED);
    }

    @PatchMapping(value = {"/citizens/{address}","/citizens/{address}/"})
    public ResponseEntity<?> editCitizenAddress(@PathVariable("cardID") Long cardID, @RequestParam String address){
        Citizen citizen = null;
        try{
            citizen = citizenService.getCitizenByID(cardID);
            citizen = citizenService.editCitizenAddress(citizen,address);
        } catch(IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(convertToDto(citizen),HttpStatus.CREATED);
    }

    @PatchMapping(value = {"/citizens/{onlineAccountActivated}","/citizens/{onlineAccountActivated}/"})
    public ResponseEntity<?> editCitizenOnlineAccountActivated(@PathVariable("cardID") Long cardID, @RequestParam Boolean onlineAccountActivated){
        Citizen citizen = null;
        try{
            citizen = citizenService.getCitizenByID(cardID);
            citizen = citizenService.editCitizenOnlineAccountActivated(citizen,onlineAccountActivated);
        } catch(IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(convertToDto(citizen),HttpStatus.CREATED);
    }

    @PatchMapping(value = {"/citizens/{isLocal}","/citizens/{isLocal}/"})
    public ResponseEntity<?> editCitizenisLocal(@PathVariable("cardID") Long cardID, @RequestParam Boolean isLocal){
        Citizen citizen = null;
        try{
            citizen = citizenService.getCitizenByID(cardID);
            citizen = citizenService.editisLocal(citizen,isLocal);
        } catch(IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(convertToDto(citizen),HttpStatus.CREATED);
    }

    @PatchMapping(value = {"/citizens/{balance}","/citizens/{balance}/"})
    public ResponseEntity<?> editCitizenBalance(@PathVariable("cardID") Long cardID, @RequestParam Double balance){
        Citizen citizen = null;
        try{
            citizen = citizenService.getCitizenByID(cardID);
            citizen = citizenService.editBalance(citizen,balance);
        } catch(IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(convertToDto(citizen),HttpStatus.CREATED);
    }
}
