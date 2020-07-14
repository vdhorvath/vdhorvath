import java.util.EmptyStackException;

public class EmptyDeckOfCards implements Stack {

  public EmptyDeckOfCards() {

  }

  /**
   * Test of the DeckOfCards is Empty
   *
   * @return True, for the empty DeckOfCards class
   */

  @Override
  public boolean isEmpty() {
    return true;
  }

  /**
   * Size of the DeckOfCards
   *
   * @return 0, representing no cards in the deck.
   */


  @Override
  public Integer size() {
    return 0;
  }


  /**
   * Adds a Card to the DeckOfCards.
   *
   * @param card, pushes card onto the DeckOfCards.
   */


  @Override
  public void addCard(Card card) {
    return;
  }


  /**
   * Returns nothing - Empty DeckOfCards
   *
   * @return The most recently-added item.
   * @throws EmptyStackException Throws an EmptyStackException if the method is called on an empty
   */


  @Override
  public Card popCard() throws EmptyStackException {
    throw new IllegalArgumentException("Empty DeckOfCards!");
  }

  /**
   * Returns the most recently-added item.
   *
   * @return The most recently-added item. (Show)
   * @throws EmptyStackException Throws an EmptyStackException if the method is called on an empty
   */

  @Override
  public Card showTopCard() throws EmptyStackException {
    throw new IllegalArgumentException("Empty DeckOfCards");
  }


  @Override
  public String toString() {
    return "EmptyDeckOfCards{}";
  }
}


