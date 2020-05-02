import java.util.EmptyStackException;

public class Deck extends ADeck  {
  private Card card;
  private ADeck deck;

  /**
   * Creates an empty Deck of Cards
   * @param card - A Card
   * @param deck - A Suit
   */

  public Deck(Card card, Deck deck) {
    this.card = card;
    this.deck = deck;

  }


  /**
   * Tests if this Stack is empty.
   *
   * @return true if and only if the Stack contains no items. Otherwise return false.
   */

  
  @Override
  public boolean isEmpty() {
    return false;
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
    return null;
  }

  /**
   * Creates a non empty Deck.
   */



}
