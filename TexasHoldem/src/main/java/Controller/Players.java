package Controller;


import Model.Hand;
import Model.NoCashException;


public class Players extends Hand {

  private final int playerNumber = 0;
  private Double purse = 100000.00;


  public Players() {
    super();
    this.purse = getPurse();
    this.playerNumber =



  }



  public void check() {
    return;

  }

  public Double getPurse() {
    return this.purse;

  }

  public Integer getPlay() {
    return getPNumberGen();
  }



  public void fold() {
    int i = 0;
    while (i < this.getHand().size()) {
      this.getHand().remove(this.getHand().size() - 1);
    }


  }


  public Double bet(Double amount) throws NoCashException {
    if (this.purse - amount > 0) {
      return this.purse - amount;
    }
    throw new NoCashException();


  }

  public String toCustomString() {
    return "Player = " + getPNumberGen() + " " +
        "purse=" + purse +
        '}';
  }




  @Override
  public String toString() {
    return "Player = " + getPNumberGen() + getHand() +
        "purse=" + purse +
        '}';
  }








}









