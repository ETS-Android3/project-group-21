package ca.mcgill.ecse321.library.models;
public class Shift
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum DayofWeek { Monday, Tuesday, Wednesday, Thursday, Friday }
  
  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Shift Associations
  private DayofWeek day;
  private HeadLibrarian headLibrarian;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Shift(HeadLibrarian aHeadLibrarian, DayofWeek aDay)
  {
    day = aDay;
    boolean didAddHeadLibrarian = setHeadLibrarian(aHeadLibrarian);
    if (!didAddHeadLibrarian)
    {
      throw new RuntimeException("Unable to create shift due to headLibrarian. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public HeadLibrarian getHeadLibrarian()
  {
    return headLibrarian;
  }
  /* Code from template association_SetOneToMany */
  public boolean setHeadLibrarian(HeadLibrarian aHeadLibrarian)
  {
    boolean wasSet = false;
    if (aHeadLibrarian == null)
    {
      return wasSet;
    }

    HeadLibrarian existingHeadLibrarian = headLibrarian;
    headLibrarian = aHeadLibrarian;
    if (existingHeadLibrarian != null && !existingHeadLibrarian.equals(aHeadLibrarian))
    {
      existingHeadLibrarian.removeShift(this);
    }
    headLibrarian.addShift(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    HeadLibrarian placeholderHeadLibrarian = headLibrarian;
    this.headLibrarian = null;
    if(placeholderHeadLibrarian != null)
    {
      placeholderHeadLibrarian.removeShift(this);
    }
  }

}

