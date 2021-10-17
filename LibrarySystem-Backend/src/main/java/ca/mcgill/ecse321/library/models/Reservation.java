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
  private int reservationID;

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

  public void setReservationID(int aReservationID)
  {
	  this.reservationID = aReservationID;
  }
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public int getReservationID()
  {
    return reservationID;
  }
  
//  /* Code from template attribute_GetUnique */
//  public static Reservation getWithReservationID(int aReservationID)
//  {
//    return reservationsByReservationID.get(aReservationID);
//  }
//  /* Code from template attribute_HasUnique */
//  public static boolean hasWithReservationID(int aReservationID)
//  {
//    return getWithReservationID(aReservationID) != null;
//  }
  

  @ManyToOne//(optional=false)  // Multiple(5) Reservation is associated with one User; A Reservation cannot exist without User
  public User getUser()
  {
    return user;
  }

  @OneToOne(optional=false) // One Reservation is associated with one LibraryItem; A Reservation cannot exist without LibraryItem  
  @JoinColumn(name = "libraryitem")
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