package ca.mcgill.ecse321.library.dto;

import ca.mcgill.ecse321.library.models.Shift;


	
public class ApplicationUserDto {
	private String fullName;
	private String address;
	private String username;
	private String password;
	private Long ID;
	private Shift shift;
	
//	public ApplicationUserDto() {
//		//empty
//	}
	
	public ApplicationUserDto(Long ID) {
		//initial default value of info other then key info
		this(ID,"username","N/A","bookworm","password",null);
	}
	
	public ApplicationUserDto(Long ID,String name,String address,String username,String password,
			Shift shift) {
		this.ID = ID;
		this.fullName = name;
		this.address = address;
		this.username = username;
		this.password = password;
		this.shift = shift;
	}
	
	public String getName() {
		return this.fullName;
	}
	
	public String getAdress() {
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
	
	public Shift getShift() {
		return this.shift;
	}
}

