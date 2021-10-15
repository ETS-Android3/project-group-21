/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4607.2d2b84eb8 modeling language!*/

package ca.mcgill.ecse321.library.models;
import java.util.*;
import java.sql.Time;

// line 41 "../../../../../LibrarySystem.ump"
public class HeadLibrarian extends User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //HeadLibrarian Attributes
  private Shift aShift;

  //HeadLibrarian Associations
  private List<OpeningHour> openinghour;
  private List<Shift> shift;
  private Library library;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public HeadLibrarian(String aFullName, int aCardID, String aAddress, String aUsername, String aPassword, boolean aOnlineAccountActivated, Shift aAShift, Library aLibrary)
  {
    super(aFullName, aCardID, aAddress, aUsername, aPassword, aOnlineAccountActivated);
    aShift = aAShift;
    openinghour = new ArrayList<OpeningHour>();
    shift = new ArrayList<Shift>();
    if (aLibrary == null || aLibrary.getHeadlibrarian() != null)
    {
      throw new RuntimeException("Unable to create HeadLibrarian due to aLibrary. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    library = aLibrary;
  }

  public HeadLibrarian(String aFullName, int aCardID, String aAddress, String aUsername, String aPassword, boolean aOnlineAccountActivated, Shift aAShift)
  {
    super(aFullName, aCardID, aAddress, aUsername, aPassword, aOnlineAccountActivated);
    aShift = aAShift;
    openinghour = new ArrayList<OpeningHour>();
    shift = new ArrayList<Shift>();
    library = new Library(this);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setAShift(Shift aAShift)
  {
    boolean wasSet = false;
    aShift = aAShift;
    wasSet = true;
    return wasSet;
  }

  public Shift getAShift()
  {
    return aShift;
  }
  /* Code from template association_GetMany */
  public OpeningHour getOpeninghour(int index)
  {
    OpeningHour aOpeninghour = openinghour.get(index);
    return aOpeninghour;
  }

  public List<OpeningHour> getOpeninghour()
  {
    List<OpeningHour> newOpeninghour = Collections.unmodifiableList(openinghour);
    return newOpeninghour;
  }

  public int numberOfOpeninghour()
  {
    int number = openinghour.size();
    return number;
  }

  public boolean hasOpeninghour()
  {
    boolean has = openinghour.size() > 0;
    return has;
  }

  public int indexOfOpeninghour(OpeningHour aOpeninghour)
  {
    int index = openinghour.indexOf(aOpeninghour);
    return index;
  }
  /* Code from template association_GetMany */
  public Shift getShift(int index)
  {
    Shift aShift = shift.get(index);
    return aShift;
  }

  public List<Shift> getShift()
  {
    List<Shift> newShift = Collections.unmodifiableList(shift);
    return newShift;
  }

  public int numberOfShift()
  {
    int number = shift.size();
    return number;
  }

  public boolean hasShift()
  {
    boolean has = shift.size() > 0;
    return has;
  }

  public int indexOfShift(Shift aShift)
  {
    int index = shift.indexOf(aShift);
    return index;
  }
  /* Code from template association_GetMany_clear */
  protected void clear_shift()
  {
    shift.clear();
  }
  /* Code from template association_GetOne */
  public Library getLibrary()
  {
    return library;
  }
  /* Code from template association_GetMany_relatedSpecialization */
  public Shift getShift_Shift(int index)
  {
    Shift aShift = (Shift)shift.get(index);
    return aShift;
  }

  /* required for Java 7. */
  @SuppressWarnings("unchecked")
  public List<Shift> getShift_Shift()
  {
    List<? extends Shift> newShift = Collections.unmodifiableList(shift);
    return (List<Shift>)newShift;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfOpeninghour()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public OpeningHour addOpeninghour(Time aStartTime, Time aEndTime, OpeningHour.DayOfWeek aDay, Library aLibrary)
  {
    return new OpeningHour(aStartTime, aEndTime, aDay, aLibrary, this);
  }

  public boolean addOpeninghour(OpeningHour aOpeninghour)
  {
    boolean wasAdded = false;
    if (openinghour.contains(aOpeninghour)) { return false; }
    HeadLibrarian existingHeadLibrarian = aOpeninghour.getHeadLibrarian();
    boolean isNewHeadLibrarian = existingHeadLibrarian != null && !this.equals(existingHeadLibrarian);
    if (isNewHeadLibrarian)
    {
      aOpeninghour.setHeadLibrarian(this);
    }
    else
    {
      openinghour.add(aOpeninghour);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeOpeninghour(OpeningHour aOpeninghour)
  {
    boolean wasRemoved = false;
    //Unable to remove aOpeninghour, as it must always have a headLibrarian
    if (!this.equals(aOpeninghour.getHeadLibrarian()))
    {
      openinghour.remove(aOpeninghour);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addOpeninghourAt(OpeningHour aOpeninghour, int index)
  {  
    boolean wasAdded = false;
    if(addOpeninghour(aOpeninghour))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOpeninghour()) { index = numberOfOpeninghour() - 1; }
      openinghour.remove(aOpeninghour);
      openinghour.add(index, aOpeninghour);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveOpeninghourAt(OpeningHour aOpeninghour, int index)
  {
    boolean wasAdded = false;
    if(openinghour.contains(aOpeninghour))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOpeninghour()) { index = numberOfOpeninghour() - 1; }
      openinghour.remove(aOpeninghour);
      openinghour.add(index, aOpeninghour);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addOpeninghourAt(aOpeninghour, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfShift()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Shift addShift(Time aStartTime, Time aEndTime, Shift.DayOfWeek aDay)
  {
    return new Shift(aStartTime, aEndTime, aDay, this);
  }

  public boolean addShift(Shift aShift)
  {
    boolean wasAdded = false;
    if (shift.contains(aShift)) { return false; }
    if (shift.contains(aShift)) { return false; }
    HeadLibrarian existingHeadLibrarian = aShift.getHeadLibrarian();
    boolean isNewHeadLibrarian = existingHeadLibrarian != null && !this.equals(existingHeadLibrarian);
    if (isNewHeadLibrarian)
    {
      aShift.setHeadLibrarian(this);
    }
    else
    {
      shift.add(aShift);
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
      shift.remove(aShift);
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
      if(index > numberOfShift()) { index = numberOfShift() - 1; }
      shift.remove(aShift);
      shift.add(index, aShift);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveShiftAt(Shift aShift, int index)
  {
    boolean wasAdded = false;
    if(shift.contains(aShift))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfShift()) { index = numberOfShift() - 1; }
      shift.remove(aShift);
      shift.add(index, aShift);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addShiftAt(aShift, index);
    }
    return wasAdded;
  }
  /* Code from template association_set_specialization_reqCommonCode */  /* Code from template association_MinimumNumberOfMethod_relatedSpecialization */
  public static int minimumNumberOfShift_Shift()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne_relatedSpecialization */
  public Shift addShift(Time aStartTime, Time aEndTime, Shift.DayOfWeek aDay)
  {
    return new Shift(aStartTime, aEndTime, aDay, this);
  }

  public boolean addShift(Shift aShift)
  {
    boolean wasAdded = false;
    if (shift.contains(aShift)) { return false; }
    if (shift.contains(aShift)) { return false; }
    HeadLibrarian existingHeadLibrarian = aShift.getHeadLibrarian();
    boolean isNewHeadLibrarian = existingHeadLibrarian != null && !this.equals(existingHeadLibrarian);
    if (isNewHeadLibrarian)
    {
      aShift.setHeadLibrarian(this);
    }
    else
    {
      shift.add(aShift);
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
      shift.remove(aShift);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions_relatedSpecialization */
  public boolean addShiftAt(Shift aShift, int index)
  {  
    boolean wasAdded = false;
    if(addShift(aShift))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfShift()) { index = numberOfShift() - 1; }
      shift.remove(aShift);
      shift.add(index, aShift);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveShiftAt(Shift aShift, int index)
  {
    boolean wasAdded = false;
    if(shift.contains(aShift))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfShift()) { index = numberOfShift() - 1; }
      shift.remove(aShift);
      shift.add(index, aShift);
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
    for(int i=openinghour.size(); i > 0; i--)
    {
      OpeningHour aOpeninghour = openinghour.get(i - 1);
      aOpeninghour.delete();
    }
    for(int i=shift.size(); i > 0; i--)
    {
      Shift aShift = shift.get(i - 1);
      aShift.delete();
    }
    Library existingLibrary = library;
    library = null;
    if (existingLibrary != null)
    {
      existingLibrary.delete();
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "aShift" + "=" + (getAShift() != null ? !getAShift().equals(this)  ? getAShift().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "library = "+(getLibrary()!=null?Integer.toHexString(System.identityHashCode(getLibrary())):"null");
  }
}