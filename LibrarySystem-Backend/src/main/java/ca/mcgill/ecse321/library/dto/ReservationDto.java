package ca.mcgill.ecse321.library.dto;

public class ReservationDto {
	
	  //------------------------
	  // MEMBER VARIABLES
	  //------------------------

	  //Reservation Attributes
	  private Long reservationID;

	  //Reservation Associations
//	  private UserDto user;
	  private LibraryItemDto libraryItem;

	  //------------------------
	  // CONSTRUCTOR
	  //------------------------
	  
	  public ReservationDto() {
		  
	  }

	  //------------------------
	  // INTERFACE
	  //------------------------
	  
	  public Long getReservationID()
	  {
	    return reservationID;
	  }

//	  public UserDto getUser()
//	  {
//	    return user;
//	  }

	  public LibraryItemDto getLibraryItem()
	  {
	    return libraryItem;
	  }

//	  public void setUser(UserDto aUser)
//	  {
//	    this.user = aUser;
//	  }

	  public void setLibraryItem(LibraryItemDto aNewLibraryItem)
	  {
	    this.libraryItem = aNewLibraryItem;
	  }

}
