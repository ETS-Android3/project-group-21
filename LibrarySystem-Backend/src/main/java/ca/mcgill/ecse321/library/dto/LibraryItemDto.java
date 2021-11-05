package ca.mcgill.ecse321.library.dto;

import ca.mcgill.ecse321.library.models.LibraryItem.ItemType;

public class LibraryItemDto {

//	  public enum ItemType { Book, Music, Movie, Newspaper, Archive }

	  //LibraryItem Attributes
	  private ItemType type;
	  private Long barcode;  
	  private String title;
	  private boolean isReservable;
	  private boolean isReserved;
	  private int loanPeriod;

	  
	  public LibraryItemDto(Long barcode) {
		  this(barcode, ItemType.Book, "title", true, false, 21);
	  }
	  
	  public LibraryItemDto(Long barcode, ItemType type, String title, Boolean isReservable, Boolean isReserved, Integer loanPeriod) {
		  this.barcode = barcode;
		  this.title = title;
		  this.type = type; 
		  this.isReservable = isReservable;
		  this.isReserved = isReserved;
		  this.loanPeriod = loanPeriod;
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
