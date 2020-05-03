import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;



public class Deck implements IDeck  {
  private List<Card> deck;


  public Deck() {
    this.deck = new ArrayList<>();
    this.populateDeck();
  }


  public List<Card> getDeck() {
    return this.deck;
  }

  /**
   * Tests if this Stack is empty.
   *
   * @return true if and only if the Deck contains no Cards. Otherwise return false.
   */


  @Override
  public boolean isEmpty() {
    return false;
  }


  /**
   * Returns and removes the most recently-added item.
   *
   * @return The most recently-added item.
   * @throws EmptyStackException Throws an EmptyStackException if the method is called on an empty
   *                             Stack. Note: EmptyStackException is a built-in Java exception.
   */


  @Override
  public Deck removeCard() throws EmptyStackException {
    if (this.deck.isEmpty()) {
      throw new IllegalArgumentException("Empty Deck (pop)");
    } else {
      return new Deck().removeCard();
    }
  }


  /**
   * Adds a Card to the Stack
   * @param card A Card push onto the Stack.
   */


  @Override
  public void addCard(Card card) {
    this.deck.add(card);
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
    if (this.deck.isEmpty()) {
      throw new IllegalArgumentException("There are no cards in this deck");
    } else
    return this.deck.get(-1);
  }


  /**
   * Populate a deck of cards.
   * @return A full deck of cards.
   */


  @Override
  public void populateDeck() {
    for(Suit suit : Suit.values()) {
      for (Value value : Value.values()) {
        Card newCard = new Card(suit, value);
        addCard(newCard);
      }
    }

  }



}



