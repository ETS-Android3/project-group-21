/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4607.2d2b84eb8 modeling language!*/

package ca.mcgill.ecse321.library.models;
import java.util.*;
import java.sql.Time;

// line 8 "../../../../../LibrarySystem.ump"
public class Library
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Library Associations
  private HeadLibrarian headlibrarian;
  private List<Librarian> librarian;
  private List<Citizen> citizen;
  private List<Reservation> reservation;
  private List<LibraryItem> libraryitem;
  private List<OpeningHour> openinghour;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Library(HeadLibrarian aHeadlibrarian)
  {
    if (aHeadlibrarian == null || aHeadlibrarian.getLibrary() != null)
    {
      throw new RuntimeException("Unable to create Library due to aHeadlibrarian. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    headlibrarian = aHeadlibrarian;
    librarian = new ArrayList<Librarian>();
    citizen = new ArrayList<Citizen>();
    reservation = new ArrayList<Reservation>();
    libraryitem = new ArrayList<LibraryItem>();
    openinghour = new ArrayList<OpeningHour>();
  }

  public Library(String aFullNameForHeadlibrarian, int aCardIDForHeadlibrarian, String aAddressForHeadlibrarian, String aUsernameForHeadlibrarian, String aPasswordForHeadlibrarian, boolean aOnlineAccountActivatedForHeadlibrarian, Shift aAShiftForHeadlibrarian)
  {
    headlibrarian = new HeadLibrarian(aFullNameForHeadlibrarian, aCardIDForHeadlibrarian, aAddressForHeadlibrarian, aUsernameForHeadlibrarian, aPasswordForHeadlibrarian, aOnlineAccountActivatedForHeadlibrarian, aAShiftForHeadlibrarian, this);
    librarian = new ArrayList<Librarian>();
    citizen = new ArrayList<Citizen>();
    reservation = new ArrayList<Reservation>();
    libraryitem = new ArrayList<LibraryItem>();
    openinghour = new ArrayList<OpeningHour>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public HeadLibrarian getHeadlibrarian()
  {
    return headlibrarian;
  }
  /* Code from template association_GetMany */
  public Librarian getLibrarian(int index)
  {
    Librarian aLibrarian = librarian.get(index);
    return aLibrarian;
  }

  public List<Librarian> getLibrarian()
  {
    List<Librarian> newLibrarian = Collections.unmodifiableList(librarian);
    return newLibrarian;
  }

  public int numberOfLibrarian()
  {
    int number = librarian.size();
    return number;
  }

  public boolean hasLibrarian()
  {
    boolean has = librarian.size() > 0;
    return has;
  }

  public int indexOfLibrarian(Librarian aLibrarian)
  {
    int index = librarian.indexOf(aLibrarian);
    return index;
  }
  /* Code from template association_GetMany */
  public Citizen getCitizen(int index)
  {
    Citizen aCitizen = citizen.get(index);
    return aCitizen;
  }

  public List<Citizen> getCitizen()
  {
    List<Citizen> newCitizen = Collections.unmodifiableList(citizen);
    return newCitizen;
  }

  public int numberOfCitizen()
  {
    int number = citizen.size();
    return number;
  }

  public boolean hasCitizen()
  {
    boolean has = citizen.size() > 0;
    return has;
  }

  public int indexOfCitizen(Citizen aCitizen)
  {
    int index = citizen.indexOf(aCitizen);
    return index;
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
  /* Code from template association_GetMany */
  public LibraryItem getLibraryitem(int index)
  {
    LibraryItem aLibraryitem = libraryitem.get(index);
    return aLibraryitem;
  }

  public List<LibraryItem> getLibraryitem()
  {
    List<LibraryItem> newLibraryitem = Collections.unmodifiableList(libraryitem);
    return newLibraryitem;
  }

  public int numberOfLibraryitem()
  {
    int number = libraryitem.size();
    return number;
  }

  public boolean hasLibraryitem()
  {
    boolean has = libraryitem.size() > 0;
    return has;
  }

  public int indexOfLibraryitem(LibraryItem aLibraryitem)
  {
    int index = libraryitem.indexOf(aLibraryitem);
    return index;
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfLibrarian()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Librarian addLibrarian(String aFullName, int aCardID, String aAddress, String aUsername, String aPassword, boolean aOnlineAccountActivated, Shift aAShift)
  {
    return new Librarian(aFullName, aCardID, aAddress, aUsername, aPassword, aOnlineAccountActivated, aAShift, this);
  }

  public boolean addLibrarian(Librarian aLibrarian)
  {
    boolean wasAdded = false;
    if (librarian.contains(aLibrarian)) { return false; }
    Library existingLibrary = aLibrarian.getLibrary();
    boolean isNewLibrary = existingLibrary != null && !this.equals(existingLibrary);
    if (isNewLibrary)
    {
      aLibrarian.setLibrary(this);
    }
    else
    {
      librarian.add(aLibrarian);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeLibrarian(Librarian aLibrarian)
  {
    boolean wasRemoved = false;
    //Unable to remove aLibrarian, as it must always have a library
    if (!this.equals(aLibrarian.getLibrary()))
    {
      librarian.remove(aLibrarian);
      wasRemoved = true;
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
      if(index > numberOfLibrarian()) { index = numberOfLibrarian() - 1; }
      librarian.remove(aLibrarian);
      librarian.add(index, aLibrarian);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveLibrarianAt(Librarian aLibrarian, int index)
  {
    boolean wasAdded = false;
    if(librarian.contains(aLibrarian))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLibrarian()) { index = numberOfLibrarian() - 1; }
      librarian.remove(aLibrarian);
      librarian.add(index, aLibrarian);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addLibrarianAt(aLibrarian, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfCitizen()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Citizen addCitizen(String aFullName, int aCardID, String aAddress, String aUsername, String aPassword, boolean aOnlineAccountActivated, boolean aIsLocal, double aBalance)
  {
    return new Citizen(aFullName, aCardID, aAddress, aUsername, aPassword, aOnlineAccountActivated, aIsLocal, aBalance, this);
  }

  public boolean addCitizen(Citizen aCitizen)
  {
    boolean wasAdded = false;
    if (citizen.contains(aCitizen)) { return false; }
    Library existingLibrary = aCitizen.getLibrary();
    boolean isNewLibrary = existingLibrary != null && !this.equals(existingLibrary);
    if (isNewLibrary)
    {
      aCitizen.setLibrary(this);
    }
    else
    {
      citizen.add(aCitizen);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeCitizen(Citizen aCitizen)
  {
    boolean wasRemoved = false;
    //Unable to remove aCitizen, as it must always have a library
    if (!this.equals(aCitizen.getLibrary()))
    {
      citizen.remove(aCitizen);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addCitizenAt(Citizen aCitizen, int index)
  {  
    boolean wasAdded = false;
    if(addCitizen(aCitizen))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCitizen()) { index = numberOfCitizen() - 1; }
      citizen.remove(aCitizen);
      citizen.add(index, aCitizen);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveCitizenAt(Citizen aCitizen, int index)
  {
    boolean wasAdded = false;
    if(citizen.contains(aCitizen))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCitizen()) { index = numberOfCitizen() - 1; }
      citizen.remove(aCitizen);
      citizen.add(index, aCitizen);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addCitizenAt(aCitizen, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfReservation()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Reservation addReservation(int aReservationID, User aUser, LibraryItem aLibraryItem)
  {
    return new Reservation(aReservationID, this, aUser, aLibraryItem);
  }

  public boolean addReservation(Reservation aReservation)
  {
    boolean wasAdded = false;
    if (reservation.contains(aReservation)) { return false; }
    Library existingLibrary = aReservation.getLibrary();
    boolean isNewLibrary = existingLibrary != null && !this.equals(existingLibrary);
    if (isNewLibrary)
    {
      aReservation.setLibrary(this);
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
    //Unable to remove aReservation, as it must always have a library
    if (!this.equals(aReservation.getLibrary()))
    {
      reservation.remove(aReservation);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addReservationAt(Reservation aReservation, int index)
  {  
    boolean wasAdded = false;
    if(addReservation(aReservation))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfReservation()) { index = numberOfReservation() - 1; }
      reservation.remove(aReservation);
      reservation.add(index, aReservation);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveReservationAt(Reservation aReservation, int index)
  {
    boolean wasAdded = false;
    if(reservation.contains(aReservation))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfReservation()) { index = numberOfReservation() - 1; }
      reservation.remove(aReservation);
      reservation.add(index, aReservation);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addReservationAt(aReservation, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfLibraryitem()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public LibraryItem addLibraryitem(LibraryItem.ItemType aType, int aBarcode, String aTitle, boolean aIsReservable, boolean aIsReserved, int aLoanPeriod)
  {
    return new LibraryItem(aType, aBarcode, aTitle, aIsReservable, aIsReserved, aLoanPeriod, this);
  }

  public boolean addLibraryitem(LibraryItem aLibraryitem)
  {
    boolean wasAdded = false;
    if (libraryitem.contains(aLibraryitem)) { return false; }
    Library existingLibrary = aLibraryitem.getLibrary();
    boolean isNewLibrary = existingLibrary != null && !this.equals(existingLibrary);
    if (isNewLibrary)
    {
      aLibraryitem.setLibrary(this);
    }
    else
    {
      libraryitem.add(aLibraryitem);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeLibraryitem(LibraryItem aLibraryitem)
  {
    boolean wasRemoved = false;
    //Unable to remove aLibraryitem, as it must always have a library
    if (!this.equals(aLibraryitem.getLibrary()))
    {
      libraryitem.remove(aLibraryitem);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addLibraryitemAt(LibraryItem aLibraryitem, int index)
  {  
    boolean wasAdded = false;
    if(addLibraryitem(aLibraryitem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLibraryitem()) { index = numberOfLibraryitem() - 1; }
      libraryitem.remove(aLibraryitem);
      libraryitem.add(index, aLibraryitem);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveLibraryitemAt(LibraryItem aLibraryitem, int index)
  {
    boolean wasAdded = false;
    if(libraryitem.contains(aLibraryitem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLibraryitem()) { index = numberOfLibraryitem() - 1; }
      libraryitem.remove(aLibraryitem);
      libraryitem.add(index, aLibraryitem);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addLibraryitemAt(aLibraryitem, index);
    }
    return wasAdded;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfOpeninghourValid()
  {
    boolean isValid = numberOfOpeninghour() >= minimumNumberOfOpeninghour();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfOpeninghour()
  {
    return 1;
  }
  /* Code from template association_AddMandatoryManyToOne */
  public OpeningHour addOpeninghour(Time aStartTime, Time aEndTime, OpeningHour.DayOfWeek aDay, HeadLibrarian aHeadLibrarian)
  {
    OpeningHour aNewOpeninghour = new OpeningHour(aStartTime, aEndTime, aDay, this, aHeadLibrarian);
    return aNewOpeninghour;
  }

  public boolean addOpeninghour(OpeningHour aOpeninghour)
  {
    boolean wasAdded = false;
    if (openinghour.contains(aOpeninghour)) { return false; }
    Library existingLibrary = aOpeninghour.getLibrary();
    boolean isNewLibrary = existingLibrary != null && !this.equals(existingLibrary);

    if (isNewLibrary && existingLibrary.numberOfOpeninghour() <= minimumNumberOfOpeninghour())
    {
      return wasAdded;
    }
    if (isNewLibrary)
    {
      aOpeninghour.setLibrary(this);
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
    //Unable to remove aOpeninghour, as it must always have a library
    if (this.equals(aOpeninghour.getLibrary()))
    {
      return wasRemoved;
    }

    //library already at minimum (1)
    if (numberOfOpeninghour() <= minimumNumberOfOpeninghour())
    {
      return wasRemoved;
    }

    openinghour.remove(aOpeninghour);
    wasRemoved = true;
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

  public void delete()
  {
    HeadLibrarian existingHeadlibrarian = headlibrarian;
    headlibrarian = null;
    if (existingHeadlibrarian != null)
    {
      existingHeadlibrarian.delete();
    }
    while (librarian.size() > 0)
    {
      Librarian aLibrarian = librarian.get(librarian.size() - 1);
      aLibrarian.delete();
      librarian.remove(aLibrarian);
    }
    
    while (citizen.size() > 0)
    {
      Citizen aCitizen = citizen.get(citizen.size() - 1);
      aCitizen.delete();
      citizen.remove(aCitizen);
    }
    
    while (reservation.size() > 0)
    {
      Reservation aReservation = reservation.get(reservation.size() - 1);
      aReservation.delete();
      reservation.remove(aReservation);
    }
    
    while (libraryitem.size() > 0)
    {
      LibraryItem aLibraryitem = libraryitem.get(libraryitem.size() - 1);
      aLibraryitem.delete();
      libraryitem.remove(aLibraryitem);
    }
    
    while (openinghour.size() > 0)
    {
      OpeningHour aOpeninghour = openinghour.get(openinghour.size() - 1);
      aOpeninghour.delete();
      openinghour.remove(aOpeninghour);
    }
    
  }

}