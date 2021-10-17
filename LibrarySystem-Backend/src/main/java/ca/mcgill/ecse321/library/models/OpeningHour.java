/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.library.models;
import java.sql.Time;

import javax.persistence.*;

@Entity
@Table(name = "openinghour")
public class OpeningHour
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum DayOfWeek { Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //OpeningHour Attributes
  private Time startTime;
  private Time endTime;
  private DayOfWeek day;

  //OpeningHour Associations
  private HeadLibrarian headLibrarian;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public OpeningHour() {
	  
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

  public Time getStartTime()
  {
    return this.startTime;
  }

  public Time getEndTime()
  {
    return this.endTime;
  }
  
  @Id
  public DayOfWeek getDay()
  {
    return this.day;
  }

  @ManyToOne
  public HeadLibrarian getHeadLibrarian()
  {
    return this.headLibrarian;
  }

  public void setHeadLibrarian(HeadLibrarian aHeadLibrarian)
  {
	  this.headLibrarian=aHeadLibrarian;
  }


}