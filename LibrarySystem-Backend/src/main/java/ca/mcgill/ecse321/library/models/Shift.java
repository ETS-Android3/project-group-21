/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.library.models;
import java.sql.Time;
import java.util.*;

import javax.persistence.*;

@Entity
@Table(name = "shift")
public class Shift
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum DayOfWeek { Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Shift Attributes
  private Time startTime;
  private Time endTime;
  private DayOfWeek day;
  private int shiftCode;

  //Shift Associations
  private HeadLibrarian headLibrarian;
  private List<Librarian> librarians;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Shift(){
  }

  //------------------------
  // INTERFACE
  //------------------------

  public void setStartTime(Time aStartTime)
  {
	  this.startTime=aStartTime;
  }

  public void setEndTime(Time aEndTime)
  {
	  this.endTime=aEndTime;
  }

  public void setDay(DayOfWeek aDay)
  {
	  this.day=aDay;
  }

  public void setShiftCode(int aShiftCode)
  {
	  this.shiftCode=aShiftCode;
  }

  /**
   * Essentailly the same as the OpeningHour Class
   */
  public Time getStartTime()
  {
    return this.startTime;
  }

  public Time getEndTime()
  {
    return this.endTime;
  }

  public DayOfWeek getDay()
  {
    return this.day;
  }
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public int getShiftCode()
  {
    return this.shiftCode;
  }
 
  @ManyToOne
  public HeadLibrarian getHeadLibrarian()
  {
    return this.headLibrarian;
  }

  @ManyToMany
  public List<Librarian> getLibrarians()
  {
    return this.librarians;
  }
 
  public void setHeadLibrarian(HeadLibrarian aHeadLibrarian)
  {
	  this.headLibrarian=aHeadLibrarian;

  }

  public void setLibrarians (List<Librarian> alibrarians) {
	  this.librarians=alibrarians;
  }

  
}