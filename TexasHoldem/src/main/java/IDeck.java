import java.util.EmptyStackException;


  public interface IDeck {



    /**
     * Create an empty deck of Cards.
     *
     * @return and empty deck of Cards.
     */

    static IDeck createDeck() {
      return new EmptyDeckStack();
    }


    /**
     * Tests if this Stack is empty.
     *
     * @return true if and only if the Stack contains no items. Otherwise return false.
     */

    boolean isEmpty();


    /**
     * Adds a Card to the Stack.
     *
     * @param card A Card push onto the Stack.
     */

    Deck addCard(Card card);

    /**
     * Returns and removes the most recently-added item.
     * @return The most recently-added item.
     * @throws EmptyStackException Throws an EmptyStackException if the method is called on an empty
     *                             Stack. Note: EmptyStackException is a built-in Java exception.
     */


    Deck popCard() throws EmptyStackException;

    /**
     * Returns the most recently-added item.
     *
     * @return The most recently-added item. (Show)
     * @throws EmptyStackException Throws an EmptyStackException if the method is called on an empty
     *                             Stack. Note: EmptyStackException is a built-in Java exception.
     */

    Card showCard() throws EmptyStackException;

    /**
     * Populate the deck of cards.
     * @return A full deck of cards.
     */


    Deck populateDeck();


}