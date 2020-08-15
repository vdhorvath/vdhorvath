package View;


import Model.DeckOfCards;
import Model.Hand;
import Model.NoCashException;
import Model.TexasHoldem;
import java.util.ArrayList;

public class Table extends Hand {
  private TexasHoldem texasHoldemMinimum;
  private Double pot = 0.0;
  private DeckOfCards currDeckOfCards;




  public Table() {
    super();
    this.pot = pot;


  }


  public Double getPot() {
    return pot;
  }





  public Double addBetsToPot(Double pot)
  {
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
