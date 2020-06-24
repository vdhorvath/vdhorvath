import java.util.EmptyStackException;

public class EmptyDeck implements IDeck {

  /**
   * Test of the Deck is Empty
   *
   * @return True, for the empty Deck class
   */

  @Override
  public boolean isEmpty() {
    return true;
  }

  /**
   * Size of the Deck
   *
   * @return 0, representing no cards in the deck.
   */


  @Override
  public Integer size() {
    return 0;
  }

  /**
   * Return the card at a particular index
   *
   * @param index
   * @throws IllegalArgumentException, for indexes that are out of range.
   */


  @Override
  public Card cardAtPosition(Integer index) throws IndexOutOfBoundsException {
    throw new IndexOutOfBoundsException("There are no cards at this position");

  }

  /**
   * Adds a Card to the Deck.
   *
   * @param card, pushes card onto the Deck.
   */


  @Override
  public void addCard(Card card) {
    return;
  }


  /**
   * Returns Deck and removes the most recently-added item.
   *
   * @return The most recently-added item.
   * @throws EmptyStackException Throws an EmptyStackException if the method is called on an empty
   */


  @Override
  public Deck removeCard() throws EmptyStackException {
    throw new IllegalArgumentException("Empty Deck!");
  }

  /**
   * Returns the most recently-added item.
   *
   * @return The most recently-added item. (Show)
   * @throws EmptyStackException Throws an EmptyStackException if the method is called on an empty
   */

  @Override
  public Card showTopCard() throws EmptyStackException {
    throw new IllegalArgumentException("Empty Deck");
  }


  /**
   * Populate the deck of cards.
   *
   * @return A full deck of cards.
   */


  @Override
  public void populateDeck() throws EmptyStackException {
    throw new EmptyStackException();
  }

}


