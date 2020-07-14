import java.util.EmptyStackException;

/**
 * Stack Implementation of a DeckOfCards.
 */


public interface Stack {


    /**
     * Tests if this Stack is empty.
     *
     * @return true if and only if the Stack contains no items. Otherwise return false.
     */

    boolean isEmpty();

    /**
     * Size of the DeckOfCards
     *
     * @return Number of cards in deck
     */


    Integer size();


    /**
     * Adds a Card to the DeckOfCards.
     *
     * @param card A Card push onto the Stack.
     */

    void addCard(Card card);

    /**
     * Returns and removes the most recently-added item.
     *
     * @return The most recently-added item.
     * @throws EmptyStackException Throws an EmptyStackException if the method is called on an empty
     */


    Card popCard() throws EmptyStackException;

    /**
     * Returns the most recently-added item.
     *
     * @return The most recently-added item. (Show)
     * @throws EmptyStackException Throws an EmptyStackException if the method is called on an empty
     */

    Card showTopCard() throws EmptyStackException;

}


