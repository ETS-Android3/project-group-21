/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.library.models;
import java.util.*;

import javax.persistence.*;

@Entity
@Table(name = "libraryitem")
public class LibraryItem
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum ItemType { Book, Music, Movie, Newspaper, Archive }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //LibraryItem Attributes
  private ItemType type;
  private Long barcode;
  private String title;
  private boolean isReservable;
  private boolean isReserved;
  private int loanPeriod;


  //------------------------
  // CONSTRUCTOR
  //------------------------
  
  public LibraryItem() {
	  
  }

  //------------------------
  // INTERFACE
  //------------------------

  public void setType(ItemType aType)
  {
    this.type = aType;
  }

  public void setBarcode(Long aBarcode){
	  this.barcode = aBarcode;
  }

  public void setTitle(String aTitle)
  {
    this.title = aTitle;
  }

  public void setIsReservable(boolean aIsReservable)
  {
    this.isReservable = aIsReservable;
  }

  public void setIsReserved(boolean aIsReserved)
  {
    this.isReserved = aIsReserved;
  }

  public void setLoanPeriod(int aLoanPeriod)
  {
    this.loanPeriod = aLoanPeriod;
  }

  public ItemType getType()
  {
    return this.type;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public Long getBarcode()
  {
    return this.barcode;
  }
  
//  /* Code from template attribute_GetUnique */
//  public static LibraryItem getWithBarcode(Long aBarcode)
//  {
//    return libraryitemsByBarcode.get(aBarcode);
//  }
//  /* Code from template attribute_HasUnique */
//  public static boolean hasWithBarcode(Long aBarcode)
//  {
//    return getWithBarcode(aBarcode) != null;
//  }

  public String getTitle()
  {
    return this.title;
  }

  public boolean getIsReservable()
  {
    return this.isReservable;
  }

  public boolean getIsReserved()
  {
    return this.isReserved;
  }

  public int getLoanPeriod()
  {
    return this.loanPeriod;
  }

}