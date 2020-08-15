package Controller;

import Model.Hand;
import Model.NoCashException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;


public class Players extends Hand {
  private static AtomicInteger playerNumberGen = new AtomicInteger(1);
  private double purse = 10000.00;
  private Integer playerNumber = 1;
  private Integer choice = 0;




  public Players() {
    super();
    this.purse = getPurse();
    this.playerNumber = this.playerNumberGen.getAndIncrement();
    this.choice = 0;



  }

  protected static void setPlayerNumber(int startingInt) {
    Players.playerNumberGen = new AtomicInteger(startingInt);
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


  public double betOrCheck(Double currBet) throws NoCashException {
    if (this.purse - currBet > 0) {
      System.out.println(this.purse);
      return this.purse - currBet;

    }
    throw new NoCashException();


  }

  public Integer getChoice() {
    return this.choice;
  }




  public void updateChoice(Integer newChoice) {
    this.choice = newChoice;
  }




  public String toCustomString() {
    return "Player = " + getPlayerNum() + " " +
        "purse = " + purse +
        '}';
  }




  @Override
  public String toString() {
    return "Player = " + getPlayerNum() + getHand();
  }








}









