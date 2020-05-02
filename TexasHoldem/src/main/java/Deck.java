import java.util.EmptyStackException;
import java.util.Objects;

public class Deck extends ADeck  {
  private Card card;
  private IDeck deck;

  /**
   * Creates an empty Deck of Cards
   * @param card - A Card
   * @param deck - A Suit
   */

  public Deck(Card card, IDeck deck) {
    this.card = card;
    this.deck = deck;

  }

  /**
   * Tests if this Stack is empty.
   *
   * @return true if and only if the Deck contains no Cards. Otherwise return false.
   */


  @Override
  public boolean isEmpty() {
    return false;
  }


  /**
   * Returns and removes the most recently-added item.
   *
   * @return The most recently-added item.
   * @throws EmptyStackException Throws an EmptyStackException if the method is called on an empty
   *                             Stack. Note: EmptyStackException is a built-in Java exception.
   */
  @Override
  public Deck popCard() throws EmptyStackException {
    if (this.deck.isEmpty()) {
      throw new IllegalArgumentException("Empty Deck (pop)");
    } else {
      return new Deck(this.card, this.deck.popCard());
    }
  }

  /**
   * Returns the most recently-added item.
   *
   * @return The most recently-added item. (Show)
   * @throws EmptyStackException Throws an EmptyStackException if the method is called on an empty
   *                             Stack. Note: EmptyStackException is a built-in Java exception.
   */


  @Override
  public Card showCard() throws EmptyStackException {
    if (this.deck.isEmpty()) {
      throw new IllegalArgumentException("There are no cards in this deck");
    } else
    return this.deck.showCard();
  }


  /**
   * Populate the deck of cards.
   *
   * @return A full deck of cards.
   */
  @Override
  public Deck populateDeck() {
    return null;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Deck)) {
      return false;
    }
    Deck deck1 = (Deck) o;
    return card.equals(deck1.card) &&
        deck.equals(deck1.deck);
  }

  @Override
  public int hashCode() {
    return Objects.hash(card, deck);
  }
}



