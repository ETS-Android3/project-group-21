package ca.mcgill.ecse321.library.models;

import java.util.*;

//line 13 "model.ump"
//line 49 "model.ump"
public class LibraryItem
{

//------------------------
// ENUMERATIONS
//------------------------

public enum ItemType { Book, Movie, Music, Newspaper, Archive }

//------------------------
// STATIC VARIABLES
//------------------------

private static Map<String, LibraryItem> libraryitemsByBarcode = new HashMap<String, LibraryItem>();

//------------------------
// MEMBER VARIABLES
//------------------------

//LibraryItem Attributes
private ItemType type;
private String title;
private String barcode;
private boolean isReservable;
private boolean isReservaed;
private int loanPeriod;

//LibraryItem Associations
private Reservation reservation;

//------------------------
// CONSTRUCTOR
//------------------------

public LibraryItem(ItemType aType, String aTitle, String aBarcode, boolean aIsReservable, boolean aIsReservaed, int aLoanPeriod, Reservation aReservation)
{
 type = aType;
 title = aTitle;
 isReservable = aIsReservable;
 isReservaed = aIsReservaed;
 loanPeriod = aLoanPeriod;
 if (!setBarcode(aBarcode))
 {
   throw new RuntimeException("Cannot create due to duplicate barcode. See http://manual.umple.org?RE003ViolationofUniqueness.html");
 }
 if (aReservation == null || aReservation.getLibraryItem() != null)
 {
   throw new RuntimeException("Unable to create LibraryItem due to aReservation. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
 }
 reservation = aReservation;
}

public LibraryItem(ItemType aType, String aTitle, String aBarcode, boolean aIsReservable, boolean aIsReservaed, int aLoanPeriod, int aReservationIDForReservation, User aUserForReservation)
{
 type = aType;
 title = aTitle;
 if (!setBarcode(aBarcode))
 {
   throw new RuntimeException("Cannot create due to duplicate barcode. See http://manual.umple.org?RE003ViolationofUniqueness.html");
 }
 isReservable = aIsReservable;
 isReservaed = aIsReservaed;
 loanPeriod = aLoanPeriod;
 reservation = new Reservation(aReservationIDForReservation, this, aUserForReservation);
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

public boolean setTitle(String aTitle)
{
 boolean wasSet = false;
 title = aTitle;
 wasSet = true;
 return wasSet;
}

public boolean setBarcode(String aBarcode)
{
 boolean wasSet = false;
 String anOldBarcode = getBarcode();
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

public boolean setIsReservable(boolean aIsReservable)
{
 boolean wasSet = false;
 isReservable = aIsReservable;
 wasSet = true;
 return wasSet;
}

public boolean setIsReservaed(boolean aIsReservaed)
{
 boolean wasSet = false;
 isReservaed = aIsReservaed;
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

public String getTitle()
{
 return title;
}

public String getBarcode()
{
 return barcode;
}
/* Code from template attribute_GetUnique */
public static LibraryItem getWithBarcode(String aBarcode)
{
 return libraryitemsByBarcode.get(aBarcode);
}
/* Code from template attribute_HasUnique */
public static boolean hasWithBarcode(String aBarcode)
{
 return getWithBarcode(aBarcode) != null;
}

public boolean getIsReservable()
{
 return isReservable;
}

public boolean getIsReservaed()
{
 return isReservaed;
}

public int getLoanPeriod()
{
 return loanPeriod;
}
/* Code from template association_GetOne */
public Reservation getReservation()
{
 return reservation;
}

public void delete()
{
 libraryitemsByBarcode.remove(getBarcode());
 Reservation existingReservation = reservation;
 reservation = null;
 if (existingReservation != null)
 {
   existingReservation.delete();
 }
}


public String toString()
{
 return super.toString() + "["+
         "title" + ":" + getTitle()+ "," +
         "barcode" + ":" + getBarcode()+ "," +
         "isReservable" + ":" + getIsReservable()+ "," +
         "isReservaed" + ":" + getIsReservaed()+ "," +
         "loanPeriod" + ":" + getLoanPeriod()+ "]" + System.getProperties().getProperty("line.separator") +
         "  " + "type" + "=" + (getType() != null ? !getType().equals(this)  ? getType().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
         "  " + "reservation = "+(getReservation()!=null?Integer.toHexString(System.identityHashCode(getReservation())):"null");
}
}
