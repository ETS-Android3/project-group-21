/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.library.models;
import java.util.*;


import javax.persistence.*;

@Entity
//@Table(name = "\"applicationUser\"")
public abstract class ApplicationUser
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ApplicationUser Attributes
  private String fullname;
  private Long cardID;
  private String address;
  private String username;
  private String password;
  private boolean onlineAccountActivated;

  //------------------------
  // CONSTRUCTOR
  //------------------------
  
  public ApplicationUser() {
	  
  }

  //------------------------
  // INTERFACE
  //------------------------

  public void setFullName(String aFullName)
  {
    this.fullname = aFullName;
  }
  
  public void setCardID(Long aCardID)
  {
    this.cardID = aCardID;
  }

  public void setAddress(String aAddress)
  {
    this.address = aAddress;
  }

  public void setUsername(String aUsername)
  {
    this.username = aUsername;
  }

  public void setPassword(String aPassword)
  {
    this.password = aPassword;
  }

  public void setOnlineAccountActivated(boolean aOnlineAccountActivated)
  {
    this.onlineAccountActivated = aOnlineAccountActivated;
  }

  public String getFullName()
  {
    return this.fullname;
  }

  @Id
 // @GeneratedValue(strategy = GenerationType.AUTO)  //cannot fix the problem for not
  public Long getCardID()
  {
    return this.cardID;
  }

  public String getAddress()
  {
    return this.address;
  }

  public String getUsername()
  {
    return this.username;
  }
  
//  /* Code from template attribute_GetUnique */
//  public static ApplicationUser getWithUsername(String aUsername)
//  {
//    return usersByUsername.get(aUsername);
//  }
//  /* Code from template attribute_HasUnique */
//  public static boolean hasWithUsername(String aUsername)
//  {
//    return getWithUsername(aUsername) != null;
//  }

  public String getPassword()
  {
    return this.password;
  }

  public boolean getOnlineAccountActivated()
  {
    return this.onlineAccountActivated;
  }

//  @OneToMany
//  public Reservation getReservation(int index)
//  {
//    Reservation aReservation = reservation.get(index);
//    return aReservation;
//  }
  
//  @OneToMany
//  public List<Reservation> getReservation()
//  {
//	  return this.reservation;
//  }
//
//  public void setReservation(List<Reservation> Reservations)
//  {
//	  this.reservation=Reservations;
//  }
  
}