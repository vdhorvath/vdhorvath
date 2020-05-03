import java.util.EmptyStackException;
import java.util.List;


public interface IDeck {


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

    void addCard(Card card);

    /**
     * Returns and removes the most recently-added item.
     * @return The most recently-added item.
     * @throws EmptyStackException Throws an EmptyStackException
     * if the method is called on an empty
     */


    Deck removeCard() throws EmptyStackException;

    /**
     * Returns the most recently-added item.
     *
     * @return The most recently-added item. (Show)
     * @throws EmptyStackException Throws an EmptyStackException
     * if the method is called on an empty
     *
     */

    Card showCard() throws EmptyStackException;

    /**
     * Populate the deck of cards.
     * @return A full deck of cards.
     */


    void populateDeck();


}