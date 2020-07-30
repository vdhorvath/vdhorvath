package Controller;


import Model.Hand;
import Model.NoCashException;
import java.util.concurrent.atomic.AtomicInteger;


public class Players extends Hand {
  private static AtomicInteger playerNumberGen = new AtomicInteger(1);
  private double purse = 10000.00;
  private Integer playerNumber = 1;




  public Players() {
    super();
    this.purse = getPurse();
    this.playerNumber = this.playerNumberGen.getAndIncrement();

  }

  protected static void setPlayerNumber(int startingInt) {
    Players.playerNumberGen = new AtomicInteger(startingInt);
  }



  public void check() {
    return;

  }

  public Double getPurse() {
    return this.purse;

  }

  public Integer getPlayerNum() {
    return this.playerNumber;
  }



  public void fold() {
    int i = 0;
    while (i < this.getHand().size()) {
      this.getHand().remove(this.getHand().size() - 1);
    }


  }


  public double bet(Double currBet) throws NoCashException {
    if (this.purse - currBet > 0) {
      System.out.println(currBet);
      return this.purse - currBet;

    }
    throw new NoCashException();


  }



  public String toCustomString() {
    return "Player = " + getPlayerNum() + " " +
        "purse = " + purse +
        '}';
  }




  @Override
  public String toString() {
    return "Player = " + getPlayerNum() + getHand() +
        "purse=" + purse +
        '}';
  }








}









