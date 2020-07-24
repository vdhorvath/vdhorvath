package View;


import Model.DeckOfCards;
import Model.Hand;
import Model.Pot;


public class Table extends Hand {
  private Pot pot;
  private DeckOfCards currDeckOfCards;


  public Table() {
    super();
    this.pot = pot;
    this.currDeckOfCards = new DeckOfCards();

  }

  public Pot getPot() {
    return pot;
  }

  public DeckOfCards getCurrDeckOfCards() {
    return currDeckOfCards;
  }

}
