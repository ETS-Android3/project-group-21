/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

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
  private User user;
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
  

  
  @ManyToOne//(optional=false)  // Multiple(5) Reservation is associated with one User; A Reservation cannot exist without User
//  @JoinColumn(name = "user_cardid", referencedColumnName = "cardid")
  public User getUser()
  {
    return user;
  }

  @OneToOne//(optional=false) // One Reservation is associated with one LibraryItem; A Reservation cannot exist without LibraryItem  
//  @JoinColumn(name = "library_item_barcode")//, referencedColumnName = "barcode")
  public LibraryItem getLibraryItem()
  {
    return libraryItem;
  }

  public void setUser(User aUser)
  {
    this.user = aUser;
  }

  public void setLibraryItem(LibraryItem aNewLibraryItem)
  {
    this.libraryItem = aNewLibraryItem;
  }


}