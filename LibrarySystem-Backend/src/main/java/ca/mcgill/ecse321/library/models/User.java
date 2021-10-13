package ca.mcgill.ecse321.library.models;
import java.util.*;

// line 2 "model.ump"
// line 24 "model.ump"
public class User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //User Attributes
  private String fullName;
  private int cardId;
  private String adress;
  private String username;
  private String password;
  private boolean onlineAccountActivated;

  //User Associations
  private List<Reservation> reservations;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public User(String aFullName, int aCardId, String aAdress, String aUsername, String aPassword, boolean aOnlineAccountActivated)
  {
    fullName = aFullName;
    cardId = aCardId;
    adress = aAdress;
    username = aUsername;
    password = aPassword;
    onlineAccountActivated = aOnlineAccountActivated;
    reservations = new ArrayList<Reservation>();
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

  public boolean setCardId(int aCardId)
  {
    boolean wasSet = false;
    cardId = aCardId;
    wasSet = true;
    return wasSet;
  }

  public boolean setAdress(String aAdress)
  {
    boolean wasSet = false;
    adress = aAdress;
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

  public int getCardId()
  {
    return cardId;
  }

  public String getAdress()
  {
    return adress;
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
  /* Code from template attribute_IsBoolean */
  public boolean isOnlineAccountActivated()
  {
    return onlineAccountActivated;
  }
  /* Code from template association_GetMany */
  public Reservation getReservation(int index)
  {
    Reservation aReservation = reservations.get(index);
    return aReservation;
  }

  public List<Reservation> getReservations()
  {
    List<Reservation> newReservations = Collections.unmodifiableList(reservations);
    return newReservations;
  }

  public int numberOfReservations()
  {
    int number = reservations.size();
    return number;
  }

  public boolean hasReservations()
  {
    boolean has = reservations.size() > 0;
    return has;
  }

  public int indexOfReservation(Reservation aReservation)
  {
    int index = reservations.indexOf(aReservation);
    return index;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfReservationsValid()
  {
    boolean isValid = numberOfReservations() >= minimumNumberOfReservations() && numberOfReservations() <= maximumNumberOfReservations();
    return isValid;
  }
  /* Code from template association_RequiredNumberOfMethod */
  public static int requiredNumberOfReservations()
  {
    return 5;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfReservations()
  {
    return 5;
  }
  /* Code from template association_MaximumNumberOfMethod */
  public static int maximumNumberOfReservations()
  {
    return 5;
  }
  /* Code from template association_AddMNToOnlyOne */
  public Reservation addReservation()
  {
    if (numberOfReservations() >= maximumNumberOfReservations())
    {
      return null;
    }
    else
    {
      return new Reservation(this);
    }
  }

  public boolean addReservation(Reservation aReservation)
  {
    boolean wasAdded = false;
    if (reservations.contains(aReservation)) { return false; }
    if (numberOfReservations() >= maximumNumberOfReservations())
    {
      return wasAdded;
    }

    User existingUser = aReservation.getUser();
    boolean isNewUser = existingUser != null && !this.equals(existingUser);

    if (isNewUser && existingUser.numberOfReservations() <= minimumNumberOfReservations())
    {
      return wasAdded;
    }

    if (isNewUser)
    {
      aReservation.setUser(this);
    }
    else
    {
      reservations.add(aReservation);
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
    if (numberOfReservations() <= minimumNumberOfReservations())
    {
      return wasRemoved;
    }
    reservations.remove(aReservation);
    wasRemoved = true;
    return wasRemoved;
  }

  public void delete()
  {
    for(int i=reservations.size(); i > 0; i--)
    {
      Reservation aReservation = reservations.get(i - 1);
      aReservation.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "fullName" + ":" + getFullName()+ "," +
            "cardId" + ":" + getCardId()+ "," +
            "adress" + ":" + getAdress()+ "," +
            "username" + ":" + getUsername()+ "," +
            "password" + ":" + getPassword()+ "," +
            "onlineAccountActivated" + ":" + getOnlineAccountActivated()+ "]";
  }
}