package Controller;

import Model.Card;
import java.util.ArrayList;



public class Players {
  private ArrayList<Card> hand;


  public Players() {
    this.hand = new ArrayList<>();
  }


  public ArrayList<Card> getCards() {
    return this.hand;

  }


  public void addCard(Card card) {
    this.hand.add(card);
  }






  @Override
  public String toString() {
    return "Players{" +
        "hand=" + hand +
        '}';
  }
}









