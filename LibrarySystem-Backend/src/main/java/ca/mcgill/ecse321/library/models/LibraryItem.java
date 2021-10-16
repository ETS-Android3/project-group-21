/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.library.models;
import java.util.*;

// line 22 "../../../../../LibrarySystem.ump"
public class LibraryItem
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum ItemType { Book, Music, Movie, Newspaper, Archive }

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<Integer, LibraryItem> libraryitemsByBarcode = new HashMap<Integer, LibraryItem>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //LibraryItem Attributes
  private ItemType type;
  private int barcode;
  private String title;
  private boolean isReservable;
  private boolean isReserved;
  private int loanPeriod;

  //LibraryItem Associations
  private Library library;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public LibraryItem(ItemType aType, int aBarcode, String aTitle, boolean aIsReservable, boolean aIsReserved, int aLoanPeriod, Library aLibrary)
  {
    type = aType;
    title = aTitle;
    isReservable = aIsReservable;
    isReserved = aIsReserved;
    loanPeriod = aLoanPeriod;
    if (!setBarcode(aBarcode))
    {
      throw new RuntimeException("Cannot create due to duplicate barcode. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    boolean didAddLibrary = setLibrary(aLibrary);
    if (!didAddLibrary)
    {
      throw new RuntimeException("Unable to create libraryitem due to library. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setType(ItemType aType)
  {
    boolean wasSet = false;
    type = aType;
    wasSet = true;
    return wasSet;
  }

  public boolean setBarcode(int aBarcode)
  {
    boolean wasSet = false;
    Integer anOldBarcode = getBarcode();
    if (anOldBarcode != null && anOldBarcode.equals(aBarcode)) {
      return true;
    }
    if (hasWithBarcode(aBarcode)) {
      return wasSet;
    }
    barcode = aBarcode;
    wasSet = true;
    if (anOldBarcode != null) {
      libraryitemsByBarcode.remove(anOldBarcode);
    }
    libraryitemsByBarcode.put(aBarcode, this);
    return wasSet;
  }

  public boolean setTitle(String aTitle)
  {
    boolean wasSet = false;
    title = aTitle;
    wasSet = true;
    return wasSet;
  }

  public boolean setIsReservable(boolean aIsReservable)
  {
    boolean wasSet = false;
    isReservable = aIsReservable;
    wasSet = true;
    return wasSet;
  }

  public boolean setIsReserved(boolean aIsReserved)
  {
    boolean wasSet = false;
    isReserved = aIsReserved;
    wasSet = true;
    return wasSet;
  }

  public boolean setLoanPeriod(int aLoanPeriod)
  {
    boolean wasSet = false;
    loanPeriod = aLoanPeriod;
    wasSet = true;
    return wasSet;
  }

  public ItemType getType()
  {
    return type;
  }

  public int getBarcode()
  {
    return barcode;
  }
  /* Code from template attribute_GetUnique */
  public static LibraryItem getWithBarcode(int aBarcode)
  {
    return libraryitemsByBarcode.get(aBarcode);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithBarcode(int aBarcode)
  {
    return getWithBarcode(aBarcode) != null;
  }

  public String getTitle()
  {
    return title;
  }

  public boolean getIsReservable()
  {
    return isReservable;
  }

  public boolean getIsReserved()
  {
    return isReserved;
  }

  public int getLoanPeriod()
  {
    return loanPeriod;
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
      existingLibrary.removeLibraryitem(this);
    }
    library.addLibraryitem(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    libraryitemsByBarcode.remove(getBarcode());
    Library placeholderLibrary = library;
    this.library = null;
    if(placeholderLibrary != null)
    {
      placeholderLibrary.removeLibraryitem(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "barcode" + ":" + getBarcode()+ "," +
            "title" + ":" + getTitle()+ "," +
            "isReservable" + ":" + getIsReservable()+ "," +
            "isReserved" + ":" + getIsReserved()+ "," +
            "loanPeriod" + ":" + getLoanPeriod()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "type" + "=" + (getType() != null ? !getType().equals(this)  ? getType().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "library = "+(getLibrary()!=null?Integer.toHexString(System.identityHashCode(getLibrary())):"null");
  }
}