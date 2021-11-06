package ca.mcgill.ecse321.library.dto;

import ca.mcgill.ecse321.library.models.Shift;

public class HeadLibrarianDto extends ApplicationUserDto{
	
	public HeadLibrarianDto(Long cardID) {
		this(cardID,"head librarian","N/A","bookworm","password");
	}

	public HeadLibrarianDto(Long cardID,String name,String address,String username,String password) {
		super(cardID, name, address, username, password);
	}
	
}
