package ca.mcgill.ecse321.library.models;

// line 11 "model.ump"
// line 29 "model.ump"
public class Citizen extends User
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

  public Citizen(String aFullName, int aCardId, String aAdress, String aUsername, String aPassword, boolean aOnlineAccountActivated, boolean aIsLocal, double aBalance)
  {
    super(aFullName, aCardId, aAdress, aUsername, aPassword, aOnlineAccountActivated);
    isLocal = aIsLocal;
    balance = aBalance;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setIsLocal(boolean aIsLocal)
  {
    boolean wasSet = false;
    isLocal = aIsLocal;
    wasSet = true;
    return wasSet;
  }

  public boolean setBalance(double aBalance)
  {
    boolean wasSet = false;
    balance = aBalance;
    wasSet = true;
    return wasSet;
  }

  public boolean getIsLocal()
  {
    return isLocal;
  }

  public double getBalance()
  {
    return balance;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isIsLocal()
  {
    return isLocal;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "isLocal" + ":" + getIsLocal()+ "," +
            "balance" + ":" + getBalance()+ "]";
  }
  //method
  public void pay(){
    //todo
  }
}
