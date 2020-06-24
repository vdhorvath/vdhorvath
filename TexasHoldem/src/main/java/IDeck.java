import java.util.EmptyStackException;

/**
 * Stack Implementation of a Deck.
 */


public interface IDeck {


    /**
     * Tests if this Stack is empty.
     *
     * @return true if and only if the Stack contains no items. Otherwise return false.
     */

    boolean isEmpty();

    /**
     * Size of the Deck
     *
     * @return Number of cards in deck
     */


    Integer size();

    /**
     * Return the card at a particular index
     *
     * @return
     */

    Card cardAtPosition(Integer index);


    /**
     * Adds a Card to the Deck.
     *
     * @param card A Card push onto the Stack.
     */

    void addCard(Card card);

    /**
     * Returns Deck and removes the most recently-added item.
     *
     * @return The most recently-added item.
     * @throws EmptyStackException Throws an EmptyStackException if the method is called on an empty
     */


    Deck removeCard() throws EmptyStackException;

    /**
     * Returns the most recently-added item.
     *
     * @return The most recently-added item. (Show)
     * @throws EmptyStackException Throws an EmptyStackException if the method is called on an empty
     */

    Card showTopCard() throws EmptyStackException;


    /**
     * Populate the deck of cards.
     *
     * @return A full deck of cards.
     */


    void populateDeck();
}


