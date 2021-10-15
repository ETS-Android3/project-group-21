/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4607.2d2b84eb8 modeling language!*/

package ca.mcgill.ecse321.library.models;
import java.util.*;

// line 17 "../../../../../../LibrarySystem.ump"
public class Reservation
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<Integer, Reservation> reservationsByReservationID = new HashMap<Integer, Reservation>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Reservation Attributes
  private int reservationID;

  //Reservation Associations
  private Library library;
  private LibraryItem libraryItem;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Reservation(int aReservationID, Library aLibrary, LibraryItem aLibraryItem)
  {
    if (!setReservationID(aReservationID))
    {
      throw new RuntimeException("Cannot create due to duplicate reservationID. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    boolean didAddLibrary = setLibrary(aLibrary);
    if (!didAddLibrary)
    {
      throw new RuntimeException("Unable to create reservation due to library. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    if (!setLibraryItem(aLibraryItem))
    {
      throw new RuntimeException("Unable to create Reservation due to aLibraryItem. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setReservationID(int aReservationID)
  {
    boolean wasSet = false;
    Integer anOldReservationID = getReservationID();
    if (anOldReservationID != null && anOldReservationID.equals(aReservationID)) {
      return true;
    }
    if (hasWithReservationID(aReservationID)) {
      return wasSet;
    }
    reservationID = aReservationID;
    wasSet = true;
    if (anOldReservationID != null) {
      reservationsByReservationID.remove(anOldReservationID);
    }
    reservationsByReservationID.put(aReservationID, this);
    return wasSet;
  }

  public int getReservationID()
  {
    return reservationID;
  }
  /* Code from template attribute_GetUnique */
  public static Reservation getWithReservationID(int aReservationID)
  {
    return reservationsByReservationID.get(aReservationID);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithReservationID(int aReservationID)
  {
    return getWithReservationID(aReservationID) != null;
  }
  /* Code from template association_GetOne */
  public Library getLibrary()
  {
    return library;
  }
  /* Code from template association_GetOne */
  public LibraryItem getLibraryItem()
  {
    return libraryItem;
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
      existingLibrary.removeReservation(this);
    }
    library.addReservation(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setLibraryItem(LibraryItem aNewLibraryItem)
  {
    boolean wasSet = false;
    if (aNewLibraryItem != null)
    {
      libraryItem = aNewLibraryItem;
      wasSet = true;
    }
    return wasSet;
  }

  public void delete()
  {
    reservationsByReservationID.remove(getReservationID());
    Library placeholderLibrary = library;
    this.library = null;
    if(placeholderLibrary != null)
    {
      placeholderLibrary.removeReservation(this);
    }
    libraryItem = null;
  }


  public String toString()
  {
    return super.toString() + "["+
            "reservationID" + ":" + getReservationID()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "library = "+(getLibrary()!=null?Integer.toHexString(System.identityHashCode(getLibrary())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "libraryItem = "+(getLibraryItem()!=null?Integer.toHexString(System.identityHashCode(getLibraryItem())):"null");
  }
}