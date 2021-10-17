/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.library.models;
import java.util.*;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.sql.Time;

@Entity
@Table(name = "library")
public class Library
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Library Attributes
  private String name;

  //Library Associations
  private HeadLibrarian headlibrarian;
  private List<Librarian> librarian;
  private List<Citizen> citizen;
  private List<Reservation> reservation;
  private List<LibraryItem> libraryitem;
  private List<OpeningHour> openinghour;


  //------------------------
  // CONSTRUCTOR
  //------------------------
  
  public Library() {
	  
  }

  //------------------------
  // INTERFACE
  //------------------------

  public void setName(String aName)
  {
    this.name = aName;
  }

  @Id
  public String getName()
  {
    return this.name;
  }
  
  @OneToOne //(cascade = {CascadeType.ALL})
  public HeadLibrarian getHeadlibrarian()
  {
    return this.headlibrarian;
  }
  
  // to match getHeadlibrarian
  public void setHeadlibrarian(HeadLibrarian headlibrarian) {
	  this.headlibrarian = headlibrarian;
  }
  
  @OneToMany(cascade = {CascadeType.ALL})
  public List<Librarian> getLibrarian()
  {
    return this.librarian;
  }

  @OneToMany(cascade = {CascadeType.ALL})
  public List<Citizen> getCitizen()
  {
    return this.citizen;
  }

  @OneToMany(cascade = {CascadeType.ALL})
  public List<Reservation> getReservation()
  {
    return this.reservation;
  }

  @OneToMany(cascade = {CascadeType.ALL})
  public List<LibraryItem> getLibraryitem()
  {
    return this.libraryitem;
  }

  @OneToMany(cascade = {CascadeType.ALL})
  public List<OpeningHour> getOpeninghour()
  {
    return this.openinghour;
  }

  public void setLibrarian(List<Librarian> aLibrarian)
  {
    this.librarian = aLibrarian;
  }

  public void setCitizen(List<Citizen> aCitizen)
  {
	 this.citizen=aCitizen;
  }


  public void setReservation(List<Reservation> aReservation)
  {
	  this.reservation=aReservation;
  }


  public void setLibraryitem(List<LibraryItem> aLibraryitem)
  {
	  this.libraryitem=aLibraryitem;
  }

  public void setOpeninghour(List<OpeningHour> aOpeninghour)
  {
	  this.openinghour=aOpeninghour;
  }

}