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

  /**
   * Compares this object with the specified object for order.  Returns a negative integer, zero, or
   * a positive integer as this object is less than, equal to, or greater than the specified
   * object.
   *
   * @param other the object to be compared.
   * @return a negative integer, zero, or a positive integer as this object is less than, equal to,
   * or greater than the specified object.
   * @throws NullPointerException if the specified object is null
   * @throws ClassCastException   if the specified object's type prevents it from being compared to
   *                              this object.
   */


  public int compareTo(Card other) {
    CardComparator cardComparator = new CardComparator();
    return cardComparator.compare(this, other);

  }


  /**
   * Compares its two arguments for Suite.  Returns one integer or zero,
   *
   * the first object to be compared.
   * @param other the second object to be compared.
   * @return a one if equal, zero, otherwise.
   * equal to, or greater than the second.
   * @throws NullPointerException if an argument is null and this comparator does not permit null
   *                              arguments
   * @throws ClassCastException   if the arguments' types prevent them from being compared by this
   *                              comparator.
   */

  public int compareSuit(Card other) {
    CardComparator cardComparator = new CardComparator();
    return cardComparator.compareSuit(this, other);

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
    return "Card{" +
        "suit=" + suit +
        ", value=" + value +
        '}';
  }
}



