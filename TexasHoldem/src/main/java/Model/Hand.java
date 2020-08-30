package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Hand  {
  private List<Card> hand;


  public Hand() {
    this.hand = new ArrayList<>();

  }


  public Integer size() {
    return this.hand.size();
  }

  /**
   * Getter for a Hand
   *
   * @return ArrayList for a Hand
   */

  public List<Card> getHand() {
    return this.hand;
  }


  /**
   * Add a Card to the hand
   *
   * @param card, being added to the hand
   */

  public void addCard(Card card) {
    this.hand.add(card);

  }

  /**
   * Getter method for a card in hand.
   *
   * @param cardIndex
   * @return
   */


  public Card getCards(Integer cardIndex) {
    return this.hand.get(cardIndex);

  }





  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Hand)) {
      return false;
    }
    Hand pokerHand1 = (Hand) o;
    return Objects.equals(hand, pokerHand1.hand);
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
