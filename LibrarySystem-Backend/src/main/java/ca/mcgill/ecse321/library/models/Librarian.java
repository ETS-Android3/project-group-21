/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.library.models;
import java.util.*;

import javax.persistence.*;

@Entity
@Table(name = "librarian")
public class Librarian extends User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Librarian Associations
  private List<Shift> shift;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Librarian() {
	  
  }

  //------------------------
  // INTERFACE
  //------------------------

  public void setShift(List<Shift> aShift)
  {
    this.shift = aShift;
  }

  @ManyToMany
  public List<Shift> getShift()
  {
    return this.shift;
  }
  

}