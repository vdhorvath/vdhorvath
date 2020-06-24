import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;



public class Deck implements IDeck {
  private List<Card> deck;
  private Integer numberOfCardsInDeck = 52;



  public Deck() {
    this.deck = new ArrayList<>();
    this.populateDeck();
  }

  /**
   * Getter for the deck
   * @return a List of Cards
   */


  public List<Card> getDeck() {
    return this.deck;
  }


  /**
   * Setter for Deck helper to shuffle cards
   * @param index , Index would like to exchange within the deck.
   * @param card , Card we are exchanging.
   */

  private void set(Integer index, Card card) {
    this.deck.set(index, card);

  }


  /**
   * Size of the Deck
   *
   * @return Number of cards in deck
   */


  public Integer size() {
    return this.deck.size();
  }

  /**
   * Return the card at a particular index
   *
   * @return
   */


  @Override
  public Card cardAtPosition(Integer index) throws IndexOutOfBoundsException {
    return this.deck.get(index);
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
    return new Deck().removeCard();
  }


  /**
   * Adds a Card to the Deck
   *
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
  public Card showTopCard() throws EmptyStackException {
    return this.deck.get(-1);
  }


  /**
   * Populate a deck of cards.
   *
   * @return A full deck of cards.
   */


  @Override
  public void populateDeck() {
    for (Suit suit : Suit.values()) {
      for (Value value : Value.values()) {
        Card newCard = new Card(suit, value);
        addCard(newCard);

      }
    }

  }


  /**
   * Shuffle the Deck of Cards
   * @param deck
   */


  public void shuffleDeck(Deck deck) {
      for (int i = 0; i < numberOfCardsInDeck; i++) {
        int index = i + (int) (Math.random() * (numberOfCardsInDeck - i));
        Card temp = deck.cardAtPosition(index);
        deck.set(index, deck.cardAtPosition(i));
        deck.set(i, temp);
        System.out.println(temp);

      }

    }
  }



