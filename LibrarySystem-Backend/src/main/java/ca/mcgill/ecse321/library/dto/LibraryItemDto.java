package ca.mcgill.ecse321.library.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import ca.mcgill.ecse321.library.models.LibraryItem.ItemType;

public class LibraryItemDto {
	
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
	  
	  public LibraryItemDto() {
		  
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

	  public Long getBarcode()
	  {
	    return this.barcode;
	  }

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
