/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.library.models;
import java.util.*;

// line 7 "../../../../../LibrarySystem.ump"
public class Library
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Library Associations
  private HeadLibrarian headLibrarian;
  private List<Librarian> librarians;
  private List<Citizen> citizens;
  private List<Reservation> reservations;
  private List<LibraryItem> libraryItems;
  private List<OpeningHour> openingHours;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Library(HeadLibrarian aHeadLibrarian)
  {
    if (aHeadLibrarian == null || aHeadLibrarian.getLibrary() != null)
    {
      throw new RuntimeException("Unable to create Library due to aHeadLibrarian. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    headLibrarian = aHeadLibrarian;
    librarians = new ArrayList<Librarian>();
    citizens = new ArrayList<Citizen>();
    reservations = new ArrayList<Reservation>();
    libraryItems = new ArrayList<LibraryItem>();
    openingHours = new ArrayList<OpeningHour>();
  }

  public Library()
  {
    headLibrarian = new HeadLibrarian(this);
    librarians = new ArrayList<Librarian>();
    citizens = new ArrayList<Citizen>();
    reservations = new ArrayList<Reservation>();
    libraryItems = new ArrayList<LibraryItem>();
    openingHours = new ArrayList<OpeningHour>();
  }

  //------------------------
  // INTERFACE
  //------------------------
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
  /* Code from template association_GetMany */
  public Citizen getCitizen(int index)
  {
    Citizen aCitizen = citizens.get(index);
    return aCitizen;
  }

  public List<Citizen> getCitizens()
  {
    List<Citizen> newCitizens = Collections.unmodifiableList(citizens);
    return newCitizens;
  }

  public int numberOfCitizens()
  {
    int number = citizens.size();
    return number;
  }

  public boolean hasCitizens()
  {
    boolean has = citizens.size() > 0;
    return has;
  }

  public int indexOfCitizen(Citizen aCitizen)
  {
    int index = citizens.indexOf(aCitizen);
    return index;
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
  /* Code from template association_GetMany */
  public LibraryItem getLibraryItem(int index)
  {
    LibraryItem aLibraryItem = libraryItems.get(index);
    return aLibraryItem;
  }

  public List<LibraryItem> getLibraryItems()
  {
    List<LibraryItem> newLibraryItems = Collections.unmodifiableList(libraryItems);
    return newLibraryItems;
  }

  public int numberOfLibraryItems()
  {
    int number = libraryItems.size();
    return number;
  }

  public boolean hasLibraryItems()
  {
    boolean has = libraryItems.size() > 0;
    return has;
  }

  public int indexOfLibraryItem(LibraryItem aLibraryItem)
  {
    int index = libraryItems.indexOf(aLibraryItem);
    return index;
  }
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfLibrarians()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Librarian addLibrarian()
  {
    return new Librarian(this);
  }

  public boolean addLibrarian(Librarian aLibrarian)
  {
    boolean wasAdded = false;
    if (librarians.contains(aLibrarian)) { return false; }
    Library existingLibrary = aLibrarian.getLibrary();
    boolean isNewLibrary = existingLibrary != null && !this.equals(existingLibrary);
    if (isNewLibrary)
    {
      aLibrarian.setLibrary(this);
    }
    else
    {
      librarians.add(aLibrarian);
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
      librarians.remove(aLibrarian);
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfCitizens()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Citizen addCitizen()
  {
    return new Citizen(this);
  }

  public boolean addCitizen(Citizen aCitizen)
  {
    boolean wasAdded = false;
    if (citizens.contains(aCitizen)) { return false; }
    Library existingLibrary = aCitizen.getLibrary();
    boolean isNewLibrary = existingLibrary != null && !this.equals(existingLibrary);
    if (isNewLibrary)
    {
      aCitizen.setLibrary(this);
    }
    else
    {
      citizens.add(aCitizen);
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
      citizens.remove(aCitizen);
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
      if(index > numberOfCitizens()) { index = numberOfCitizens() - 1; }
      citizens.remove(aCitizen);
      citizens.add(index, aCitizen);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveCitizenAt(Citizen aCitizen, int index)
  {
    boolean wasAdded = false;
    if(citizens.contains(aCitizen))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCitizens()) { index = numberOfCitizens() - 1; }
      citizens.remove(aCitizen);
      citizens.add(index, aCitizen);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addCitizenAt(aCitizen, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfReservations()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Reservation addReservation(int aReservationID, LibraryItem aLibraryItem)
  {
    return new Reservation(aReservationID, this, aLibraryItem);
  }

  public boolean addReservation(Reservation aReservation)
  {
    boolean wasAdded = false;
    if (reservations.contains(aReservation)) { return false; }
    Library existingLibrary = aReservation.getLibrary();
    boolean isNewLibrary = existingLibrary != null && !this.equals(existingLibrary);
    if (isNewLibrary)
    {
      aReservation.setLibrary(this);
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
    //Unable to remove aReservation, as it must always have a library
    if (!this.equals(aReservation.getLibrary()))
    {
      reservations.remove(aReservation);
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
      if(index > numberOfReservations()) { index = numberOfReservations() - 1; }
      reservations.remove(aReservation);
      reservations.add(index, aReservation);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveReservationAt(Reservation aReservation, int index)
  {
    boolean wasAdded = false;
    if(reservations.contains(aReservation))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfReservations()) { index = numberOfReservations() - 1; }
      reservations.remove(aReservation);
      reservations.add(index, aReservation);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addReservationAt(aReservation, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfLibraryItems()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public LibraryItem addLibraryItem(LibraryItem.ItemType aType, int aBarcode, String aTitle, boolean aIsReservable, boolean aIsReserved, int aLoanPeriod)
  {
    return new LibraryItem(aType, aBarcode, aTitle, aIsReservable, aIsReserved, aLoanPeriod, this);
  }

  public boolean addLibraryItem(LibraryItem aLibraryItem)
  {
    boolean wasAdded = false;
    if (libraryItems.contains(aLibraryItem)) { return false; }
    Library existingLibrary = aLibraryItem.getLibrary();
    boolean isNewLibrary = existingLibrary != null && !this.equals(existingLibrary);
    if (isNewLibrary)
    {
      aLibraryItem.setLibrary(this);
    }
    else
    {
      libraryItems.add(aLibraryItem);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeLibraryItem(LibraryItem aLibraryItem)
  {
    boolean wasRemoved = false;
    //Unable to remove aLibraryItem, as it must always have a library
    if (!this.equals(aLibraryItem.getLibrary()))
    {
      libraryItems.remove(aLibraryItem);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addLibraryItemAt(LibraryItem aLibraryItem, int index)
  {  
    boolean wasAdded = false;
    if(addLibraryItem(aLibraryItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLibraryItems()) { index = numberOfLibraryItems() - 1; }
      libraryItems.remove(aLibraryItem);
      libraryItems.add(index, aLibraryItem);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveLibraryItemAt(LibraryItem aLibraryItem, int index)
  {
    boolean wasAdded = false;
    if(libraryItems.contains(aLibraryItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLibraryItems()) { index = numberOfLibraryItems() - 1; }
      libraryItems.remove(aLibraryItem);
      libraryItems.add(index, aLibraryItem);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addLibraryItemAt(aLibraryItem, index);
    }
    return wasAdded;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfOpeningHoursValid()
  {
    boolean isValid = numberOfOpeningHours() >= minimumNumberOfOpeningHours();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfOpeningHours()
  {
    return 1;
  }
  /* Code from template association_AddMandatoryManyToOne */
  public OpeningHour addOpeningHour()
  {
    OpeningHour aNewOpeningHour = new OpeningHour(this);
    return aNewOpeningHour;
  }

  public boolean addOpeningHour(OpeningHour aOpeningHour)
  {
    boolean wasAdded = false;
    if (openingHours.contains(aOpeningHour)) { return false; }
    Library existingLibrary = aOpeningHour.getLibrary();
    boolean isNewLibrary = existingLibrary != null && !this.equals(existingLibrary);

    if (isNewLibrary && existingLibrary.numberOfOpeningHours() <= minimumNumberOfOpeningHours())
    {
      return wasAdded;
    }
    if (isNewLibrary)
    {
      aOpeningHour.setLibrary(this);
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
    //Unable to remove aOpeningHour, as it must always have a library
    if (this.equals(aOpeningHour.getLibrary()))
    {
      return wasRemoved;
    }

    //library already at minimum (1)
    if (numberOfOpeningHours() <= minimumNumberOfOpeningHours())
    {
      return wasRemoved;
    }

    openingHours.remove(aOpeningHour);
    wasRemoved = true;
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

  public void delete()
  {
    HeadLibrarian existingHeadLibrarian = headLibrarian;
    headLibrarian = null;
    if (existingHeadLibrarian != null)
    {
      existingHeadLibrarian.delete();
    }
    for(int i=librarians.size(); i > 0; i--)
    {
      Librarian aLibrarian = librarians.get(i - 1);
      aLibrarian.delete();
    }
    for(int i=citizens.size(); i > 0; i--)
    {
      Citizen aCitizen = citizens.get(i - 1);
      aCitizen.delete();
    }
    for(int i=reservations.size(); i > 0; i--)
    {
      Reservation aReservation = reservations.get(i - 1);
      aReservation.delete();
    }
    for(int i=libraryItems.size(); i > 0; i--)
    {
      LibraryItem aLibraryItem = libraryItems.get(i - 1);
      aLibraryItem.delete();
    }
    for(int i=openingHours.size(); i > 0; i--)
    {
      OpeningHour aOpeningHour = openingHours.get(i - 1);
      aOpeningHour.delete();
    }
  }

}