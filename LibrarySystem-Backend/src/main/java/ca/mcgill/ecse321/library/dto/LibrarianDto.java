package ca.mcgill.ecse321.library.dto;

import ca.mcgill.ecse321.library.models.Shift;

public class LibrarianDto extends ApplicationUserDto{
	
	public LibrarianDto(Long ID) {
		this(ID,"librarian","N/A","bookworm","password");
	}

	public LibrarianDto(Long ID,String name,String address,String username,String password) {
		super(ID, name, address, username, password);
	}
}
