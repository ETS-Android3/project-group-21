package ca.mcgill.ecse321.library.dto;

public class CitizenDto {
	private String fullName;
	private String address;
	private String username;
	private String password;
	private Boolean onlineAccountActivated;
	private Boolean isLocal;
	private Double balance;
	private Long ID;
	
	public CitizenDto() {
		//empty
	}
	public CitizenDto(Long ID) {
		//initial default value of info other then key info
		this(ID,"cizien","N/A","bookworm","password",false,false,0.0);
	}
	
	public CitizenDto(Long ID,String name,String address,String username,String password,
			Boolean onlineAccountActivated,Boolean isLocal,Double balance) {
		this.ID = ID;
		this.fullName = name;
		this.address = address;
		this.username = username;
		this.password = password;
		this.onlineAccountActivated = onlineAccountActivated;
		this.isLocal = isLocal;
		this.balance = balance;
	}
	
	public String getName() {
		return this.fullName;
	}
	
	public String getAdress() {
		return this.address;
	}
	
	public String password() {
		return this.password;
	}
	
	public Boolean getOnlineAccountActivated() {
		return this.onlineAccountActivated;
	}
	
	public Boolean getIsLocal() {
		return this.isLocal;
	}
	
	public String getUserName() {
		return this.username;
	}
	
	public Double getBalance() {
		return this.balance;
	}
	
	public Long getID() {
		return this.ID;
	}
	
}
