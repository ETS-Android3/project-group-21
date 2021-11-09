package ca.mcgill.ecse321.library.dto;

public class CitizenDto extends ApplicationUserDto{

	private Boolean isLocal;
	private Double balance;
	private Boolean onlineAccountActivated;
	
	
	public CitizenDto() {
		
	}
	
	public CitizenDto(Long ID) {
		//initial default value of info other then key info
		this(ID,"cizien","N/A","bookworm","password", false,0.0, false);
	}
	
	public CitizenDto(Long ID,String name,String address,String username,String password,
			Boolean isLocal,Double balance, Boolean onlineAccountActivated) {
//		this.ID = ID;
//		this.fullName = super.;
//		this.address = address;
//		this.username = username;
//		this.password = password;
//		this.onlineAccountActivated = onlineAccountActivated;
		super(ID, name, address, username, password);
		this.isLocal = isLocal;
		this.balance = balance;
		this.onlineAccountActivated = onlineAccountActivated;
	}
	
	
	
	public Boolean getIsLocal() {
		return this.isLocal;
	}
	
	
	public Double getBalance() {
		return this.balance;
	}
	
	public Boolean getOnlineAccountActivated() {
		return this.onlineAccountActivated;
	}
}
