/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4607.2d2b84eb8 modeling language!*/

package ca.mcgill.ecse321.library.models;
import java.util.*;

// line 56 "../../../../../LibrarySystem.ump"
public class Citizen extends User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Citizen Attributes
  private boolean isLocal;
  private double balance;

  //Citizen Associations
  private Library library;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Citizen(String aFullName, int aCardID, String aAddress, String aUsername, String aPassword, boolean aOnlineAccountActivated, boolean aIsLocal, double aBalance, Library aLibrary)
  {
    super(aFullName, aCardID, aAddress, aUsername, aPassword, aOnlineAccountActivated);
    isLocal = aIsLocal;
    balance = aBalance;
    boolean didAddLibrary = setLibrary(aLibrary);
    if (!didAddLibrary)
    {
      throw new RuntimeException("Unable to create citizen due to library. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setIsLocal(boolean aIsLocal)
  {
    boolean wasSet = false;
    isLocal = aIsLocal;
    wasSet = true;
    return wasSet;
  }

  public boolean setBalance(double aBalance)
  {
    boolean wasSet = false;
    balance = aBalance;
    wasSet = true;
    return wasSet;
  }

  public boolean getIsLocal()
  {
    return isLocal;
  }

  public double getBalance()
  {
    return balance;
  }
  /* Code from template association_GetOne */
  public Library getLibrary()
  {
    return library;
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
      existingLibrary.removeCitizen(this);
    }
    library.addCitizen(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Library placeholderLibrary = library;
    this.library = null;
    if(placeholderLibrary != null)
    {
      placeholderLibrary.removeCitizen(this);
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "isLocal" + ":" + getIsLocal()+ "," +
            "balance" + ":" + getBalance()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "library = "+(getLibrary()!=null?Integer.toHexString(System.identityHashCode(getLibrary())):"null");
  }
}