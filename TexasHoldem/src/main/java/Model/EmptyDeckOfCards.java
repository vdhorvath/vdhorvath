package Model;

import Model.Card;
import java.util.EmptyStackException;

public class EmptyDeckOfCards extends ADeckOfCards {

  public EmptyDeckOfCards() {

  }

  /**
   * Test of the Model.DeckOfCards is Empty
   *
   * @return True, for the empty Model.DeckOfCards class
   */

  @Override
  public boolean isEmpty() {
    return true;
  }

  /**
   * Size of the Model.DeckOfCards
   *
   * @return 0, representing no cards in the deck.
   */


  @Override
  public Integer size() {
    return 0;
  }


  /**
   * Returns nothing - Empty Model.DeckOfCards
   *
   * @return The most recently-added item.
   * @throws EmptyStackException Throws an EmptyStackException if the method is called on an empty
   */


  @Override
  public Card popCard() throws EmptyStackException {
    throw new IllegalArgumentException("Empty Model.DeckOfCards!");
  }

  /**
   * Returns the most recently-added item.
   *
   * @return The most recently-added item. (Show)
   * @throws EmptyStackException Throws an EmptyStackException if the method is called on an empty
   */

  @Override
  public Card showTopCard() throws EmptyStackException {
    throw new IllegalArgumentException("Empty Model.DeckOfCards");
  }


  @Override
  public String toString() {
    return "Model.EmptyDeckOfCards{}";
  }
}


