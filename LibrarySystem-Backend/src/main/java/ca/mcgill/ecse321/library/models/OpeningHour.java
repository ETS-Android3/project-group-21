/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.library.models;

// line 38 "../../../../../LibrarySystem.ump"
public class OpeningHour
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //OpeningHour Associations
  private Library library;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public OpeningHour(Library aLibrary)
  {
    boolean didAddLibrary = setLibrary(aLibrary);
    if (!didAddLibrary)
    {
      throw new RuntimeException("Unable to create openingHour due to library. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
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
  /* Code from template association_SetOneToMandatoryMany */
  public boolean setLibrary(Library aLibrary)
  {
    boolean wasSet = false;
    //Must provide library to openingHour
    if (aLibrary == null)
    {
      return wasSet;
    }

    if (library != null && library.numberOfOpeningHours() <= Library.minimumNumberOfOpeningHours())
    {
      return wasSet;
    }

    Library existingLibrary = library;
    library = aLibrary;
    if (existingLibrary != null && !existingLibrary.equals(aLibrary))
    {
      boolean didRemove = existingLibrary.removeOpeningHour(this);
      if (!didRemove)
      {
        library = existingLibrary;
        return wasSet;
      }
    }
    library.addOpeningHour(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Library placeholderLibrary = library;
    this.library = null;
    if(placeholderLibrary != null)
    {
      placeholderLibrary.removeOpeningHour(this);
    }
  }

}