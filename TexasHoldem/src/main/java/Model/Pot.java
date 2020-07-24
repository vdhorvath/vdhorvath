package Model;

public class Pot {
  private Double pot = 0.0;

  public Pot(Double pot) {
    this.pot = pot;
  }



  public Double getPot() {
    return pot;
  }



  public Double addBet(Integer bet) {
    return this.pot + bet;

  }


}
