package Controller;


import Model.Card;
import Model.NoCashException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;




public class Players {

  private static AtomicInteger playerNumberGen = new AtomicInteger(1);
  private Double purse = 10000.00;
  private Integer playerNumber = 1;
  private Integer choice = 0;
  private boolean raiseFlag;
  private ArrayList<Card> hand;


  /**
   * Constructor for a player
   *
   * @param hand
   */


  public Players(ArrayList<Card> hand) {
    this.playerNumber = this.playerNumberGen.getAndIncrement();
    this.purse = purse;
    this.choice = 0;
    this.raiseFlag = false;
    this.hand = new ArrayList<>();

  }

  public Players() {
    this.playerNumber = this.playerNumberGen.getAndIncrement();
    this.purse = purse;
    this.choice = 0;
    this.raiseFlag = false;
    this.hand = new ArrayList<>();
  }




  public ArrayList<Card> getHand() {
    return hand;
  }


  /**
   * Setter for the player number to update and change with every new player that is created
   *
   * @param startingInt
   */

  protected static void setPlayerNumber(int startingInt) {
    Players.playerNumberGen = new AtomicInteger(startingInt);
  }


  /**
   * Getter for a Player's wallet.
   *
   * @return
   */

  public Double getPurse() {
    return this.purse;

  }

  /**
   * Setter for a Player's raise flag. Set to True
   */


  public void updateRaiseFlagTrue() {
    this.raiseFlag = true;

  }

  /**
   * Setter for a Player's raise flag. Set to False
   */

  public void updateRaiseFlagFalse() {
    this.raiseFlag = false;

  }

  /**
   * Getter for a Player's number
   *
   * @return Integer
   */


  public Integer getPlayerNum() {
    return this.playerNumber;
  }


  /**
   * Getter for a Player's raise flag.
   *
   * @return
   */

  public boolean getRaiseFlag() {
    return this.raiseFlag;
  }

  /**
   * Bet or Check, subtract a player's amount from the purse.
   *
   * @param currBet
   * @return
   * @throws NoCashException
   */

  public double betOrCheck(Double currBet) throws NoCashException {
    if (this.purse - currBet > 0) {
      this.purse = this.purse - currBet;
      return this.purse;

    }
    throw new NoCashException();


  }

  /**
   * Getter for a Player's choice.
   *
   * @return Integer for a player's choice.
   */

  public Integer getChoice() {
    return this.choice;
  }

  /**
   * Setter to update a player's choice,
   *
   * @param newChoice
   */


  public void updateChoice(Integer newChoice) {
    this.choice = newChoice;
  }


  /**
   * Custom toString for a player
   *
   * @return String, player, player's number and their purse.
   */

  public String toCustomString() {
    return "Player = " + getPlayerNum() + " " +
        "purse = " + purse +
        '}';
  }







  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Players)) {
      return false;
    }
    Players players = (Players) o;
    return Double.compare(players.purse, purse) == 0 &&
        raiseFlag == players.raiseFlag &&
        Objects.equals(playerNumber, players.playerNumber) &&
        Objects.equals(choice, players.choice);
  }

  @Override
  public int hashCode() {
    return Objects.hash(purse, playerNumber, choice, raiseFlag);
  }


  @Override
  public String toString() {
    return "Player = " + playerNumber +
        " hand = " + hand +
        '}';
  }
}









