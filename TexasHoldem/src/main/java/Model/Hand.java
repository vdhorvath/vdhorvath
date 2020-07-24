package Model;

import java.util.ArrayList;



public class Hand  {
  private ArrayList<Card> hand = new ArrayList<>();


  public Hand()  {
    this.hand = new ArrayList<>();


  }

  public ArrayList<Card> getHand() {
    return this.hand;
  }


  public void addCard(Card card) {
    this.hand.add(card);

  }



  @Override
  public String toString() {
    return "Hand{" +
        "hand=" + hand +
        '}';
  }
}
