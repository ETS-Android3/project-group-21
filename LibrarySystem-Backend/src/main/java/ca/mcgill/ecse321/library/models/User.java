/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4607.2d2b84eb8 modeling language!*/

package ca.mcgill.ecse321.library.models;
import java.util.*;

// line 32 "../../../../../LibrarySystem.ump"
public class User
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<Integer, User> usersByCardID = new HashMap<Integer, User>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //User Attributes
  private String fullName;
  private int cardID;
  private String address;
  private String username;
  private String password;
  private boolean onlineAccountActivated;

  //User Associations
  private List<Reservation> reservation;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public User(String aFullName, int aCardID, String aAddress, String aUsername, String aPassword, boolean aOnlineAccountActivated)
  {
    fullName = aFullName;
    address = aAddress;
    username = aUsername;
    password = aPassword;
    onlineAccountActivated = aOnlineAccountActivated;
    if (!setCardID(aCardID))
    {
      throw new RuntimeException("Cannot create due to duplicate cardID. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    reservation = new ArrayList<Reservation>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setFullName(String aFullName)
  {
    boolean wasSet = false;
    fullName = aFullName;
    wasSet = true;
    return wasSet;
  }

  public boolean setCardID(int aCardID)
  {
    boolean wasSet = false;
    Integer anOldCardID = getCardID();
    if (anOldCardID != null && anOldCardID.equals(aCardID)) {
      return true;
    }
    if (hasWithCardID(aCardID)) {
      return wasSet;
    }
    cardID = aCardID;
    wasSet = true;
    if (anOldCardID != null) {
      usersByCardID.remove(anOldCardID);
    }
    usersByCardID.put(aCardID, this);
    return wasSet;
  }

  public boolean setAddress(String aAddress)
  {
    boolean wasSet = false;
    address = aAddress;
    wasSet = true;
    return wasSet;
  }

  public boolean setUsername(String aUsername)
  {
    boolean wasSet = false;
    username = aUsername;
    wasSet = true;
    return wasSet;
  }

  public boolean setPassword(String aPassword)
  {
    boolean wasSet = false;
    password = aPassword;
    wasSet = true;
    return wasSet;
  }

  public boolean setOnlineAccountActivated(boolean aOnlineAccountActivated)
  {
    boolean wasSet = false;
    onlineAccountActivated = aOnlineAccountActivated;
    wasSet = true;
    return wasSet;
  }

  public String getFullName()
  {
    return fullName;
  }

  public int getCardID()
  {
    return cardID;
  }
  /* Code from template attribute_GetUnique */
  public static User getWithCardID(int aCardID)
  {
    return usersByCardID.get(aCardID);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithCardID(int aCardID)
  {
    return getWithCardID(aCardID) != null;
  }

  public String getAddress()
  {
    return address;
  }

  public String getUsername()
  {
    return username;
  }

  public String getPassword()
  {
    return password;
  }

  public boolean getOnlineAccountActivated()
  {
    return onlineAccountActivated;
  }
  /* Code from template association_GetMany */
  public Reservation getReservation(int index)
  {
    Reservation aReservation = reservation.get(index);
    return aReservation;
  }

  public List<Reservation> getReservation()
  {
    List<Reservation> newReservation = Collections.unmodifiableList(reservation);
    return newReservation;
  }

  public int numberOfReservation()
  {
    int number = reservation.size();
    return number;
  }

  public boolean hasReservation()
  {
    boolean has = reservation.size() > 0;
    return has;
  }

  public int indexOfReservation(Reservation aReservation)
  {
    int index = reservation.indexOf(aReservation);
    return index;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfReservationValid()
  {
    boolean isValid = numberOfReservation() >= minimumNumberOfReservation() && numberOfReservation() <= maximumNumberOfReservation();
    return isValid;
  }
  /* Code from template association_RequiredNumberOfMethod */
  public static int requiredNumberOfReservation()
  {
    return 5;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfReservation()
  {
    return 5;
  }
  /* Code from template association_MaximumNumberOfMethod */
  public static int maximumNumberOfReservation()
  {
    return 5;
  }
  /* Code from template association_AddMNToOnlyOne */
  public Reservation addReservation(int aReservationID, Library aLibrary, LibraryItem aLibraryItem)
  {
    if (numberOfReservation() >= maximumNumberOfReservation())
    {
      return null;
    }
    else
    {
      return new Reservation(aReservationID, aLibrary, this, aLibraryItem);
    }
  }

  public boolean addReservation(Reservation aReservation)
  {
    boolean wasAdded = false;
    if (reservation.contains(aReservation)) { return false; }
    if (numberOfReservation() >= maximumNumberOfReservation())
    {
      return wasAdded;
    }

    User existingUser = aReservation.getUser();
    boolean isNewUser = existingUser != null && !this.equals(existingUser);

    if (isNewUser && existingUser.numberOfReservation() <= minimumNumberOfReservation())
    {
      return wasAdded;
    }

    if (isNewUser)
    {
      aReservation.setUser(this);
    }
    else
    {
      reservation.add(aReservation);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeReservation(Reservation aReservation)
  {
    boolean wasRemoved = false;
    //Unable to remove aReservation, as it must always have a user
    if (this.equals(aReservation.getUser()))
    {
      return wasRemoved;
    }

    //user already at minimum (5)
    if (numberOfReservation() <= minimumNumberOfReservation())
    {
      return wasRemoved;
    }
    reservation.remove(aReservation);
    wasRemoved = true;
    return wasRemoved;
  }

  public void delete()
  {
    usersByCardID.remove(getCardID());
    for(int i=reservation.size(); i > 0; i--)
    {
      Reservation aReservation = reservation.get(i - 1);
      aReservation.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "fullName" + ":" + getFullName()+ "," +
            "cardID" + ":" + getCardID()+ "," +
            "address" + ":" + getAddress()+ "," +
            "username" + ":" + getUsername()+ "," +
            "password" + ":" + getPassword()+ "," +
            "onlineAccountActivated" + ":" + getOnlineAccountActivated()+ "]";
  }
}