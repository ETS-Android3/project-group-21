/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.library.models;

// line 32 "../../../../../LibrarySystem.ump"
public class HeadLibrarian
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //HeadLibrarian Associations
  private Library library;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public HeadLibrarian(Library aLibrary)
  {
    if (aLibrary == null || aLibrary.getHeadLibrarian() != null)
    {
      throw new RuntimeException("Unable to create HeadLibrarian due to aLibrary. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    library = aLibrary;
  }

  public HeadLibrarian()
  {
    library = new Library(this);
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public Library getLibrary()
  {
    return library;
  }

  public void delete()
  {
    Library existingLibrary = library;
    library = null;
    if (existingLibrary != null)
    {
      existingLibrary.delete();
    }
  }

}