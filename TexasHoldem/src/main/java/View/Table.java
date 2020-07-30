package View;


import Model.DeckOfCards;
import Model.Hand;



public class Table extends Hand {
  private Double pot = 0.0;
  private DeckOfCards currDeckOfCards;




  public Table() {
    super();
    this.pot = pot;


  }


  public Double getPot() {
    return pot;
  }





  public Double addBetsToPot(Double pot) {
    return this.pot += pot;
  }






  @Override
  public String toString() {
    return "Table {" +
        "pot =" + pot +
        ", currDeckOfCards = " + currDeckOfCards +
        '}';
  }
}
