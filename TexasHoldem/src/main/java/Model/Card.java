package Model;

import java.util.Objects;

/**
 * Class for the Model.Card
 */

public class Card implements Comparable<Card> {
  private Suit suit;
  private Value value;


  /**
   * Model.Card Constructor
   * @param suit
   * @param value
   */

  public Card(Suit suit, Value value) {
    this.suit = suit;
    this.value = value;

  }

  /**
   * Get suit of the Model.Card.
   * @return suit
   */


  public Suit getSuit() {
    return this.suit;
  }

  /**
   * Get the Model.Value of the Model.Card.
   * @return value
   */


  public Value getValue() {
    return this.value;

  }



  public int compareTo(Card c) {
    return this.getValue().getValue() - c.getValue().getValue();
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Card)) {
      return false;
    }
    Card card = (Card) o;
    return suit == card.suit &&
        value == card.value;
  }

  @Override
  public int hashCode() {
    return Objects.hash(suit, value);
  }

  @Override
  public String toString() {
    return "<"+ value + " " + "of" + " " + suit +">";
  }
}



