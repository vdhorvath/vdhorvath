package Model;

import java.util.Scanner;

public class Bet {
  private Double bigBlind = 1000.0;
  private Double smallBlind = bigBlind / 2;
  private Double betAmount;


  public Bet(Double smallBlind, Double bigBlind, Double betAmount) {
    this.smallBlind = smallBlind;
    this.bigBlind = bigBlind;
    this.betAmount = betAmount;
  }



  public Double getBigBlind() {
    return bigBlind;
  }


  public void updateBigBlind(Double bigBlind) {
    this.bigBlind = bigBlind;

  }



  /**
   * Get players bet amount;
   * @return a double, players bet amount.
   * @throws MinBetException
   */


  public Double askForBetAmt() throws MinBetException {
    Scanner scanner = new Scanner(System.in);
    System.out.println("_____________________________");
    System.out.println("_____________________________");
    System.out.println(String
        .format("How much would you like bet? Your bet must be a minimum of %f", this.bigBlind));
    Double amount = scanner.nextDouble();

    if (amount < this.bigBlind) {
      throw new MinBetException();
    } return amount;

  }




   /* if (amount == this.bigBlind) {
    this.currRoundPot += amount;
    return amount;

  } else if (amount > this.bigBlind) {
    this.currRoundPot += amount;
    return updateBigBlind(amount);
  }

*/








  public Double getSmallBlind() {
    return smallBlind;
  }



  public void setSmallBlind(Double smallBlind) {
    this.smallBlind = smallBlind;
  }


  public Double getBetAmount() {
    return betAmount;
  }

  public void setBetAmount(Double betAmount) {
    this.betAmount = betAmount;
  }
}
