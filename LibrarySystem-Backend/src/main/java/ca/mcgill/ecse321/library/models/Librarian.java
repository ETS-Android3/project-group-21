/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4607.2d2b84eb8 modeling language!*/

package ca.mcgill.ecse321.library.models;
import java.util.*;

// line 49 "../../../../../LibrarySystem.ump"
public class Librarian extends User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Librarian Attributes
  private Shift aShift;

  //Librarian Associations
  private List<Shift> shift;
  private Library library;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Librarian(String aFullName, int aCardID, String aAddress, String aUsername, String aPassword, boolean aOnlineAccountActivated, Shift aAShift, Library aLibrary)
  {
    super(aFullName, aCardID, aAddress, aUsername, aPassword, aOnlineAccountActivated);
    aShift = aAShift;
    shift = new ArrayList<Shift>();
    boolean didAddLibrary = setLibrary(aLibrary);
    if (!didAddLibrary)
    {
      throw new RuntimeException("Unable to create librarian due to library. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
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
  /* Code from template association_GetOne */
  public Library getLibrary()
  {
    return library;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfShift()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addShift(Shift aShift)
  {
    boolean wasAdded = false;
    if (shift.contains(aShift)) { return false; }
    shift.add(aShift);
    if (aShift.indexOfLibrarian(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aShift.addLibrarian(this);
      if (!wasAdded)
      {
        shift.remove(aShift);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeShift(Shift aShift)
  {
    boolean wasRemoved = false;
    if (!shift.contains(aShift))
    {
      return wasRemoved;
    }

    int oldIndex = shift.indexOf(aShift);
    shift.remove(oldIndex);
    if (aShift.indexOfLibrarian(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aShift.removeLibrarian(this);
      if (!wasRemoved)
      {
        shift.add(oldIndex,aShift);
      }
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
  /* Code from template association_SetOneToMany */
  public boolean setLibrary(Library aLibrary)
  {
    boolean wasSet = false;
    if (aLibrary == null)
    {
      return wasSet;
    }

    Library existingLibrary = library;
    library = aLibrary;
    if (existingLibrary != null && !existingLibrary.equals(aLibrary))
    {
      existingLibrary.removeLibrarian(this);
    }
    library.addLibrarian(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ArrayList<Shift> copyOfShift = new ArrayList<Shift>(shift);
    shift.clear();
    for(Shift aShift : copyOfShift)
    {
      aShift.removeLibrarian(this);
    }
    Library placeholderLibrary = library;
    this.library = null;
    if(placeholderLibrary != null)
    {
      placeholderLibrary.removeLibrarian(this);
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