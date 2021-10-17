/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.library.models;
import java.util.*;
import java.sql.Time;

import javax.persistence.*;


@Entity
@Table(name = "headlibrarian")
public class HeadLibrarian extends User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //HeadLibrarian Associations
  private List<OpeningHour> openinghour;
  private List<Shift> shift;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public HeadLibrarian() {
	  
  }

  //------------------------
  // INTERFACE
  //------------------------

  public void setShift(List<Shift> aShift)
  {
    this.shift = aShift;
  }

  @OneToMany
  public List<Shift> getShift()
  {
    return this.shift;
  }

  @OneToMany
  public List<OpeningHour> getOpeninghour()
  {
    return this.openinghour;
  }

  public void setOpeninghour(List<OpeningHour> aOpeninghour)
  {
    this.openinghour = aOpeninghour;
  }

 }