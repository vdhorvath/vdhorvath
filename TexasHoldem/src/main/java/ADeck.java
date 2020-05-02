import java.util.EmptyStackException;

public abstract class ADeck implements IDeck {


  /**
   * Adds a Card to the Stack.
   *
   * @param card A Card push onto the Stack.
   */
  @Override
  public Deck addCard(Card card) {
    return new Deck(card, this);
  }

  /**
   * Returns and removes the most recently-added item.
   *
   * @return The most recently-added item.
   * @throws EmptyStackException Throws an EmptyStackException if the method is called on an empty
   *                             Stack. Note: EmptyStackException is a built-in Java exception.
   */
  @Override
  public Card popCard() throws EmptyStackException {
    return popCard();
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

}
