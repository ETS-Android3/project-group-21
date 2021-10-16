/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.library.models;
import java.sql.Time;
import java.util.*;

// line 73 "../../../../../LibrarySystem.ump"
public class Shift
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum DayOfWeek { Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday }

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<Integer, Shift> shiftsByShiftCode = new HashMap<Integer, Shift>();

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

  public Shift(Time aStartTime, Time aEndTime, DayOfWeek aDay, int aShiftCode, HeadLibrarian aHeadLibrarian)
  {
    startTime = aStartTime;
    endTime = aEndTime;
    day = aDay;
    if (!setShiftCode(aShiftCode))
    {
      throw new RuntimeException("Cannot create due to duplicate shiftCode. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    boolean didAddHeadLibrarian = setHeadLibrarian(aHeadLibrarian);
    if (!didAddHeadLibrarian)
    {
      throw new RuntimeException("Unable to create shift due to headLibrarian. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    librarians = new ArrayList<Librarian>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setStartTime(Time aStartTime)
  {
    boolean wasSet = false;
    startTime = aStartTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndTime(Time aEndTime)
  {
    boolean wasSet = false;
    endTime = aEndTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setDay(DayOfWeek aDay)
  {
    boolean wasSet = false;
    day = aDay;
    wasSet = true;
    return wasSet;
  }

  public boolean setShiftCode(int aShiftCode)
  {
    boolean wasSet = false;
    Integer anOldShiftCode = getShiftCode();
    if (anOldShiftCode != null && anOldShiftCode.equals(aShiftCode)) {
      return true;
    }
    if (hasWithShiftCode(aShiftCode)) {
      return wasSet;
    }
    shiftCode = aShiftCode;
    wasSet = true;
    if (anOldShiftCode != null) {
      shiftsByShiftCode.remove(anOldShiftCode);
    }
    shiftsByShiftCode.put(aShiftCode, this);
    return wasSet;
  }

  /**
   * Essentailly the same as the OpeningHour Class
   */
  public Time getStartTime()
  {
    return startTime;
  }

  public Time getEndTime()
  {
    return endTime;
  }

  public DayOfWeek getDay()
  {
    return day;
  }

  public int getShiftCode()
  {
    return shiftCode;
  }
  /* Code from template attribute_GetUnique */
  public static Shift getWithShiftCode(int aShiftCode)
  {
    return shiftsByShiftCode.get(aShiftCode);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithShiftCode(int aShiftCode)
  {
    return getWithShiftCode(aShiftCode) != null;
  }
  /* Code from template association_GetOne */
  public HeadLibrarian getHeadLibrarian()
  {
    return headLibrarian;
  }
  /* Code from template association_GetMany */
  public Librarian getLibrarian(int index)
  {
    Librarian aLibrarian = librarians.get(index);
    return aLibrarian;
  }

  public List<Librarian> getLibrarians()
  {
    List<Librarian> newLibrarians = Collections.unmodifiableList(librarians);
    return newLibrarians;
  }

  public int numberOfLibrarians()
  {
    int number = librarians.size();
    return number;
  }

  public boolean hasLibrarians()
  {
    boolean has = librarians.size() > 0;
    return has;
  }

  public int indexOfLibrarian(Librarian aLibrarian)
  {
    int index = librarians.indexOf(aLibrarian);
    return index;
  }
  /* Code from template association_SetOneToMany */
  public boolean setHeadLibrarian(HeadLibrarian aHeadLibrarian)
  {
    boolean wasSet = false;
    if (aHeadLibrarian == null)
    {
      return wasSet;
    }

    HeadLibrarian existingHeadLibrarian = headLibrarian;
    headLibrarian = aHeadLibrarian;
    if (existingHeadLibrarian != null && !existingHeadLibrarian.equals(aHeadLibrarian))
    {
      existingHeadLibrarian.removeShift(this);
    }
    headLibrarian.addShift(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfLibrarians()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addLibrarian(Librarian aLibrarian)
  {
    boolean wasAdded = false;
    if (librarians.contains(aLibrarian)) { return false; }
    librarians.add(aLibrarian);
    if (aLibrarian.indexOfShift(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aLibrarian.addShift(this);
      if (!wasAdded)
      {
        librarians.remove(aLibrarian);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeLibrarian(Librarian aLibrarian)
  {
    boolean wasRemoved = false;
    if (!librarians.contains(aLibrarian))
    {
      return wasRemoved;
    }

    int oldIndex = librarians.indexOf(aLibrarian);
    librarians.remove(oldIndex);
    if (aLibrarian.indexOfShift(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aLibrarian.removeShift(this);
      if (!wasRemoved)
      {
        librarians.add(oldIndex,aLibrarian);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addLibrarianAt(Librarian aLibrarian, int index)
  {  
    boolean wasAdded = false;
    if(addLibrarian(aLibrarian))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLibrarians()) { index = numberOfLibrarians() - 1; }
      librarians.remove(aLibrarian);
      librarians.add(index, aLibrarian);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveLibrarianAt(Librarian aLibrarian, int index)
  {
    boolean wasAdded = false;
    if(librarians.contains(aLibrarian))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLibrarians()) { index = numberOfLibrarians() - 1; }
      librarians.remove(aLibrarian);
      librarians.add(index, aLibrarian);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addLibrarianAt(aLibrarian, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    shiftsByShiftCode.remove(getShiftCode());
    HeadLibrarian placeholderHeadLibrarian = headLibrarian;
    this.headLibrarian = null;
    if(placeholderHeadLibrarian != null)
    {
      placeholderHeadLibrarian.removeShift(this);
    }
    ArrayList<Librarian> copyOfLibrarians = new ArrayList<Librarian>(librarians);
    librarians.clear();
    for(Librarian aLibrarian : copyOfLibrarians)
    {
      aLibrarian.removeShift(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "shiftCode" + ":" + getShiftCode()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "startTime" + "=" + (getStartTime() != null ? !getStartTime().equals(this)  ? getStartTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endTime" + "=" + (getEndTime() != null ? !getEndTime().equals(this)  ? getEndTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "day" + "=" + (getDay() != null ? !getDay().equals(this)  ? getDay().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "headLibrarian = "+(getHeadLibrarian()!=null?Integer.toHexString(System.identityHashCode(getHeadLibrarian())):"null");
  }
}