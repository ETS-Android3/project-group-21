package ca.mcgill.ecse321.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse321.library.dao.*;
import ca.mcgill.ecse321.library.models.*;

@Service
public class LibrarianService {
	@Autowired
	ca.mcgill.ecse321.library.dao.LibrarianRepository librarianRepository;

	/*
	 * @Author: Yujin li
	 * create a Librarian
	 * @param fullname
	 * @param username
	 * @param password
	 * @param address
	 * @param cardID
	 */
	
	@Transactional
	public Librarian createLibrarian (String fullname, String userName, String password, String address, Long cardID) {
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
		
		
		Librarian aLibrarian = new Librarian();
		aLibrarian.setFullName(fullname);
		aLibrarian.setUsername(userName);
		aLibrarian.setPassword(password);
		aLibrarian.setAddress(address);
		aLibrarian.setCardID(cardID);
		aLibrarian.setOnlineAccountActivated(true);//by default librarians' accounts are activated
		
		librarianRepository.save(aLibrarian);
		return aLibrarian;
	}

	/*
	 * @Author: Yujin li
	 * get Librarian with a provided ID
	 * @param ID
	 */
	
	@Transactional
	public Librarian getLibrarianByID(Long ID) {
		return librarianRepository.findLibrarianByCardID(ID);
	}

	/*
	 * @Author: Yujin li
	 * get all Librarian
	 */
	@Transactional
	public List<Librarian> getAllLibrarian(){
		return (List<Librarian>) librarianRepository.findAll();
	}

	/*
	 * @Author: Yujin li
	 * delete a Librarian
	 * @param aLibrarian
	 */
	@Transactional
	public Librarian deleteLibrarian(Librarian hl) {
		librarianRepository.delete(hl);
		hl = null;
		return hl;
	}

	/*
	 * @Author: Yujin li
	 * edit Librarian's full name
	 * @param aLibrarian
	 * @param fullname
	 */
	
	@Transactional
	public Librarian editLibrarianFullName(Librarian hl, String newname) {
		if(newname == null || newname.equals("")) {
			throw new IllegalArgumentException("fullname can't be empty");
		}
		librarianRepository.delete(hl);
		hl.setFullName(newname);
		librarianRepository.save(hl);
		return hl;
	}

	/*
	 * @Author: Yujin li
	 * edit Librarian's username
	 * @param aLibrarian
	 * @param newname
	 */
	
	@Transactional
	public Librarian editLibrarianUserName(Librarian hl, String newname) {
		if(newname == null || newname.equals("")) {
			throw new IllegalArgumentException("username can't be empty");
		}
		librarianRepository.delete(hl);
		hl.setUsername(newname);
		librarianRepository.save(hl);
		return hl;
	}


	/*
	 * @Author: Yujin li
	 * edit Librarian's password
	 * @param aLibrarian
	 * @param newpassword
	 */
	@Transactional
	public Librarian editLibrarianPassword(Librarian hl, String newpassword) {
		if(newpassword == null || newpassword.equals("")) {
			throw new IllegalArgumentException("password can't be empty");
		}
		librarianRepository.delete(hl);
		hl.setPassword(newpassword);
		librarianRepository.save(hl);
		return hl;
	}

	/*
	 * @Author: Yujin li
	 * edit Librarian's address
	 * @param aLibrarian
	 * @param newaddress
	 */
	@Transactional
	public Librarian editLibrarianAddress(Librarian hl, String newaddress) {
		if(newaddress == null || newaddress.equals("")) {
			throw new IllegalArgumentException("address can't be empty");
		}
		librarianRepository.delete(hl);
		hl.setAddress(newaddress);
		librarianRepository.save(hl);
		return hl;
	}
}
