package ca.mcgill.ecse321.library.dto;

import ca.mcgill.ecse321.library.models.Shift;


	
public class ApplicationUserDto {
	private String fullName;
	private String address;
	private String username;
	private String password;
	private Long ID;

	
	public ApplicationUserDto() {

	}
	
	public ApplicationUserDto(Long ID) {
		//initial default value of info other then key info
		this(ID,"username","N/A","bookworm","password");
	}
	
	public ApplicationUserDto(Long ID,String name,String address,String username,String password) {
		this.ID = ID;
		this.fullName = name;
		this.address = address;
		this.username = username;
		this.password = password;

	}
	
	public String getName() {
		return this.fullName;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public String getUserName() {
		return this.username;
	}
	
	public String password() {
		return this.password;
	}
	
	
	public Long getID() {
		return this.ID;
	}
	
}

