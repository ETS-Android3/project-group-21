package ca.mcgill.ecse321.library.dto;

import ca.mcgill.ecse321.library.models.Shift;

/*
 * @Author: Yujin li
 */
public class LibrarianDto extends ApplicationUserDto{
	
	public LibrarianDto() {
		
	}
	
	public LibrarianDto(Long cardID) {
		this(cardID,"librarian","N/A","bookworm","password");
	}

	public LibrarianDto(Long cardID,String name,String address,String username,String password) {
		super(cardID, name, address, username, password);
	}
}
