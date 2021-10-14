/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.library.models;

// line 36 "../../../../../LibrarySystem.ump"
public class Citizen
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Citizen Associations
  private Library library;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Citizen(Library aLibrary)
  {
    boolean didAddLibrary = setLibrary(aLibrary);
    if (!didAddLibrary)
    {
      throw new RuntimeException("Unable to create citizen due to library. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
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
  }

}