package ca.mcgill.ecse321.library.models;

public class LibraryItem {
   
  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum ItemType { Book, Movie, Music, Newspaper, Archive }
   
   // Add any code that gets generated for library item after this!

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //LibraryItem Attributes
  private ItemType type;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public LibraryItem(ItemType aType)
  {
    type = aType;
  }

    
}
