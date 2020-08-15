package Model;

import java.util.EmptyStackException;

/**
 * Model.IStack Implementation of a Model.DeckOfCards.
 */


public interface IStack {


    /**
     * Tests if this Model.IStack is empty.
     *
     * @return true if and only if the Model.IStack contains no items. Otherwise return false.
     */

    boolean isEmpty();

    /**
     * Size of the Model.DeckOfCards
     *
     * @return Number of cards in deck
     */


    Integer size();


    /**
     * Adds a Model.Card to the Model.DeckOfCards.
     *
     * @param card A Model.Card push onto the Model.IStack.
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


