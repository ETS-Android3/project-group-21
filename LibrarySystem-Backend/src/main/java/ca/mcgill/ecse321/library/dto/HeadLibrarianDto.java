package ca.mcgill.ecse321.library.dto;

import ca.mcgill.ecse321.library.models.Shift;

public class HeadLibrarianDto extends ApplicationUserDto{
	
	public HeadLibrarianDto(Long ID) {
		this(ID,"head librarian","N/A","bookworm","password");
	}

	public HeadLibrarianDto(Long ID,String name,String address,String username,String password) {
		super(ID, name, address, username, password);
	}
	
}
