package ca.mcgill.ecse321.library.models;
import java.util.*;

import javax.persistence.*;

@Entity
@Table(name = "reservation")
public class Reservation
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Reservation Attributes
  private Long reservationID;

  //Reservation Associations
  private ApplicationUser ApplicationUser;
  private LibraryItem libraryItem;

  //------------------------
  // CONSTRUCTOR
  //------------------------
  
  public Reservation() {
	  
  }

  //------------------------
  // INTERFACE
  //------------------------

  public void setReservationID(Long aReservationID)
  {
	  this.reservationID = aReservationID;
  }
  
  //@GeneratedValue(strategy = GenerationType.AUTO) //cannot fix the problem for now
  @Id
  public Long getReservationID()
  {
    return reservationID;
  }
  

  
  @ManyToOne//(optional=false)  // Multiple(5) Reservation is associated with one ApplicationApplicationUser; A Reservation cannot exist without ApplicationApplicationUser
//  @JoinColumn(name = "ApplicationUser_cardid", referencedColumnName = "cardid")
  public ApplicationUser getApplicationUser()
  {
    return ApplicationUser;
  }

  @OneToOne//(optional=false) // One Reservation is associated with one LibraryItem; A Reservation cannot exist without LibraryItem  
//  @JoinColumn(name = "library_item_barcode")//, referencedColumnName = "barcode")
  public LibraryItem getLibraryItem()
  {
    return libraryItem;
  }

  public void setApplicationUser(ApplicationUser aApplicationUser)
  {
    this.ApplicationUser = aApplicationUser;
  }

  public void setLibraryItem(LibraryItem aNewLibraryItem)
  {
    this.libraryItem = aNewLibraryItem;
  }


}