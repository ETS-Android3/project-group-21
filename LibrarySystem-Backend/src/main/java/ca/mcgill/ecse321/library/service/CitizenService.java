package ca.mcgill.ecse321.library.service;

import java.util.ArrayList;
import java.util.List;



import org.springframework.transaction.annotation.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.library.dao.*;
import ca.mcgill.ecse321.library.models.*;

@Service
public class CitizenService {
	@Autowired
	private CitizenRepository citizenRepository;
	
	@Transactional
	public Citizen creatCitizen(String fullname,String userName, String password,
			String address, Boolean onlineAccountActivated, Boolean isLocal, Double balance, Long cardID) {
		Citizen aCitizen = null;
		
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
		
		if(cardID == null) {
			throw new IllegalArgumentException("CardID can't be empty");
		}
		aCitizen = new Citizen();
		aCitizen.setFullName(fullname);
		aCitizen.setBalance(balance);
		aCitizen.setAddress(address);
		aCitizen.setIsLocal(isLocal);
		aCitizen.setOnlineAccountActivated(onlineAccountActivated);
		aCitizen.setPassword(password);
		aCitizen.setUsername(userName);
		aCitizen.setCardID(cardID);
	
		citizenRepository.save(aCitizen);

		
		//System.out.println("test in service: "+ aCitizen.getCardID());
		return aCitizen;
	}
	
//	@Transactional
//	public Citizen getCitizenByUsernameAndPassword(String userName, String password) {
//		return citizenRepository.findCitizenByUsernameAndPassword(userName, password);
//	}
	
	@Transactional
	public List<Citizen> getAllCitizen(){
		//System.out.println("newcitizen:"+citizenRepository.findAll());
		return (List<Citizen>) citizenRepository.findAll();
	}
	
	@Transactional
	public Citizen deleteCitizen(Citizen citizen) {
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
		citizenRepository.save(aCitizen);
		return aCitizen;
	}
	
	@Transactional
	public Citizen editCitizenUsername(Citizen aCitizen, String username) {
		if(username == null || username.equals("") || username.equals("undefined")) {
			throw new IllegalArgumentException("username can't be empty");
		}
		aCitizen.setUsername(username);
		citizenRepository.save(aCitizen);
		return aCitizen;
	}
	
	@Transactional
	public Citizen editCitizenPassword(Citizen aCitizen, String password) {
		if(password == null || password.equals("") || password.equals("undefined")) {
			throw new IllegalArgumentException("password can't be empty");
		}
		aCitizen.setPassword(password);
		citizenRepository.save(aCitizen);
		return aCitizen;
	}
	
	@Transactional
	public Citizen editCitizenAddress(Citizen aCitizen, String address) {
		if(address == null || address.equals("") || address.equals("undefined")) {
			throw new IllegalArgumentException("address can't be empty");
		}
		aCitizen.setAddress(address);
		citizenRepository.save(aCitizen);
		return aCitizen;
	}
	
	@Transactional
	public Citizen editCitizenOnlineAccountActivated(Citizen aCitizen, Boolean isActivated) {
		if(isActivated == null) {
			throw new IllegalArgumentException("status of online account can't be empty");
		}
		aCitizen.setOnlineAccountActivated(isActivated);
		citizenRepository.save(aCitizen);
		return aCitizen;
	}
	
	@Transactional
	public Citizen editCitizenIsLocal(Citizen aCitizen, Boolean isLocal) {
		if(isLocal == null) {
			throw new IllegalArgumentException("status of local can't be empty");
		}
		aCitizen.setIsLocal(isLocal);
		citizenRepository.save(aCitizen);
		return aCitizen;
	}
	
	@Transactional
	public Citizen editCitizenBalance(Citizen aCitizen, Double balance) {
		if(balance == null) {
			throw new IllegalArgumentException("balance can't be empty");
		}
		aCitizen.setBalance(balance);
		citizenRepository.save(aCitizen);
		return aCitizen;
	}
	
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
}
