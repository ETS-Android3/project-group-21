package ca.mcgill.ecse321.library.service;


import java.util.List;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.library.dao.*;
import ca.mcgill.ecse321.library.models.*;

@Service
public class HeadLibrarianService {
	@Autowired
	private ca.mcgill.ecse321.library.dao.HeadLibrarianRepository headLibrarianRepository;
	
	@Transactional
	public HeadLibrarian createHeadlibrarian (String fullname, String userName, String password, String address, Long cardID) {
		if (fullname == null || fullname.equals("")) {
			throw new IllegalArgumentException ("Fullname can't be empty");
		}
		
		if (userName == null || userName.equals("")) {
			throw new IllegalArgumentException ("Username can't be empty");
		}
		
		if (password == null || password.equals("")) {
			throw new IllegalArgumentException ("Password can't be empty");
		}
		
		if (address == null || address.equals("")) {
			throw new IllegalArgumentException ("Address can't be empty");
		}
		
		
		HeadLibrarian aHeadLibrarian = new HeadLibrarian();
		aHeadLibrarian.setFullName(fullname);
		aHeadLibrarian.setUsername(userName);
		aHeadLibrarian.setPassword(password);
		aHeadLibrarian.setAddress(address);
		aHeadLibrarian.setCardID(cardID);
		aHeadLibrarian.setOnlineAccountActivated(true);//by default librarians' accounts are activated
		
		headLibrarianRepository.save(aHeadLibrarian);
		return aHeadLibrarian;
	}
	
	@Transactional
	public HeadLibrarian getHeadLibrarianByID(Long ID) {
		return headLibrarianRepository.findHeadLibrarianByCardID(ID);
	}
	
	//there is only 1 headlibrarian, do not have to implement getallheadlibrarian

	@Transactional
	public HeadLibrarian deleteHeadLibrarian(HeadLibrarian hl) {

		headLibrarianRepository.delete(hl);
		hl = null;
		return hl;
	}
	
	@Transactional
	public HeadLibrarian editHeadLibrarianFullName(HeadLibrarian hl, String newname) {
		if(newname == null || newname.equals("")) {
			throw new IllegalArgumentException("fullname can't be empty");
		}
		headLibrarianRepository.delete(hl);
		hl.setFullName(newname);
		headLibrarianRepository.save(hl);
		return hl;
	}
	@Transactional
	public HeadLibrarian editHeadLibrarianUserName(HeadLibrarian hl, String newname) {
		if(newname == null || newname.equals("")) {
			throw new IllegalArgumentException("username can't be empty");
		}
		headLibrarianRepository.delete(hl);
		hl.setUsername(newname);
		headLibrarianRepository.save(hl);
		return hl;
	}
	
	@Transactional
	public HeadLibrarian editHeadLibrarianPassword(HeadLibrarian hl, String newpassword) {
		if(newpassword == null || newpassword.equals("")) {
			throw new IllegalArgumentException("password can't be empty");
		}
		headLibrarianRepository.delete(hl);
		hl.setPassword(newpassword);
		headLibrarianRepository.save(hl);
		return hl;
	}
	
	@Transactional
	public HeadLibrarian editHeadLibrarianAddress(HeadLibrarian hl, String newaddress) {
		if(newaddress == null || newaddress.equals("")) {
			throw new IllegalArgumentException("address can't be empty");
		}
		headLibrarianRepository.delete(hl);
		hl.setAddress(newaddress);
		headLibrarianRepository.save(hl);
		return hl;
	}
}
