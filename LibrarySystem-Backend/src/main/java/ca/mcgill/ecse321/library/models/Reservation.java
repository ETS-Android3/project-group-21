package ca.mcgill.ecse321.library.models;
import java.util.*;

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
  private User user;
  private LibraryItem libraryItem;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Reservation(int aReservationID, LibraryItem aLibraryItem, User aUser)
  {
    if (!setReservationID(aReservationID))
    {
      throw new RuntimeException("Cannot create due to duplicate reservationID. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    if (aLibraryItem == null || aLibraryItem.getReservation() != null)
    {
      throw new RuntimeException("Unable to create Reservation due to aLibraryItem. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    libraryItem = aLibraryItem;
    boolean didAddUser = setUser(aUser);
    if (!didAddUser)
    {
      throw new RuntimeException("Unable to create reservation due to user. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  
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
  
  public User getUser()
  {
    return user;
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
  public LibraryItem getLibraryItem()
  {
    return libraryItem;
  }
  
  /* Code from template association_SetOneToAtMostN */
  public boolean setUser(User aUser)
  {
    boolean wasSet = false;
    //Must provide user to reservation
    if (aUser == null)
    {
      return wasSet;
    }

    //user already at maximum (5)
    if (aUser.numberOfReservations() >= User.maximumNumberOfReservations())
    {
      return wasSet;
    }
    
    User existingUser = user;
    user = aUser;
    if (existingUser != null && !existingUser.equals(aUser))
    {
      boolean didRemove = existingUser.removeReservation(this);
      if (!didRemove)
      {
        user = existingUser;
        return wasSet;
      }
    }
    user.addReservation(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    reservationsByReservationID.remove(getReservationID());
    LibraryItem existingLibraryItem = libraryItem;
    libraryItem = null;
    if (existingLibraryItem != null)
    {
      existingLibraryItem.delete();
    }
    User placeholderUser = user;
    this.user = null;
    if(placeholderUser != null)
    {
      placeholderUser.removeReservation(this);
    }
  }
  
  public String toString()
  {
    return super.toString() + "["+
            "reservationID" + ":" + getReservationID()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "libraryItem = "+(getLibraryItem()!=null?Integer.toHexString(System.identityHashCode(getLibraryItem())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "user = "+(getUser()!=null?Integer.toHexString(System.identityHashCode(getUser())):"null");
  }

}

