/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.library.models;
import java.util.*;

import javax.persistence.*;

@Entity
@Table(name = "citizen")
public class Citizen extends ApplicationUser
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Citizen Attributes
  private boolean isLocal;
  private double balance;


  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Citizen() {
	  
  }

  //------------------------
  // INTERFACE
  //------------------------

  public void setIsLocal(boolean aIsLocal)
  {
    this.isLocal = aIsLocal;
  }

  public void setBalance(double aBalance)
  {
    this.balance = aBalance;
  }

  public boolean getIsLocal()
  {
    return this.isLocal;
  }

  public double getBalance()
  {
    return this.balance;
  }
}