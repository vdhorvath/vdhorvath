package Model;

import java.util.ArrayList;
import java.util.Objects;


public abstract class Hand  {
  private ArrayList<Card> hand = new ArrayList<>();


  public Hand()  {
    this.hand = new ArrayList<>();


  }

  /**
   * Getter for a Hand
   * @return ArrayList for a Hand
   */

  public ArrayList<Card> getHand() {
    return this.hand;
  }


  /**
   * Add a Card to the hand
   * @param card, being added to the hand
   */

  public void addCard(Card card) {
    this.hand.add(card);

  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Hand)) {
      return false;
    }
    Hand hand1 = (Hand) o;
    return Objects.equals(hand, hand1.hand);
  }

  @Override
  public int hashCode() {
    return Objects.hash(hand);
  }

  @Override
  public String toString() {
    return "Hand{" +
        "hand=" + hand +
        '}';
  }
}
