package View;


import Model.DeckOfCards;
import Model.Hand;



public class Table extends Hand {
  private Integer pot;
  private DeckOfCards currDeckOfCards;




  public Table() {
    super();
    this.pot = pot;


  }




  public Integer getPot() {
    return pot;
  }






  public void setPot(Integer pot) {
    this.pot = pot;
  }






  @Override
  public String toString() {
    return "Table {" +
        "pot =" + pot +
        ", currDeckOfCards = " + currDeckOfCards +
        '}';
  }
}
