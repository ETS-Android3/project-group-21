package ca.mcgill.ecse321.library.dto;

public class ReservationDto {

	  private Long reservationID;

	  private ApplicationUserDto ApplicationUserDto;
	  private LibraryItemDto libraryItemDto;

	  public ReservationDto() {
		  
	  }
	  
	  public ReservationDto(Long reservationID) {
		  this(reservationID, null, null);
	  }
	  
	  public ReservationDto(Long reservationID, ApplicationUserDto applicationUserDto, LibraryItemDto liDto) {
		  this.reservationID = reservationID;
		  this.libraryItemDto = liDto;
		  this.ApplicationUserDto = ApplicationUserDto;
	  }
	  

	  public Long getReservationID()
	  {
	    return reservationID;
	  }

	  public ApplicationUserDto getApplicationUser()
	  {
	    return ApplicationUserDto;
	  }

	  public void setApplicationUser(ApplicationUserDto applicationUserDto)
	  {
	    this.ApplicationUserDto = applicationUserDto;
	  }
	  
	  public LibraryItemDto getLibraryItem()
	  {
	    return libraryItemDto;
	  }
	  
	  public void setLibraryItem(LibraryItemDto liDto)
	  {
	    this.libraryItemDto = liDto;
	  }

}
