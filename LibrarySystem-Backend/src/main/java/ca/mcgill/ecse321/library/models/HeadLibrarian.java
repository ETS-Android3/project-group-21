package ca.mcgill.ecse321.library.models;
import java.util.*;
import java.sql.Time;

// line 21 "model.ump"
// line 58 "model.ump"
public class HeadLibrarian extends User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //HeadLibrarian Associations
  private List<OpeningHour> openingHours;
  private List<Shift> shifts;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public HeadLibrarian(String aFullName, int aCardId, String aAdress, String aUsername, String aPassword, boolean aOnlineAccountActivated)
  {
    super(aFullName, aCardId, aAdress, aUsername, aPassword, aOnlineAccountActivated);
    openingHours = new ArrayList<OpeningHour>();
    shifts = new ArrayList<Shift>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public OpeningHour getOpeningHour(int index)
  {
    OpeningHour aOpeningHour = openingHours.get(index);
    return aOpeningHour;
  }

  public List<OpeningHour> getOpeningHours()
  {
    List<OpeningHour> newOpeningHours = Collections.unmodifiableList(openingHours);
    return newOpeningHours;
  }

  public int numberOfOpeningHours()
  {
    int number = openingHours.size();
    return number;
  }

  public boolean hasOpeningHours()
  {
    boolean has = openingHours.size() > 0;
    return has;
  }

  public int indexOfOpeningHour(OpeningHour aOpeningHour)
  {
    int index = openingHours.indexOf(aOpeningHour);
    return index;
  }
  /* Code from template association_GetMany */
  public Shift getShift(int index)
  {
    Shift aShift = shifts.get(index);
    return aShift;
  }

  public List<Shift> getShifts()
  {
    List<Shift> newShifts = Collections.unmodifiableList(shifts);
    return newShifts;
  }

  public int numberOfShifts()
  {
    int number = shifts.size();
    return number;
  }

  public boolean hasShifts()
  {
    boolean has = shifts.size() > 0;
    return has;
  }

  public int indexOfShift(Shift aShift)
  {
    int index = shifts.indexOf(aShift);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfOpeningHours()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public OpeningHour addOpeningHour(Time aStartTime, Time aEndTime, DayofWeek aDay)
  {
    return new OpeningHour(aStartTime, aEndTime, aDay, this);
  }

  public boolean addOpeningHour(OpeningHour aOpeningHour)
  {
    boolean wasAdded = false;
    if (openingHours.contains(aOpeningHour)) { return false; }
    HeadLibrarian existingHeadLibrarian = aOpeningHour.getHeadLibrarian();
    boolean isNewHeadLibrarian = existingHeadLibrarian != null && !this.equals(existingHeadLibrarian);
    if (isNewHeadLibrarian)
    {
      aOpeningHour.setHeadLibrarian(this);
    }
    else
    {
      openingHours.add(aOpeningHour);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeOpeningHour(OpeningHour aOpeningHour)
  {
    boolean wasRemoved = false;
    //Unable to remove aOpeningHour, as it must always have a headLibrarian
    if (!this.equals(aOpeningHour.getHeadLibrarian()))
    {
      openingHours.remove(aOpeningHour);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addOpeningHourAt(OpeningHour aOpeningHour, int index)
  {  
    boolean wasAdded = false;
    if(addOpeningHour(aOpeningHour))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOpeningHours()) { index = numberOfOpeningHours() - 1; }
      openingHours.remove(aOpeningHour);
      openingHours.add(index, aOpeningHour);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveOpeningHourAt(OpeningHour aOpeningHour, int index)
  {
    boolean wasAdded = false;
    if(openingHours.contains(aOpeningHour))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOpeningHours()) { index = numberOfOpeningHours() - 1; }
      openingHours.remove(aOpeningHour);
      openingHours.add(index, aOpeningHour);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addOpeningHourAt(aOpeningHour, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfShifts()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Shift addShift()
  {
    return new Shift(this);
  }

  public boolean addShift(Shift aShift)
  {
    boolean wasAdded = false;
    if (shifts.contains(aShift)) { return false; }
    HeadLibrarian existingHeadLibrarian = aShift.getHeadLibrarian();
    boolean isNewHeadLibrarian = existingHeadLibrarian != null && !this.equals(existingHeadLibrarian);
    if (isNewHeadLibrarian)
    {
      aShift.setHeadLibrarian(this);
    }
    else
    {
      shifts.add(aShift);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeShift(Shift aShift)
  {
    boolean wasRemoved = false;
    //Unable to remove aShift, as it must always have a headLibrarian
    if (!this.equals(aShift.getHeadLibrarian()))
    {
      shifts.remove(aShift);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addShiftAt(Shift aShift, int index)
  {  
    boolean wasAdded = false;
    if(addShift(aShift))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfShifts()) { index = numberOfShifts() - 1; }
      shifts.remove(aShift);
      shifts.add(index, aShift);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveShiftAt(Shift aShift, int index)
  {
    boolean wasAdded = false;
    if(shifts.contains(aShift))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfShifts()) { index = numberOfShifts() - 1; }
      shifts.remove(aShift);
      shifts.add(index, aShift);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addShiftAt(aShift, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=openingHours.size(); i > 0; i--)
    {
      OpeningHour aOpeningHour = openingHours.get(i - 1);
      aOpeningHour.delete();
    }
    for(int i=shifts.size(); i > 0; i--)
    {
      Shift aShift = shifts.get(i - 1);
      aShift.delete();
    }
    super.delete();
  }

}
