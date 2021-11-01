package ca.mcgill.ecse321.library.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.library.dao.*;
import ca.mcgill.ecse321.library.models.*;

@Service
public class CitizenService {
	@Autowired
	private CitizenRepository citizenRepository;
	
	@Transactional
	public Citizen creatCitizen(String fullname,String userName, String password,
			String address, Boolean onlineAccountActivated, Boolean isLocal, Double balance) {
		Citizen aCitizen;
		
		if(fullname == null || fullname.equals("")) {
			throw new IllegalArgumentException("Fullname can't be empty");
		}
		
		if(userName == null || userName.equals("")) {
			throw new IllegalArgumentException("Username can't be empty");
		}
		
		if(password == null || password.equals("")) {
			throw new IllegalArgumentException("password can't be empty");
		}
		
		if(address == null || address.equals("")) {
			throw new IllegalArgumentException("Address can't be empty");
		}
		
		if(onlineAccountActivated == null) {
			throw new IllegalArgumentException("onlineAccountActivated can't be empty");
		}
		
		if(isLocal == null) {
			throw new IllegalArgumentException("isLocal can't be empty");
		}
		
		if(balance == null) {
			throw new IllegalArgumentException("Balance can't be empty");
		}
		
		aCitizen = new Citizen();
		aCitizen.setFullName(fullname);
		aCitizen.setBalance(balance);
		aCitizen.setAddress(address);
		aCitizen.setIsLocal(isLocal);
		aCitizen.setOnlineAccountActivated(onlineAccountActivated);
		aCitizen.setPassword(password);
		aCitizen.setUsername(userName);
		aCitizen.getCardID();
		return aCitizen;
	}
	
	@Transactional
	public Citizen getCitizenByUsernameAndPassword(String userName, String password) {
		return citizenRepository.findCitizenByUsernameAndPassword(userName, password);
	}
	
	@Transactional
	public List<Citizen> getAllCitizen(){
		return (List<Citizen>) citizenRepository.findAll();
	}
	
	@Transactional
	public Citizen deletCitizen(Citizen citizen) {
		citizenRepository.delete(citizen);
		citizen  = null;
		return citizen;
	}
	
	@Transactional
	public Citizen getCitizenByID(Long ID) {
		Citizen aCitizen = citizenRepository.findCitizenByCardID(ID);
		return aCitizen;
	}
	
	@Transactional
	public Citizen editCitizenFullname(Citizen aCitizen, String fullName) {
		if(fullName == null || fullName.equals("") || fullName.equals("undefined")) {
			throw new IllegalArgumentException("Fullname can't be empty");
		}
		aCitizen.setFullName(fullName);
		return aCitizen;
	}
	
	@Transactional
	public Citizen editCitizenUsername(Citizen aCitizen, String username) {
		if(username == null || username.equals("") || username.equals("undefined")) {
			throw new IllegalArgumentException("username can't be empty");
		}
		aCitizen.setUsername(username);
		return aCitizen;
	}
	
	@Transactional
	public Citizen editCitizenPassword(Citizen aCitizen, String password) {
		if(password == null || password.equals("") || password.equals("undefined")) {
			throw new IllegalArgumentException("password can't be empty");
		}
		aCitizen.setPassword(password);
		return aCitizen;
	}
	
	@Transactional
	public Citizen editCitizenAdress(Citizen aCitizen, String address) {
		if(address == null || address.equals("") || address.equals("undefined")) {
			throw new IllegalArgumentException("address can't be empty");
		}
		aCitizen.setAddress(address);
		return aCitizen;
	}
	
	@Transactional
	public Citizen editCitizenOnlineAccountActivated(Citizen aCitizen, Boolean isActivated) {
		if(isActivated == null) {
			throw new IllegalArgumentException("status of online account can't be empty");
		}
		aCitizen.setOnlineAccountActivated(isActivated);
		return aCitizen;
	}
	
	@Transactional
	public Citizen editisLocal(Citizen aCitizen, Boolean isLocal) {
		if(isLocal == null) {
			throw new IllegalArgumentException("status of local can't be empty");
		}
		aCitizen.setIsLocal(isLocal);
		return aCitizen;
	}
	
	@Transactional
	public Citizen editBalance(Citizen aCitizen, Double balance) {
		if(balance == null) {
			throw new IllegalArgumentException("balance can't be empty");
		}
		aCitizen.setBalance(balance);
		return aCitizen;
	}
	
	
}
